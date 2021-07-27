----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 08/28/2020 11:50:04 PM
-- Design Name: 
-- Module Name: I2C - Behavioral
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

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity I2C is
 Port ( clk   : in  STD_LOGIC;	 
           siod  : inout  STD_LOGIC;--SSCB serial data i/o
           sioc  : out  STD_LOGIC;--SSCB serial CLK
		   taken : out  STD_LOGIC;
		   send  : in  STD_LOGIC;
           id    : in  STD_LOGIC_VECTOR (7 downto 0);
           reg   : in  STD_LOGIC_VECTOR (7 downto 0);
           value : in  STD_LOGIC_VECTOR (7 downto 0));
end I2C;

architecture Behavioral of I2C is
    signal   divider  : unsigned (7 downto 0) := "00000001";
	signal   busy  : std_logic_vector(31 downto 0) := (others => '0');
	signal   data  : std_logic_vector(31 downto 0) := (others => '1');
begin
process(busy, data(31))
	begin
		if busy(11 downto 10) = "10" or  busy(20 downto 19) = "10" or busy(29 downto 28) = "10" then
			siod <= 'Z';
		else
			siod <= data(31);
		end if;
	end process;
	
	process(clk)
	begin
		if rising_edge(clk) then
			taken <= '0';
			if busy(31) = '0' then
				sioc <= '1';
				if send = '1' then
					if divider = "00000000" then
						data <= "100" &   id & '0'  &   reg & '0' & value & '0' & "01";
						busy <= "111" & "111111111" & "111111111" & "111111111" & "11";
						taken <= '1';
					else
						divider <= divider+1; 
					end if;
				end if;
			else

				case busy(32-1 downto 32-3) & busy(2 downto 0) is
					when "111"&"111" => 
						case divider(7 downto 6) is
							when "00"   => sioc <= '1';
							when "01"   => sioc <= '1';
							when "10"   => sioc <= '1';
							when others => sioc <= '1';
						end case;
					when "111"&"110" => 
						case divider(7 downto 6) is
							when "00"   => sioc <= '1';
							when "01"   => sioc <= '1';
							when "10"   => sioc <= '1';
							when others => sioc <= '1';
						end case;
					when "111"&"100" => 
						case divider(7 downto 6) is
							when "00"   => sioc <= '0';
							when "01"   => sioc <= '0';
							when "10"   => sioc <= '0';
							when others => sioc <= '0';
						end case;
					when "110"&"000" => 
						case divider(7 downto 6) is
							when "00"   => sioc <= '0';
							when "01"   => sioc <= '1';
							when "10"   => sioc <= '1';
							when others => sioc <= '1';
						end case;
					when "100"&"000" =>
						case divider(7 downto 6) is
							when "00"   => sioc <= '1';
							when "01"   => sioc <= '1';
							when "10"   => sioc <= '1';
							when others => sioc <= '1';
						end case;
					when "000"&"000" => 
						case divider(7 downto 6) is
							when "00"   => sioc <= '1';
							when "01"   => sioc <= '1';
							when "10"   => sioc <= '1';
							when others => sioc <= '1';
						end case;
					when others      => 
						case divider(7 downto 6) is
							when "00"   => sioc <= '0';
							when "01"   => sioc <= '1';
							when "10"   => sioc <= '1';
							when others => sioc <= '0';
						end case;
				end case;   

				if divider = "11111111" then
                    busy<= busy(32-2 downto 0) & '0';
					data<= data(32-2 downto 0) & '1';
					divider <= (others => '0');
				else
					divider <= divider+1;
				end if;
			end if;
		end if;
	end process;

end Behavioral;
