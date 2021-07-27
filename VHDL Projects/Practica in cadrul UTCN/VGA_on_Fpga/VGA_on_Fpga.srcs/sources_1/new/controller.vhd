----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 08/28/2020 10:38:44 PM
-- Design Name: 
-- Module Name: controller - Behavioral
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
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity controller is
 Port ( clk   : in    STD_LOGIC;
          resend :in    STD_LOGIC;
          config_finished : out std_logic;
          sioc  : out   STD_LOGIC;
          siod  : inout STD_LOGIC;
          reset : out   STD_LOGIC;
          pwdn  : out   STD_LOGIC;
          xclk  : out   STD_LOGIC
);
end controller;

architecture Behavioral of controller is
COMPONENT registers
	PORT(
		clk      : in std_logic;
		advance  : in std_logic;          
		resend   : in STD_LOGIC;
		comand  : out std_logic_vector(15 downto 0);
		terminat : out std_logic
		);
	END COMPONENT;

	COMPONENT I2C
	PORT(
		clk   : in std_logic;
		send  : in std_logic;
		taken : out std_logic;
		id    : in std_logic_vector(7 downto 0);
		reg   : in std_logic_vector(7 downto 0);
		value : in std_logic_vector(7 downto 0);    
		siod  : inout std_logic;      
		sioc  : out std_logic
		);
	END COMPONENT;

	signal sys_clk  : std_logic := '0';	
	signal comand  : std_logic_vector(15 downto 0);
	signal finished : std_logic := '0';
	signal taken    : std_logic := '0';
	signal send     : std_logic;

	constant camera_address : std_logic_vector(7 downto 0) := x"42"; --adresa de scriere a slave-ului
begin
    config_finished <= finished;
	
	send <= not finished;
	A1: I2C PORT MAP(clk   => clk,taken => taken,siod  => siod,sioc  => sioc,send  => send,id    => camera_address,reg   => comand(15 downto 8),value => comand(7 downto 0));

	reset <= '1'; 						
	pwdn  <= '0'; 					
	xclk  <= sys_clk;
	
  A2:registers PORT MAP(	clk      => clk,advance  => taken,comand  => comand,terminat => finished,resend   => resend);

	process(clk)
	begin
		if rising_edge(clk) then
			sys_clk <= not sys_clk;
		end if;
	end process;

end Behavioral;
