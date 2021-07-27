--Copyright 1986-2016 Xilinx, Inc. All Rights Reserved.
----------------------------------------------------------------------------------
--Tool Version: Vivado v.2016.4 (win64) Build 1756540 Mon Jan 23 19:11:23 MST 2017
--Date        : Sat Aug 29 16:42:09 2020
--Host        : DESKTOP-UG71SN2 running 64-bit major release  (build 9200)
--Command     : generate_target BRAM2.bd
--Design      : BRAM2
--Purpose     : IP block netlist
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
library UNISIM;
use UNISIM.VCOMPONENTS.ALL;
entity BRAM2 is
  attribute CORE_GENERATION_INFO : string;
  attribute CORE_GENERATION_INFO of BRAM2 : entity is "BRAM2,IP_Integrator,{x_ipVendor=xilinx.com,x_ipLibrary=BlockDiagram,x_ipName=BRAM2,x_ipVersion=1.00.a,x_ipLanguage=VHDL,numBlks=1,numReposBlks=1,numNonXlnxBlks=0,numHierBlks=0,maxHierDepth=0,numSysgenBlks=0,numHlsBlks=0,numHdlrefBlks=0,numPkgbdBlks=0,bdsource=USER,synth_mode=OOC_per_IP}";
  attribute HW_HANDOFF : string;
  attribute HW_HANDOFF of BRAM2 : entity is "BRAM2.hwdef";
end BRAM2;

architecture STRUCTURE of BRAM2 is
  component BRAM2_blk_mem_gen_0_0 is
  port (
    clka : in STD_LOGIC;
    wea : in STD_LOGIC_VECTOR ( 0 to 0 );
    addra : in STD_LOGIC_VECTOR ( 17 downto 0 );
    dina : in STD_LOGIC_VECTOR ( 11 downto 0 );
    clkb : in STD_LOGIC;
    addrb : in STD_LOGIC_VECTOR ( 17 downto 0 );
    doutb : out STD_LOGIC_VECTOR ( 11 downto 0 )
  );
  end component BRAM2_blk_mem_gen_0_0;
  signal NLW_blk_mem_gen_0_doutb_UNCONNECTED : STD_LOGIC_VECTOR ( 11 downto 0 );
begin
blk_mem_gen_0: component BRAM2_blk_mem_gen_0_0
     port map (
      addra(17 downto 0) => B"000000000000000000",
      addrb(17 downto 0) => B"000000000000000000",
      clka => '0',
      clkb => '0',
      dina(11 downto 0) => B"000000000000",
      doutb(11 downto 0) => NLW_blk_mem_gen_0_doutb_UNCONNECTED(11 downto 0),
      wea(0) => '0'
    );
end STRUCTURE;
