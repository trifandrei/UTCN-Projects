----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 12/18/2019 05:12:40 PM
-- Design Name: 
-- Module Name: inmultire - Behavioral
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

entity inmultire is
      Port (x,y:in std_logic_vector(3 downto 0);
            P:out std_logic_vector(7 downto 0));
end inmultire;

architecture Behavioral of inmultire is
  
  signal x0y1:std_logic;
  signal x1y0:std_logic;
  signal c1:std_logic;
  
  signal x1y1:std_logic;
  signal x2y0:std_logic;
  signal c2:std_logic;
  signal s1:std_logic;
  
  signal x2y1:std_logic;
  signal x3y0:std_logic;
  signal c3:std_logic;
  signal s2:std_logic;
  
  signal x3y1:std_logic;
  signal c4:std_logic;
  signal s3:std_logic;
  
  signal x0y2:std_logic;
  signal c5:std_logic;
  
   signal x1y2:std_logic;
   signal c6:std_logic;
   signal ss1:std_logic;
   
   signal x2y2:std_logic;
   signal c7:std_logic;
   signal ss2:std_logic;
   
   signal x3y2:std_logic;
   signal c8:std_logic;
   signal ss3:std_logic;
   
   
    signal x0y3:std_logic;
    signal c9:std_logic;
    
    signal x1y3:std_logic;
    signal c10:std_logic;
    
    signal x2y3:std_logic;
    signal c11:std_logic;
        
     signal x3y3:std_logic;
     signal c12:std_logic;
begin
   --------------NIVELUL 1-------------------------------------------------------------
   P(0)<= x(0) and y(0);
   
    x0y1<=x(0) and y(1);
    x1y0<=x(1) and y(0);
    se1:entity  work.sume port map(x0y1,x1y0,'0',c1,P(1));
    
     x1y1<=x(1) and y(1);
     x2y0<=x(2) and y(0);
     se2:entity  work.sume port map(x1y1,x2y0,c1,c2,s1);
     
      x2y1<=x(2) and y(1);
      x3y0<=x(3) and y(0);
      se3:entity  work.sume port map(x2y1,x3y0,c2,c3,s2);
      
      x3y1<=x(3) and y(1);
      se4:entity  work.sume port map(x3y1,'0',c3,c4,s3);
      
-----------NIVELUL 2---------------------------------------------------------------------------------- 
     
      x0y2<=x(0) and y(2);
      se5:entity  work.sume port map(x0y2,s1,'0',c5,P(2));
      
       x1y2<=x(1) and y(2);
       se6:entity  work.sume port map(x1y2,s2,c5,c6,ss1);
       
        x2y2<=x(2) and y(2);
        se7:entity  work.sume port map(x2y2,s3,c6,c7,ss2);
        
         x3y2<=x(3) and y(2);
         se8:entity  work.sume port map(x3y2,c4,c7,c8,ss3);
         
-------------------NIVELUL  3------------------------------------------------------------------------- 
              
        x0y3<=x(0) and y(3);
        se9:entity  work.sume port map(x0y3,ss1,'0',c9,P(3));
               
        x1y3<=x(1) and y(3);
        se10:entity  work.sume port map(x1y3,ss2,c9,c10,P(4));
                
        x2y3<=x(2) and y(3);
        se11:entity  work.sume port map(x2y3,ss3,c10,c11,P(5));
                 
        x3y3<=x(3) and y(3);
        se12:entity  work.sume port map(x3y3,c8,c11,c12,P(6));
                  
        P(7)<=c12;
      
end Behavioral;
