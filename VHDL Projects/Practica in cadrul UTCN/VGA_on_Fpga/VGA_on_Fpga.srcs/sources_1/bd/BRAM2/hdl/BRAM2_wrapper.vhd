--Copyright 1986-2016 Xilinx, Inc. All Rights Reserved.
----------------------------------------------------------------------------------
--Tool Version: Vivado v.2016.4 (win64) Build 1756540 Mon Jan 23 19:11:23 MST 2017
--Date        : Sat Aug 29 16:42:09 2020
--Host        : DESKTOP-UG71SN2 running 64-bit major release  (build 9200)
--Command     : generate_target BRAM2_wrapper.bd
--Design      : BRAM2_wrapper
--Purpose     : IP block netlist
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
library UNISIM;
use UNISIM.VCOMPONENTS.ALL;
entity BRAM2_wrapper is
end BRAM2_wrapper;

architecture STRUCTURE of BRAM2_wrapper is
  component BRAM2 is
  end component BRAM2;
begin
BRAM2_i: component BRAM2
 ;
end STRUCTURE;
