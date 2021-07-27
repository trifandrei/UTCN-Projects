
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;



entity REGISTER_FILE is
    Port ( RA1 : in STD_LOGIC_VECTOR (2 downto 0);
           RA2 : in STD_LOGIC_VECTOR (2 downto 0);
           WA : in STD_LOGIC_VECTOR (2 downto 0);
           WD : in STD_LOGIC_VECTOR (15 downto 0);
           WE : in STD_LOGIC;
           EnableMPG: in std_logic;
           CLK : in STD_LOGIC;
           RD1 : out STD_LOGIC_VECTOR (15 downto 0);
           RD2 : out STD_LOGIC_VECTOR (15 downto 0));
end REGISTER_FILE;

architecture Behavioral of REGISTER_FILE is
type memorie is array (0 to 15) of std_logic_vector (15 downto 0);
signal mem: memorie := (
        X"0000", --$0
		X"0001",  --$1
		X"0002", --$2
		X"0003", --$3
		X"0004", --$4
		X"0005", --$5
		X"0006", --$6
		X"0007", --$7
		others => X"0000");


begin
RD1<=mem(conv_integer(RA1));
RD2<=mem(conv_integer(RA2));

process(clk,EnableMPG,WE) 
begin
  if EnableMPG='1' then
    if rising_edge(clk) and WE='1' then
       mem(conv_integer(WA))<=WD; 
end if;
end if;
end process;

end Behavioral;
