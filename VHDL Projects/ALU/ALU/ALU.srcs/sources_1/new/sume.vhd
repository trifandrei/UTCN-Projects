----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 12/19/2019 04:40:24 PM
-- Design Name: 
-- Module Name: sume - Behavioral
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

entity sume is
     Port (x,y,cin:STD_LOGIC;
           cout,s:out std_logic);
end sume;

architecture Behavioral of sume is

begin
    s<=x xor y xor cin;
    cout<=  (x and y) or ( x and cin) or (y and cin);
    

end Behavioral;
