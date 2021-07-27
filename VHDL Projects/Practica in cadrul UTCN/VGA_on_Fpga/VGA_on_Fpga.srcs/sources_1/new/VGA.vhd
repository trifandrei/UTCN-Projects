----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 08/29/2020 12:45:49 PM
-- Design Name: 
-- Module Name: VGA - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity VGA is
 Port (
       clk25       : in  STD_LOGIC;
       red     : out STD_LOGIC_VECTOR(4 downto 0);
       green   : out STD_LOGIC_VECTOR(5 downto 0);
       blue    : out STD_LOGIC_VECTOR(4 downto 0);
       hsync   : out STD_LOGIC;
       vsync   : out STD_LOGIC;
       frame_addr  : out STD_LOGIC_VECTOR(17 downto 0);
       frame_pixel : in  STD_LOGIC_VECTOR(11 downto 0)
   );
end VGA;

architecture Behavioral of VGA is

   constant hrez       : natural := 640;
   constant hstartsync : natural := 640+16;
   constant hendsync   : natural := 640+16+96;
   constant hmaxcount  : natural := 800;

   constant vrez       : natural := 480;
   constant vstartsync : natural := 480+10;
   constant vendsync   : natural := 480+10+2;
   constant vmaxcount  : natural := 480+10+2+33;

   constant hsync_active : std_logic := '0';
   constant vsync_active : std_logic := '0';

   signal hcounter : unsigned( 9 downto 0) := (others => '0');
   signal vcounter : unsigned( 9 downto 0) := (others => '0');
   signal address  : unsigned(18 downto 0) := (others => '0');
   signal blank    : std_logic := '1';
begin
 frame_addr <= std_logic_vector(address(18 downto 1));

  process(clk25)
  begin
     if rising_edge(clk25) then
      
        if hcounter = hmaxcount-1 then
           hCounter <= (others => '0');
           if vcounter = vmaxcount-1 then
              vcounter <= (others => '0');
           else
              vcounter <= vcounter+1;
           end if;
        else
           hcounter <= hcounter+1;
        end if;

        if blank = '0' then
           red   <= frame_pixel(11 downto 8) & "0";
           green <= frame_pixel( 7 downto 4) & "00";
           blue  <= frame_pixel( 3 downto 0) & "0";
        else
           red   <= (others => '0');
           green <= (others => '0');
           blue  <= (others => '0');
        end if;

        if vcounter  >= vrez then
           address <= (others => '0');
           blank <= '1';
        else
           if hcounter  < 640 then
              blank <= '0';
              address <= address+1;
           else
              blank <= '1';
           end if;
        end if;

        
        if hcounter > hstartsync and hcounter <= hendsync then
          hsync <= hsync_active;
        else
           hsync <= not hsync_active;
        end if;

       
        if vcounter >= vstartsync and vcounter < vendsync then
           vsync <= vsync_active;
        else
           vsync <= not vsync_active;
        end if;
     end if;
  end process;
end Behavioral;
