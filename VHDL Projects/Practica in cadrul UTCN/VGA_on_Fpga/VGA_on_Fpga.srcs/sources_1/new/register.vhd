----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 08/28/2020 11:09:00 PM
-- Design Name: 
-- Module Name: register - Behavioral
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
use IEEE.NUMERIC_STD.ALL;
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity registers is
    Port ( clk:in STD_LOGIC;
           resend :in STD_LOGIC;
           advance:in STD_LOGIC;
           comand: out STD_LOGIC_VECTOR(15 downto 0);
           terminat:out STD_LOGIC);
end registers;

architecture Behavioral of registers is
    
    signal reg :STD_LOGIC_VECTOR(15 downto 0);
    signal adr: STD_LOGIC_VECTOR(7 downto 0):=(others =>'0');
    
begin
    comand<=reg;
    with reg select terminat <= '1' when x"FFFF",'0' when others;--termin transferul prima data cand reg ajunge FFFF.

    process (clk)
    begin
     if rising_edge(clk) then
       if resend ='1' then 
        adr<=(others => '0');
      elsif advance='1' then 
        adr<=std_logic_vector(unsigned (adr)+1);
      end if;
        
      case adr is
      
          when x"00" => reg <= x"1280"; -- COM7   
          when x"01" => reg <= x"1280"; -- COM7   
          when x"02" => reg <= x"1204"; -- COM7   
          when x"03" => reg <= x"1100"; -- CLKRC  
          when x"04" => reg <= x"0C00"; -- COM3  
          when x"05" => reg <= x"3E00"; -- COM14                     
          when x"06" => reg <= x"8C00"; -- RGB444 
          when x"07" => reg <= x"0400"; -- COM1  
          when x"08" => reg <= x"4010"; -- COM15  
          when x"09" => reg <= x"3a04"; -- TSLB  
          when x"0A" => reg <= x"1438"; -- COM9  
          when x"0B" => reg <= x"4fb3"; -- MTX1  
          when x"0C" => reg <= x"50b3"; -- MTX2  
          when x"0D" => reg <= x"5100"; -- MTX3  
          when x"0E" => reg <= x"523d"; -- MTX4  
          when x"0F" => reg <= x"53a7"; -- MTX5  
          
          when x"10" => reg <= x"54e4"; -- MTX6 
          when x"11" => reg <= x"589e"; -- MTXS 
          when x"12" => reg <= x"3dc0"; -- COM13 
          when x"13" => reg <= x"1100"; -- CLKRC  
          when x"14" => reg <= x"1711"; -- HSTART 
          when x"15" => reg <= x"1861"; -- HSTOP 
          when x"16" => reg <= x"32A4"; -- HREF      
          when x"17" => reg <= x"1903"; -- VSTART
          when x"18" => reg <= x"1A7b"; -- VSTOP  
          when x"19" => reg <= x"030a"; -- VREF  
          when x"1A" => reg <= x"0e61"; -- COM5
          when x"1B" => reg <= x"0f4b"; -- COM6
          when x"1C" => reg <= x"1602"; 
          when x"1D" => reg <= x"1e37";
          when x"1E" => reg <= x"2102";
          when x"1F" => reg <= x"2291";     
               
          when x"20" => reg <= x"2907";
          when x"21" => reg <= x"330b";                               
          when x"22" => reg <= x"350b";
          when x"23" => reg <= x"371d";
          when x"24" => reg <= x"3871";
          when x"25" => reg <= x"392a";                                          
          when x"26" => reg <= x"3c78"; 
          when x"27" => reg <= x"4d40";                                   
          when x"28" => reg <= x"4e20";
          when x"29" => reg <= x"6900";                                  
          when x"2A" => reg <= x"6b4a";
          when x"2B" => reg <= x"7410";                                      
          when x"2C" => reg <= x"8d4f";
          when x"2D" => reg <= x"8e00";                                        
          when x"2E" => reg <= x"8f00";
          when x"2F" => reg <= x"9000";
                                  
          when x"30" => reg <= x"9100";
          when x"31" => reg <= x"9600";                                
          when x"32" => reg <= x"9a00";
          when x"33" => reg <= x"b084";                                  
          when x"34" => reg <= x"b10c";
          when x"35" => reg <= x"b20e";                                
          when x"36" => reg <= x"b382";
          when x"37" => reg <= x"b80a";
       
          when others => reg <= x"FFFF";
      end case;
     end if;
    end process;
end Behavioral;
