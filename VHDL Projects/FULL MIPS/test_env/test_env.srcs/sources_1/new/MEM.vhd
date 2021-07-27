----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 10/04/2019 01:04:31 AM
-- Design Name: 
-- Module Name: MEM - Behavioral
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
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity MEM is
  Port (clk :in std_logic;
        MemWrite :in std_logic;
        ALURez :in std_logic_vector(15 downto 0);
        RD2 :in std_logic_vector(15 downto 0);
        MemData :out std_logic_vector(15 downto 0);
        ALURez2 :out std_logic_vector(15 downto 0);
        MPGEnable :in std_logic);
end MEM;

architecture Behavioral of MEM is
type memorie is array (0 to 15) of STD_LOGIC_VECTOR (15 downto 0);
signal mem: memorie := (
0=> X"0000",
1=> X"0001",
2=> X"0002",
3=> X"0002",
4=> X"0002",
5=> X"0002",
6=> X"0002",
7=> X"0002",
8=> X"0002",
9=> X"0002",
10=>X"0002",
11=> X"AAAA",
others => x"9999"
);

signal addres: std_logic_vector(3 downto 0);
begin

addres<=ALURez(3 downto 0);


MemData <= mem(conv_integer(ALURez(3 downto 0)));
process (clk,MemWrite,MPGEnable)
begin 
    if(rising_edge(clk)) then
		if MPGEnable='1' then
			if MemWrite='1' then
				mem(conv_integer(addres))<=RD2;			
			end if;
		end if;	
	end if;
	
end process;
ALURez2<=ALURez;
end Behavioral;
