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

entity EX is
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
end EX;

architecture Behavioral of EX is
signal ALU_IN: std_logic_vector(15 downto 0);
signal ALU_control: std_logic_vector(3 downto 0);
signal ALU_Rez_Aux: std_logic_vector(15 downto 0);
signal Zero_flag: std_logic;



begin

BranchAdr<=PcNext + Ext_imm;

process (RD1,Ext_imm,ALUSrc)
    begin
    case ALUSrc is
        when '0' =>
           ALU_IN<=RD2;
        when '1' =>
           ALU_IN<=Ext_imm;
    end case;
end process;

process (ALUOp,func)
    begin
    case ALUOp is 
        when "000" => 
                    case func is 
                        when "000" => ALU_control <= "0000";
                        when "001" => ALU_control <= "0001";
                        when "010" => ALU_control <= "0010";
                        when "011" => ALU_control <= "0011";
                        when "100" => ALU_control <= "0100";
                        when "101" => ALU_control <= "0101";
                        when "110" => ALU_control <= "0110";
                        when "111" => ALU_control <= "0111";
                    end case;
        when "001" => ALU_control <= "1000"; -- addi
        when "010" => ALU_control <= "1001"; --lw
        when "011" => ALU_control <= "1010"; --sw
        when "100" => ALU_control <= "1011"; --beq
        when "101" => ALU_control <= "1100"; --andi
        when "110" => ALU_control <= "1101"; --ori
        when "111" => ALU_control <= "1110"; --jump
        when others=> AlU_control <= "1111";
        end case;
end process;

process (ALU_control,RD1,ALU_IN,sa,Ext_imm)
begin
    case ALU_control is 
        when "0000" => ALU_Rez_Aux <= not(RD1 or ALU_IN);      -- NOR
        when "0001" => ALU_Rez_Aux <= RD1 + ALU_IN;            --ADD
        when "0010" => ALU_Rez_Aux <= RD1 - ALU_IN;            --SUB
        when "0011" => case (sa) is                            --SLL
                                when '1' => ALU_Rez_Aux<=RD1(14 downto 0) & "0";
                                when others => ALU_Rez_Aux<=RD1;    
                            end case;
        when "0100" => case (sa) is                            --SLR
                                when '1' => ALU_Rez_Aux<="0" & RD1(15 downto 1);
                                when others => ALU_Rez_Aux<=RD1;
                        end case;
        when "0101" => ALU_Rez_Aux <= RD1 and ALU_IN;           --AND 
        when "0110" => ALU_Rez_Aux <= RD1 or ALU_IN;            --OR
        when "0111" => ALU_Rez_Aux <= RD1 xor ALU_IN;           --XOR
        when "1000" => ALU_Rez_Aux <= RD1 + ALU_IN;            --ADDI
        when "1001" => ALU_Rez_Aux<=X"0000";
        when "1010" => ALU_Rez_Aux<=X"0000";
        when "1011" => ALU_Rez_Aux<= RD1-ALU_IN;                 --BEQ
        when "1100" => ALU_Rez_Aux<= Ext_imm and RD1 ;          --ANDI
        when "1101" => ALU_Rez_Aux<=Ext_imm or RD1 ;            -- ORI
        when "1110" => ALU_Rez_Aux<=X"ffff";
        when others => ALU_Rez_Aux<=X"ffff";                    --JUMP
    end case;
    case ALU_Rez_Aux is
            when X"0000" => Zero_flag <= '1';
            when others => Zero_flag <= '0';
        end case;
end process;



    Zero<=Zero_flag;
    ALURez<=ALU_Rez_Aux;
    

end Behavioral;
