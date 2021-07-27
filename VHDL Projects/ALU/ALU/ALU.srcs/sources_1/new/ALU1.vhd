----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/18/2019 05:36:38 PM
-- Design Name: 
-- Module Name: ALU1 - Behavioral
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
--------------------------------------------------------------------------------
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

entity ALU1 is
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
           reset:in std_logic;
           rest_imp:out std_logic_vector(3 downto 0)
            );
end ALU1;

architecture A1 of ALU1 is
    
   

    signal OP1: std_logic_vector(7 downto 0);
    signal OP2: std_logic_vector(7 downto 0);
    signal RezAux: std_logic_vector(7 downto 0);
    signal Aux :integer;
    signal eq: std_logic;
    signal gr: std_logic;
    signal ls: std_logic;
    signal ze: std_logic;
    
    signal rezz:std_logic_vector(7 downto 0);
    signal don: std_logic;
    
    signal rez_imp:std_logic_vector(7 downto 0);
    signal rst_imp:std_logic_vector(3 downto 0);
    signal rez_imm:std_logic_vector(7 downto 0);
begin
    OP1<=A;
    OP2<=B;
    
    sqrt :entity work.square_root port map(A,CLK,reset,start,rezz,don);
    
    imp:entity  work.impartire port map(A,B(3 downto 0),rez_imp,rst_imp);
    
    imm:entity  work.inmultire port map(A(3 downto 0),B(3 downto 0),rez_imm);
    
    Process(OP1,OP2,Op,RezAux,ls,gr,ze,eq)
      begin
        case Op is
            when "00000" => RezAux<= OP1+OP2; --- ADD;
            when "00001" => RezAux<= OP1-OP2; --- SUB;
            when "00010" => RezAux<= OP1 and OP2; --SI
            when "00011" => RezAux<= OP1 or OP2;  --SAU
            when "00100" => RezAux<= OP1 nand OP2; --SI-Nu
            when "00101" => RezAux<= OP1 nor OP2;  --Sau-Nu
            when "00111" => RezAux<= OP1 xor OP2;  --Sau-exclusiv
            when "01000" => RezAux<= OP1 xnor op2; --XNOR
            
            when "01001" => if (OP1>OP2) then ---MAI MARE
                             RezAux<=OP1;
                             gr<='1';
                              else
                             RezAux<=OP2;
                             gr<='0';
                           end if;
                            
            when "01010"=> if(OP1<OP2) then  ---Mai Mic
                            RezAux <= OP1;
                            ls<='1';
                             else
                            RezAux <= OP2;
                            ls<='0';
                          end if;
                          
             when "01011"=> if (OP1=OP2)  then   --EGAL
                               RezAux <=OP1;
                               eq<='1';
                               else
                               RezAux<="00000000";
                               eq<='0';
                            end if;  
                                   
              when "01100"=>  RezAux<= std_logic_vector(to_unsigned((to_integer(unsigned(OP1))+to_integer(unsigned(OP2)))/2, 8));
               
              when "01101" => RezAux <= Op1(7 downto 1) & "0"; --SLR
              
              when "01110" => RezAux <= "0"& Op1(6 downto 0); --SLL
              
              when "01111" =>RezAux<= rezz;
              
              when "10000" =>RezAux<=rez_imp;
              
              when "10001" =>RezAux<=rez_imm;
              
              when others => RezAux <= "11111111";
         end case;
         
         
         Rez<=RezAux;
         f_eq<=eq;
         f_ls<=ls;
         f_gr<=gr;
         
         if RezAux="00000000" then
            ze<='1';
            else
            ze<='0';
          end if;
         f_ze<=ze; 
         rest_imp<=rst_imp;
      end process;

end A1;
