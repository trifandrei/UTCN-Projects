LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_ARITH.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.all;


ENTITY square_root IS
  
PORT (
  x: IN STD_LOGIC_VECTOR(7 DOWNTO 0);
  clk, reset, start: IN STD_LOGIC;
  r: INOUT STD_LOGIC_VECTOR(7 DOWNTO 0);
  done: OUT STD_LOGIC
);
END square_root;



ARCHITECTURE behavior OF square_root IS

  SIGNAL next_r, s, next_s: STD_LOGIC_VECTOR(7 DOWNTO 0);
  SIGNAL greater: STD_LOGIC;
  SIGNAL ce, load: STD_LOGIC;
  
  TYPE states IS RANGE 0 TO 2;
  SIGNAL current_state: states;
BEGIN

  next_r <= r + 1;  
  
  next_s <= s + (next_r(6 DOWNTO 0)&'0') + 1;
  
  PROCESS(clk)
  BEGIN
    IF rising_edge(clk) THEN 
      IF LOAD = '1' THEN 
        r <= (OTHERS => '0');
      ELSIF 
        ce = '1' THEN r <= next_r; 
      END IF;
    END IF;
  END PROCESS;

   PROCESS(clk)
  BEGIN
    IF rising_edge(clk) THEN 
      IF LOAD = '1' THEN 
        s <= CONV_STD_LOGIC_VECTOR(1,8);
      ELSIF ce = '1' THEN
         s <= next_s; 
      END IF;
    END IF;
  END PROCESS;
  greater <= '1' WHEN s > x ELSE '0';

  PROCESS(current_state, start, greater)  
  BEGIN
    CASE current_state IS
      WHEN 0 => ce <= '0'; load <= '0'; done <= '1';
      WHEN 1 => ce <= '0'; 
                IF start = '1' THEN 
                    load <= '1'; 
                    done <= '0'; 
                    ELSE
                    load <= '0'; 
                    done <= '1'; 
                 END IF;
      WHEN 2 => IF greater = '0' THEN
                     ce <= '1';
                      ELSE 
                      ce <= '0'; 
                END IF;
                load <= '0'; done <= '0';
    END CASE;
  END PROCESS;

  PROCESS(clk, reset)
  BEGIN
    IF reset = '1' THEN 
    current_state <= 0;
    ELSIF rising_edge(clk) THEN
      CASE current_state IS
       WHEN 0 => IF start = '0' THEN 
                    current_state <= 1;
                 END IF;
       WHEN 1 => IF start = '1' THEN
                     current_state <= 2;
                   END IF;
       WHEN 2 => IF greater = '1' THEN 
                    current_state <= 0;
                  END IF;
      END CASE;
    END IF;
  END PROCESS;

END behavior;