proc start_step { step } {
  set stopFile ".stop.rst"
  if {[file isfile .stop.rst]} {
    puts ""
    puts "*** Halting run - EA reset detected ***"
    puts ""
    puts ""
    return -code error
  }
  set beginFile ".$step.begin.rst"
  set platform "$::tcl_platform(platform)"
  set user "$::tcl_platform(user)"
  set pid [pid]
  set host ""
  if { [string equal $platform unix] } {
    if { [info exist ::env(HOSTNAME)] } {
      set host $::env(HOSTNAME)
    }
  } else {
    if { [info exist ::env(COMPUTERNAME)] } {
      set host $::env(COMPUTERNAME)
    }
  }
  set ch [open $beginFile w]
  puts $ch "<?xml version=\"1.0\"?>"
  puts $ch "<ProcessHandle Version=\"1\" Minor=\"0\">"
  puts $ch "    <Process Command=\".planAhead.\" Owner=\"$user\" Host=\"$host\" Pid=\"$pid\">"
  puts $ch "    </Process>"
  puts $ch "</ProcessHandle>"
  close $ch
}

proc end_step { step } {
  set endFile ".$step.end.rst"
  set ch [open $endFile w]
  close $ch
}

proc step_failed { step } {
  set endFile ".$step.error.rst"
  set ch [open $endFile w]
  close $ch
}

set_msg_config -id {HDL 9-1061} -limit 100000
set_msg_config -id {HDL 9-1654} -limit 100000

start_step init_design
set ACTIVE_STEP init_design
set rc [catch {
  create_msg_db init_design.pb
  set_property design_mode GateLvl [current_fileset]
  set_param project.singleFileAddWarning.threshold 0
  set_property webtalk.parent_dir C:/Users/trian/Desktop/Practica/VGA_on_Fpga/VGA_on_Fpga.cache/wt [current_project]
  set_property parent.project_path C:/Users/trian/Desktop/Practica/VGA_on_Fpga/VGA_on_Fpga.xpr [current_project]
  set_property ip_output_repo C:/Users/trian/Desktop/Practica/VGA_on_Fpga/VGA_on_Fpga.cache/ip [current_project]
  set_property ip_cache_permissions {read write} [current_project]
  set_property XPM_LIBRARIES XPM_MEMORY [current_project]
  add_files -quiet C:/Users/trian/Desktop/Practica/VGA_on_Fpga/VGA_on_Fpga.runs/synth_1/vga_on_fpga.dcp
  add_files -quiet c:/Users/trian/Desktop/Practica/VGA_on_Fpga/VGA_on_Fpga.srcs/sources_1/bd/BRAM2/ip/BRAM2_blk_mem_gen_0_0/BRAM2_blk_mem_gen_0_0.dcp
  set_property netlist_only true [get_files c:/Users/trian/Desktop/Practica/VGA_on_Fpga/VGA_on_Fpga.srcs/sources_1/bd/BRAM2/ip/BRAM2_blk_mem_gen_0_0/BRAM2_blk_mem_gen_0_0.dcp]
  read_xdc C:/Users/trian/Desktop/Practica/VGA_on_Fpga/VGA_on_Fpga.srcs/constrs_1/imports/lab2/Basys3_test_env.xdc
  link_design -top vga_on_fpga -part xc7a100tcsg324-1
  write_hwdef -file vga_on_fpga.hwdef
  close_msg_db -file init_design.pb
} RESULT]
if {$rc} {
  step_failed init_design
  return -code error $RESULT
} else {
  end_step init_design
  unset ACTIVE_STEP 
}

start_step opt_design
set ACTIVE_STEP opt_design
set rc [catch {
  create_msg_db opt_design.pb
  opt_design 
  write_checkpoint -force vga_on_fpga_opt.dcp
  catch { report_drc -file vga_on_fpga_drc_opted.rpt }
  close_msg_db -file opt_design.pb
} RESULT]
if {$rc} {
  step_failed opt_design
  return -code error $RESULT
} else {
  end_step opt_design
  unset ACTIVE_STEP 
}

start_step place_design
set ACTIVE_STEP place_design
set rc [catch {
  create_msg_db place_design.pb
  implement_debug_core 
  place_design 
  write_checkpoint -force vga_on_fpga_placed.dcp
  catch { report_io -file vga_on_fpga_io_placed.rpt }
  catch { report_utilization -file vga_on_fpga_utilization_placed.rpt -pb vga_on_fpga_utilization_placed.pb }
  catch { report_control_sets -verbose -file vga_on_fpga_control_sets_placed.rpt }
  close_msg_db -file place_design.pb
} RESULT]
if {$rc} {
  step_failed place_design
  return -code error $RESULT
} else {
  end_step place_design
  unset ACTIVE_STEP 
}

start_step route_design
set ACTIVE_STEP route_design
set rc [catch {
  create_msg_db route_design.pb
  route_design 
  write_checkpoint -force vga_on_fpga_routed.dcp
  catch { report_drc -file vga_on_fpga_drc_routed.rpt -pb vga_on_fpga_drc_routed.pb -rpx vga_on_fpga_drc_routed.rpx }
  catch { report_methodology -file vga_on_fpga_methodology_drc_routed.rpt -rpx vga_on_fpga_methodology_drc_routed.rpx }
  catch { report_timing_summary -warn_on_violation -max_paths 10 -file vga_on_fpga_timing_summary_routed.rpt -rpx vga_on_fpga_timing_summary_routed.rpx }
  catch { report_power -file vga_on_fpga_power_routed.rpt -pb vga_on_fpga_power_summary_routed.pb -rpx vga_on_fpga_power_routed.rpx }
  catch { report_route_status -file vga_on_fpga_route_status.rpt -pb vga_on_fpga_route_status.pb }
  catch { report_clock_utilization -file vga_on_fpga_clock_utilization_routed.rpt }
  close_msg_db -file route_design.pb
} RESULT]
if {$rc} {
  write_checkpoint -force vga_on_fpga_routed_error.dcp
  step_failed route_design
  return -code error $RESULT
} else {
  end_step route_design
  unset ACTIVE_STEP 
}

