----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 12/18/2019 01:46:25 PM
-- Design Name: 
-- Module Name: impartire - Behavioral
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
use IEEE.STD_LOGIC_unsigned.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity impartire is
    Port (num:in std_logic_vector(7 downto 0);
          denum:in std_logic_vector(3 downto 0);
          cat:out std_logic_vector(7 downto 0);
          rest:out std_logic_vector(3 downto 0) );
end impartire;

architecture Behavioral of impartire is
 procedure div4(
                num1:in std_logic_vector(7 downto 0);
                denum1:in std_logic_vector(3 downto 0);
                cat1:out std_logic_vector(3 downto 0);
                rest1:out std_logic_vector(3 downto 0)) is
 variable d,n1:std_logic_vector(4 downto 0);
 variable n2:std_logic_vector(3 downto 0);
begin
    d:='0'&denum1;
    n2:= num1(3 downto 0);
    n1:='0'&num1(7 downto 4);
    for i in 0 to 3 loop
        n1:=n1(3 downto 0)& n2(3);
        n2:=n2(2 downto 0)& '0';
        if n1>=d then
            n1:=n1-d;
            n2(0):='1';
         end if;
      end loop;
     cat1:=n2;
     rest1:=n1(3 downto 0);
     end procedure;
begin
  process(num,denum)
    variable rH,rL,qH,qL:std_logic_vector(3 downto 0);
   begin
      div4("0000"&num(7 downto 4),denum,qH,rH);
      div4(rH & num(3 downto 0),denum,qL,rL);
      cat(7 downto 4)<=qH;
      cat(3 downto 0)<=qL;
      rest<=rL;
   end process;
end Behavioral;
