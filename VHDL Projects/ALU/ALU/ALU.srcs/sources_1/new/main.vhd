----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/27/2019 06:09:11 PM
-- Design Name: 
-- Module Name: main - Behavioral
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

entity main is

   Port ( Sw: in std_logic_vector(15 downto 0);
   AN,Seg: out std_logic_vector(7 downto 0);
   CLK: in std_logic;
   LED: out std_logic_vector(15 downto 0);
   BT: in std_logic;
   BTNU:in std_logic;
   BTNL:in std_logic);
end main;

architecture Behavioral of main is
    signal sig: std_logic_vector(31 downto 0);
    signal op:std_logic_vector (4 downto 0):="00000";
    signal bout:std_logic;
    signal bsout:std_logic;
    signal brout:std_logic;
    
        signal eq: std_logic;
        signal gr: std_logic;
        signal ls: std_logic;
        signal ze: std_logic;
        
    signal rest_imp:std_logic_vector(3 downto 0); 
begin
    process(BT,CLK,op)
     begin
     if ( rising_edge(CLK)) then
     if (bout ='1') then
        op<=op +1;
    END IF;
    end if;
    
    end process;
    db0 :entity work.db port map(BT,CLK,bout); --incrementare op alu buton
    
    db1 :entity work.db port map(BTNU,CLK,bsout); --buton start
    
    db2 :entity work.db port map(BTNL,CLK,brout);-- buton reset
    
    ssd :entity work.displ7seg port map (CLK ,'0',sig,AN,Seg);
    
    alu: entity work.ALU1 port map(Sw(7 downto 0),Sw(15 downto 8),op(4 downto 0),Sig(7 downto 0),eq,gr,ls,ze,CLK,bsout,brout,rest_imp);
    
    
    LED(4 DOWNTO 0)<=op(4 downto 0);
    LED(15)<=eq;
    LED(14)<=gr;
    LED(13)<=ls;
    LED(12)<=ze;
    
    LED(10 DOWNTO 7)<=rest_imp(3 downto 0);
end Behavioral;
