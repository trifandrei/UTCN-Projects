----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 12/16/2019 09:41:51 PM
-- Design Name: 
-- Module Name: imp8 - Behavioral
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

entity imp8 is
--  Port ( );
end imp8;

architecture Behavioral of imp8 is
 signal clr : std_logic:='0';
 signal clk : std_logic:='0';
 signal start : std_logic:='0';
 signal A,B,Q,R:std_logic_vector(7 downto 0);
 
 constant clk_period:time:= 100 ns;
begin
    DUT: entity WORK.impartitor8 port map (clr,clk,start,A,B,Q,R);
    clkp:process
        begin
           clk<='0';
           wait for clk_period/2;
           clk<='1';
           wait for clk_period/2;
        end process;
        
       clr<='1' ,'0' after 50 ns;
       start<='0','1' after 70 ns;
       
      sim_proc:process
          begin
          B<="00110000";
          A<="00001111";
           wait;
            end process;
end Behavioral;
