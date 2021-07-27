library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.all;

entity Instruction_Fetch is
    Port ( WE : in STD_LOGIC;
           RESET : in STD_LOGIC;
           clk : in STD_LOGIC;
           BranchAddress : in STD_LOGIC_VECTOR (15 downto 0);
           JumpAddress : in STD_LOGIC_VECTOR (15 downto 0);
           JCS : in STD_LOGIC;
           PCSrc : in STD_LOGIC;
           Instruction : out STD_LOGIC_VECTOR (15 downto 0);
           PC : out STD_LOGIC_VECTOR (15 downto 0));
end Instruction_Fetch;

architecture Behavioral of Instruction_Fetch is

type memorie is array (0 to 15) of std_logic_vector (15 downto 0);



signal ROM : memorie := (
b"000_000_000_001_0_001",      -- add $1,$0,$0      --0011
b"001_000_100_0001010",      -- addi $4,$0,10       --220A
b"000_000_000_010_0_001",      -- add $2,$0,$0       --0021
b"000_000_000_101_0_001",      -- add $5,$0,$0       --0051
b"100_001_100_0000111",       --beq $1,$4,7         --8607
b"010_010_011_0000000",      -- lw $3,30($2)       --49A4
b"001_011_011_0001000",        --addi $3,$3,8        --2D88
b"011_010_011_0000000",      -- sw $3,40($2)         --69A8
b"000_101_011_101_0001",     --add $5,$5,$3          --15D1
b"001_010_010_0000100",        --addi $2,$2,4        --2604
b"001_001_001_0000001",        --addi $1,$1,1        --2481
b"111_000000000100",        --j 4        --E004
b"011_000_101_0000000",        --sw $5,80($0)        --62D0

  
others => X"0000");

signal PCounter, PCAux, NextAdr, AuxSgn: std_logic_vector(15 downto 0) :=X"0000";

begin
process(JCS,AuxSgn,JumpAddress)
begin
	case(JCS) is
		when '0' => NextAdr <= AuxSgn;
		when '1' => NextAdr <= JumpAddress;
	end case;
end process;	

process(PCSrc,PCAux,BranchAddress)
begin
	case (PCSrc) is 
		when '0' => AuxSgn <= PCAux;
		when '1' => AuxSgn<=BranchAddress;
	end case;
end process;	


process(clk,reset)
begin
	if Reset='1' then
		PCounter<=X"0000";
	else if rising_edge(clk) and WE='1' then
		PCounter<=NextAdr;
		end if;
		end if;
end process;
Instruction<=ROM(conv_integer(PCounter(3 downto 0)));
PCAux<=PCounter + '1';
PC <= PCAux;

end Behavioral;
