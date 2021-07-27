----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 08/29/2020 03:52:00 PM
-- Design Name: 
-- Module Name: clock_div - Behavioral
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

entity clock_div is
  Port (
        clk100:in std_logic;
        clk50:out std_logic;
        clk25:out std_logic);
end clock_div;

architecture Behavioral of clock_div is
    COMPONENT bisD
     port(
         Q : out std_logic;    
         Clk :in std_logic;   
         D :in  std_logic ;
         QN:out std_logic
      );
    end component;
    signal qn:std_logic;
    signal qn2:std_logic;
    signal clk2:std_logic;
begin

    A1:bisD port map(clk2,clk100,qn,qn);
    clk50<=clk2;
    A2:bisD port map(clk25,clk2,qn2,qn2);
end Behavioral;
