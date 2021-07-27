
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

entity test_env is
    Port ( clk : in STD_LOGIC;
           btn : in STD_LOGIC_VECTOR (4 downto 0);
           sw : in STD_LOGIC_VECTOR (15 downto 0);
           led : out STD_LOGIC_VECTOR (15 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0);
           cat : out STD_LOGIC_VECTOR (6 downto 0));
end test_env;

architecture Behavioral of test_env is

signal DO:STD_LOGIC_VECTOR(15 downto 0):=x"0000";

------------Control_Unit signals-----------

signal RegDst: std_logic;
signal	 ExtOp: std_logic;
signal	ALUSrc: std_logic;
signal Branch: std_logic;
signal JumpC: std_logic;
signal ALUOp: std_logic_vector(2 downto 0);
signal	MemWrite: std_logic;
signal MemtoReg: std_logic;
signal RegWrite: std_logic;	


------------INSTRUCTION_FETCH signals-----------
signal enable: STD_LOGIC;    --Instruction Fetch - enable
signal enable2: STD_LOGIC;	  --Instruction Fetch - reset
signal BranchAddress:std_logic_vector(15 downto 0);  	   --branch address used for IF
signal JumpAddress:std_logic_vector(15 downto 0); 		   --jump address used for IF
signal SSDOUT : std_logic_vector(15 downto 0);    --afisare SSD
signal InstrOut: std_logic_vector(15 downto 0);			   --next instruction  
signal PCout: std_logic_vector(15 downto 0);				   --program counter	 
signal PCSrc:std_logic;	
signal Jump:STD_LOGIC;		

---------alte semnale---
signal RD1: std_logic_vector(15 downto 0);					--Read Data 1 
signal RD2: std_logic_vector(15 downto 0);					--Read Data 2
signal Ext_Imm : std_logic_vector(15 downto 0);				--Ext_Imm
signal Func :std_logic_vector(2 downto 0);					--Func - Instruction(2 downto 0)
signal SA : std_logic;												--Shift Amount
signal MemData: std_logic_vector(15 downto 0);				--Mem Data = Read Data --Memory -- OUT
signal ALUResFinal: std_logic_vector(15 downto 0);			--ALU Result - OUT from Memory
signal WriteDataReg: std_logic_vector(15 downto 0);		--WriteData for RegFile
signal ZeroSignal : std_logic;
signal ALURes: std_logic_vector(15 downto 0);

----------COMPONENTS-----------
component MPG is
    Port ( btn : in STD_LOGIC;
        clk : in STD_LOGIC;
        en : out STD_LOGIC);     
end component;

component SSD is
    Port ( digit : in STD_LOGIC_VECTOR (15 downto 0);
           clk : in STD_LOGIC;
           cat : out STD_LOGIC_VECTOR (6 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0));
end component;

component Instruction_Fetch is
	Port (WE : in std_logic;
			reset : in std_logic;
			clk: in std_logic;
			BranchAddress : in std_logic_vector(15 downto 0);
			JumpAddress : in std_logic_vector(15 downto 0);
			JCS : in std_logic;
			PCSrc : in std_logic;
			Instruction : out std_logic_vector(15 downto 0);
			PC : out std_logic_vector(15 downto 0));
end component;

component Control_Unit is
Port	( Instr:in std_logic_vector(2 downto 0);
			 RegDst: out std_logic;
			 ExtOp: out std_logic;
			 ALUSrc: out std_logic;
			 Branch: out std_logic;
			 Jump: out std_logic;
			 ALUOp: out std_logic_vector(2 downto 0);
			 MemWrite: out std_logic;
			 MemtoReg: out std_logic;
			 RegWrite: out std_logic);

end component;


component DecodeID is
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
end component;

component EX 
 Port (   PcNext : in std_logic_vector(15 downto 0);
          RD1 : in std_logic_vector(15 downto 0);
          RD2: in std_logic_vector(15 downto 0);
          Ext_imm: in std_logic_vector(15 downto 0);
          sa: in std_logic;
          func: in std_logic_vector(2 downto 0);
          ALUSrc: in std_logic;
          ALUOp: in std_logic_vector(2 downto 0);
          BranchAdr: out std_logic_vector(15 downto 0);
          ALURez: out std_logic_vector(15 downto 0);
          Zero: out std_logic);
end component;

component MEM
  Port (clk :in std_logic;
        MemWrite :in std_logic;
        ALURez :in std_logic_vector(15 downto 0);
        RD2 :in std_logic_vector(15 downto 0);
        MemData :out std_logic_vector(15 downto 0);
        ALURez2 :out std_logic_vector(15 downto 0);
        MPGEnable :in std_logic);
end component;


begin


-------------------AND Zero+Branch---------------
PCSrc<=ZeroSignal and Branch;
-------------------------------------------------

-----------------JumpAddress---------------------
JumpAddress<="000" & InstrOut(12 downto 0);
-------------------------------------------------

M3:MPG port map(btn=>btn(0),clk=>clk,en=>enable);
M4:MPG port map(btn=>btn(1),clk=>clk,en=>enable2);
S1:SSD port map(digit=>SSDOut,clk=>clk,cat=>cat,an=>an);

---------MUX pentru afisare----------------------
process(InstrOut,PCout,RD1,RD2,WriteDataReg,sw)
begin
	case(sw(7 downto 5)) is
		when "000"=>
				SSDOut<=InstrOut;			-----AFISARE INSTROUT-----
		when "001"=>
				SSDOut<=PCout;				-----AFISARE PCOUT=PC+1-----
		when "010"=>
				SSDOut<=RD1;				-----AFISARE RD1-----
		when "011"=>
				SSDOut<=RD2;				-----AFISARE RD2-----
		when "100"=>
				SSDOut<=JumpAddress;	-----AFISARE WD - RegisterFile-----
		when "101"=>
                SSDOut<=ALURes;
        when "110"=>
                SSDOut<=MemData;
		when "111"=>
				SSDOut<=WriteDataReg;
	end case;
end process;

process(RegDst,ExtOp,ALUSrc,Branch,Jump,MemWrite,MemtoReg,RegWrite,sw,ALUOp)
begin
	if sw(0)='0' then		
		led(7)<=RegDst;
		led(6)<=ExtOp;
		led(5)<=ALUSrc;
		led(4)<=Branch;
		led(3)<=Jump;
		led(2)<=MemWrite;
		led(1)<=MemtoReg;
		led(0)<=RegWrite;
		
	else
		led(2 downto 0)<=ALUOp(2 downto 0);
		led(7 downto 3)<="00000";
	end if;
end process;

led(15)<=PCSrc;
IDecode1: DecodeID port map (clk=>clk,
                              Instr=>InstrOut,
                              WD=>WriteDataReg,
                              RegWrite=>RegWrite,
                              RegWrite2=>enable,
                              RegDst=>RegDst,
                              ExtOp=>ExtOp,
                              RD1=>RD1,
                              RD2=>RD2,
                              Ext_Imm=>Ext_Imm,
                              Func=>Func,
                              sa=>SA);


CUnit1: Control_Unit port map (Instr=>InstrOut(15 downto 13),
                                RegDst=>RegDst,
                                ExtOp=>ExtOp,
                                ALUSrc=>ALUSrc,
                                Branch=>Branch,
                                Jump=>Jump,
                                ALUOp=>ALUOp,
                                MemWrite=>MemWrite,
                                MemtoReg=>MemtoReg,
                                RegWrite=>RegWrite);

IF1: Instruction_Fetch port map(WE=>enable,
                                RESET=>enable2,
                                clk=>clk,
                                BranchAddress=>BranchAddress,
                                JumpAddress=>JumpAddress,
                                JCS=>Jump,
                                PCSrc=>PCSrc,
                                Instruction=>InstrOut,
                                PC=>PCout);
                                
                                
                             

ExUnit1 : EX port map(PCout,RD1,RD2,Ext_Imm,SA,func,ALUSrc,ALUOp,BranchAddress,ALURes,ZeroSignal);
                              
                                
Memory1 : MEM port map(clk,MemWrite,ALURes,RD2,MemData,ALUResFinal,enable);
 
process(MemtoReg,ALUResFinal,MemData)
begin
	case (MemtoReg) is
		when '1' => WriteDataReg<=MemData;
		when '0' => WriteDataReg<=ALUResFinal;
		when others => WriteDataReg<=WriteDataReg;
	end case;
end process;
                                                             
end Behavioral;



