----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/04/2019 09:31:09 PM
-- Design Name: 
-- Module Name: DecodeID - Behavioral
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
use IEEE.STD_LOGIC_UNSIGNED.all;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity DecodeID is
Port ( clk: in std_logic;
			Instr: in std_logic_vector(15 downto 0);
			WD: in std_logic_vector(15 downto 0);
			RegWrite: in std_logic;
			RegWrite2: in std_logic;
			RegDst: in std_logic;
			ExtOp: in std_logic;
			RD1: out std_logic_vector(15 downto 0);
			RD2: out std_logic_vector(15 downto 0);
			Ext_Imm : out std_logic_vector(15 downto 0);
			Func : out std_logic_vector(2 downto 0);
			sa : out std_logic);	
end DecodeID;

architecture Behavioral of DecodeID is
signal ReadAddress1: std_logic_vector(2 downto 0);
signal ReadAddress2: std_logic_vector(2 downto 0);
signal WriteAddress: std_logic_vector(2 downto 0):="000";
signal ExtImm: std_logic_vector(15 downto 0);

component REGISTER_FILE is
    Port ( RA1 : in STD_LOGIC_VECTOR (2 downto 0);
           RA2 : in STD_LOGIC_VECTOR (2 downto 0);
           WA : in STD_LOGIC_VECTOR (2 downto 0);
           WD : in STD_LOGIC_VECTOR (15 downto 0);
           WE : in STD_LOGIC;
           EnableMPG: in std_logic;
           CLK : in STD_LOGIC;
           RD1 : out STD_LOGIC_VECTOR (15 downto 0);
           RD2 : out STD_LOGIC_VECTOR (15 downto 0));
end component;
begin

process(ExtOp,Instr)   
begin
	case (ExtOp) is
		when '1' => 	
				case (Instr(6)) is
					when '0' => ExtImm <= B"000000000" & Instr(6 downto 0);--+++
					when '1' =>  ExtImm <=	B"111111111" & Instr(6 downto 0);--- -
				end case;
		when '0' => ExtImm <= B"000000000" & Instr(6 downto 0);
	end case;
end process;

------------------------
process(RegDst,Instr)	
begin
	case (RegDst) is
		when '0' => WriteAddress<=Instr(9 downto 7);--rt
		when '1'=>WriteAddress<=Instr(6 downto 4);--rd
	end case;
end process;
-----------------------------

Func<=Instr(2 downto 0);	
SA<=Instr(3);					
Ext_Imm <= ExtImm;					

ReadAddress1<=Instr(12 downto 10);		
ReadAddress2<=Instr(9 downto 7);			



RF1: REGISTER_FILE port map (RA1=>Readaddress1,
                             RA2=>ReadAddress2,
                             WA=>WriteAddress,
                             WD=>WD,
                             WE=>RegWrite,
                             EnableMPG=>RegWrite2,
                             CLK=>clk,
                             RD1=>RD1,
                             RD2=>RD2);
-------------------------------------------------


end Behavioral;
