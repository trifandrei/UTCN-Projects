----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/18/2019 08:03:22 PM
-- Design Name: 
-- Module Name: ALU_TB - Behavioral
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
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use ieee.numeric_std.all;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity ALU_TB is
--  Port ( );
end ALU_TB;

architecture Behavioral of ALU_TB is
          signal A : std_logic_vector(7 downto 0);
          signal B : std_logic_vector(7 downto 0);
          signal Op: std_logic_vector(4 downto 0);
          signal Rez:  std_logic_vector(7 downto 0);
          signal f_eq: std_logic;
          signal f_gr: std_logic;
                              signal f_ls: std_logic;
                               signal f_ze: std_logic;
                               signal CLK: std_logic:='0';
                               signal start: std_logic:='0';
                               signal reset: std_logic:='1';
           
          
          
        component ALU1 is
               Port ( A : in std_logic_vector(7 downto 0);
                      B : in std_logic_vector(7 downto 0);
                      Op: in std_logic_vector(4 downto 0);
                      Rez: out  std_logic_vector(7 downto 0);
                      f_eq:out std_logic;
                      f_gr:out std_logic;
                      f_ls:out std_logic;
                      f_ze:out std_logic;
                      CLK:in std_logic;
                      start:in std_logic;
                      reset:in std_logic
                       );
           end  component;
begin

    DUT: entity WORK.ALU1 port map (A,B,OP,Rez,f_eq,f_gr,f_ls,f_ze,CLK,start,reset);
             
         
               CLK  <= not(CLK) after 100 ns;
                
                 Op<="10001","00000" after 300 ns,"10001" after 400 ns;
                 A<= "00000100";
                 B<="00000100";
end Behavioral;
