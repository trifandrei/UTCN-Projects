Library IEEE;
USE IEEE.Std_logic_1164.all;

entity bisD is 
   port(
      Q : out std_logic;    
      Clk :in std_logic;   
      D :in  std_logic ;
      QN:out std_logic
   );
end bisD;
architecture Behavioral of bisD is  
begin  
 process(Clk)
 begin 
    if(rising_edge(Clk)) then
   Q <= D; 
   QN<= not D;
    end if;       
 end process;  
end Behavioral; 