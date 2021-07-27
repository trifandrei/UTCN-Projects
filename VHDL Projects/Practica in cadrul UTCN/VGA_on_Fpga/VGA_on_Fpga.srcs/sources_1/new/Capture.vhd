----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 08/28/2020 11:58:10 AM
-- Design Name: 
-- Module Name: Capture - Behavioral
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
         --        Input   | 
         --         href   |    wr        latched           Dout            we address next_addr
         -- ciclu -1  x    |    xx      xxxxxxxxxxxxxxxx  xxxxxxxxxxxx  x   xxxx     xxxx
         -- ciclu 0   1    |    x1      xxxxxxxxRRRRRGGG  xxxxxxxxxxxx  x   xxxx     addr
         -- ciclu 1   0    |    10      RRRRRGGGGGGBBBBB  xxxxxxxxxxxx  x   addr     addr
         -- ciclu 2   x    |    0x      GGGBBBBBxxxxxxxx  RRRRGGGGBBBB  1   addr     addr+1
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity Capture is
      Port (PCLK : in STD_LOGIC;
            VSYNC : in STD_LOGIC;
            HREF : in STD_Logic;
            D : STD_LOGIC_VECTOR(7 downto 0);
            Addr : out STD_LOGIC_VECTOR(17 downto 0);
            Dout : out STD_LOGIC_VECTOR(11 downto 0);
            We : out STD_Logic);
end Capture;

architecture Behavioral of Capture is
signal latched  :STD_LOGIC_VECTOR (15 downto 0 ):=(others => '0');
signal address  :STD_LOGIC_VECTOR (18 downto 0 ):=(others => '0');
signal next_addr:STD_LOGIC_VECTOR (18 downto 0 ):=(others => '0');
signal wr :STD_LOGIC_VECTOR (1 downto 0 ):=(others => '0');

begin

 Addr<= address(18 downto 1);

 process(PCLK) 
 begin
    if rising_edge(PCLK) then
       if VSYNC ='1' then
            address<=(others =>'0');
            next_addr<=(others =>'0');
            wr <=(others =>'0');
         else
            Dout <=latched(15 downto 12) & latched(10 downto 7) & latched(4 downto 1);    
            address<=next_addr;
            We<=wr(1);
            wr<=wr(0)&(HREF and not wr(0));
            latched<=latched(7 downto 0) & D;
       
          if wr(1)='1' then
            next_addr<=std_logic_vector(unsigned(next_addr)+1);
          end if;
        end if;
      end if;
 end process;
end Behavioral;
