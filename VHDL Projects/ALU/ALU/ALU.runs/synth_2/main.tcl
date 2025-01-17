# 
# Synthesis run script generated by Vivado
# 

set_msg_config -id {HDL 9-1061} -limit 100000
set_msg_config -id {HDL 9-1654} -limit 100000
create_project -in_memory -part xc7a100tcsg324-1

set_param project.singleFileAddWarning.threshold 0
set_param project.compositeFile.enableAutoGeneration 0
set_param synth.vivado.isSynthRun true
set_property webtalk.parent_dir {C:/Users/Fonea/Desktop/PROIECT SSC/ALU/ALU.cache/wt} [current_project]
set_property parent.project_path {C:/Users/Fonea/Desktop/PROIECT SSC/ALU/ALU.xpr} [current_project]
set_property default_lib xil_defaultlib [current_project]
set_property target_language VHDL [current_project]
set_property board_part digilentinc.com:nexys4_ddr:part0:1.1 [current_project]
set_property ip_output_repo {c:/Users/Fonea/Desktop/PROIECT SSC/ALU/ALU.cache/ip} [current_project]
set_property ip_cache_permissions {read write} [current_project]
read_vhdl -library xil_defaultlib {
  {C:/Users/Fonea/Desktop/PROIECT SSC/ALU/ALU.srcs/sources_1/new/sume.vhd}
  {C:/Users/Fonea/Desktop/PROIECT SSC/ALU/ALU.srcs/sources_1/new/impartire.vhd}
  {C:/Users/Fonea/Desktop/PROIECT SSC/ALU/ALU.srcs/sources_1/new/Sqrt.vhd}
  {C:/Users/Fonea/Desktop/PROIECT SSC/ALU/ALU.srcs/sources_1/new/inmultire.vhd}
  {C:/Users/Fonea/Desktop/PROIECT SSC/ALU/ALU.srcs/sources_1/imports/new/displ7seg.vhd}
  {C:/Users/Fonea/Desktop/PROIECT SSC/ALU/ALU.srcs/sources_1/imports/new/db.vhd}
  {C:/Users/Fonea/Desktop/PROIECT SSC/ALU/ALU.srcs/sources_1/new/ALU1.vhd}
  {C:/Users/Fonea/Desktop/PROIECT SSC/ALU/ALU.srcs/sources_1/new/main.vhd}
}
foreach dcp [get_files -quiet -all *.dcp] {
  set_property used_in_implementation false $dcp
}
read_xdc {{C:/Users/Fonea/Desktop/PROIECT SSC/ALU/ALU.srcs/constrs_1/imports/a is/Nexys4DDR_Master.xdc}}
set_property used_in_implementation false [get_files {{C:/Users/Fonea/Desktop/PROIECT SSC/ALU/ALU.srcs/constrs_1/imports/a is/Nexys4DDR_Master.xdc}}]


synth_design -top main -part xc7a100tcsg324-1


write_checkpoint -force -noxdef main.dcp

catch { report_utilization -file main_utilization_synth.rpt -pb main_utilization_synth.pb }
