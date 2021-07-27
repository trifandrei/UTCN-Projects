----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 08/28/2020 11:56:58 AM
-- Design Name: 
-- Module Name: vga_on_fpga - top
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
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity vga_on_fpga is
 Port (   clk100          : in  STD_LOGIC;
          btnc            : in  STD_LOGIC;
          config_finished : out STD_LOGIC;
          
          vga_hsync : out  STD_LOGIC;
          vga_vsync : out  STD_LOGIC;
          vga_r     : out  STD_LOGIC_vector(4 downto 0);
          vga_g     : out  STD_LOGIC_vector(5 downto 0);
          vga_b     : out  STD_LOGIC_vector(4 downto 0);
          
          ov7670_pclk  : in  STD_LOGIC;
          ov7670_xclk  : out STD_LOGIC;
          ov7670_vsync : in  STD_LOGIC;
          ov7670_href  : in  STD_LOGIC;
          ov7670_data  : in  STD_LOGIC_vector(7 downto 0);
          ov7670_sioc  : out STD_LOGIC;
          ov7670_siod  : inout STD_LOGIC;
          ov7670_pwdn  : out STD_LOGIC;
          ov7670_reset : out STD_LOGIC
          );
end vga_on_fpga;
   
   
       
architecture top of vga_on_fpga is
 COMPONENT VGA
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
   END COMPONENT;
   
   signal cl25: std_logic;
   signal vgar:std_logic_vector(4 downto 0);
   signal vgag:std_logic_vector(5 downto 0);
   signal vgab:std_logic_vector(4 downto 0);
   signal vgahsync:std_logic;
   signal vgavsync:std_logic;
   signal vgaframe_addr  :  STD_LOGIC_VECTOR(17 downto 0);
   signal vgaframe_pixel :   STD_LOGIC_VECTOR(11 downto 0);
   
   COMPONENT BRAM2
    port (
      clka : in STD_LOGIC;
      wea : in STD_LOGIC;
      addra : in STD_LOGIC_VECTOR ( 17 downto 0 );
      dina : in STD_LOGIC_VECTOR ( 11 downto 0 );
      clkb : in STD_LOGIC;
      addrb : in STD_LOGIC_VECTOR ( 17 downto 0 );
      doutb : out STD_LOGIC_VECTOR ( 11 downto 0 )
    );
       END COMPONENT;
    signal cwe:std_logic;
    
    
    COMPONENT Capture
     Port (PCLK : in STD_LOGIC;
               VSYNC : in STD_LOGIC;
               HREF : in STD_Logic;
               D : STD_LOGIC_VECTOR(7 downto 0);
               Addr : out STD_LOGIC_VECTOR(17 downto 0);
               Dout : out STD_LOGIC_VECTOR(11 downto 0);
               We : out STD_Logic);
    end COMPONENT;
    
    signal caddr:STD_LOGIC_VECTOR(17 downto 0);
    signal cdout:STD_LOGIC_VECTOR(11 downto 0);
    signal clk50:std_Logic;
    signal   rdouta :  STD_LOGIC_VECTOR ( 11 downto 0 );
    signal rdinb :  STD_LOGIC_VECTOR ( 11 downto 0 );
    
    COMPONENT controller
     Port ( clk   : in    STD_LOGIC;
             resend :in    STD_LOGIC;
             config_finished : out std_logic;
             sioc  : out   STD_LOGIC;
             siod  : inout STD_LOGIC;
             reset : out   STD_LOGIC;
             pwdn  : out   STD_LOGIC;
             xclk  : out   STD_LOGIC
   );
   END COMPONENT;
   
   COMPONENT clock_div
    Port (
          clk100:in std_logic;
          clk50:out std_logic;
          clk25:out std_logic);
    end COMPONENT;
    
    
    component lab1p3
    Port ( btn1 : in STD_LOGIC;
               clk : in STD_LOGIC;
               enable : out STD_LOGIC);
       end component;
    
   signal debounce:std_logic;
   
begin
     I0:lab1p3 port map(btnc,clk100,debounce);
      
        
    I1:VGA port map(cl25,vgar,vgag,vgab,vgahsync,vgavsync,vgaframe_addr,vgaframe_pixel);
    
             vga_hsync <=vgahsync;
             vga_vsync <=vgavsync;
             vga_r     <=vgar;
             vga_g     <=vgag;
             vga_b     <=vgab;
    
    I2:Capture port map(ov7670_pclk, ov7670_vsync, ov7670_href,ov7670_data,caddr,cdout,cwe);
    
    I3: BRAM2  port map(ov7670_pclk,cwe,caddr,cdout,clk50,vgaframe_addr,vgaframe_pixel);
    
    I4:controller port map(clk50,debounce,config_finished,ov7670_sioc,ov7670_siod,ov7670_reset,ov7670_pwdn,ov7670_xclk);
    
    I5:clock_div port map(clk100,clk50,cl25);
end top;
