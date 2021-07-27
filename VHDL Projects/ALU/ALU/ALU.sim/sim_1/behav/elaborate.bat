@echo off
set xv_path=D:\\Vivado\\2016.4\\bin
call %xv_path%/xelab  -wto 116aa2fd4b8f46068a5817415c76db55 -m64 --debug typical --relax --mt 2 -L xil_defaultlib -L secureip --snapshot ALU_TB_behav xil_defaultlib.ALU_TB -log elaborate.log
if "%errorlevel%"=="0" goto SUCCESS
if "%errorlevel%"=="1" goto END
:END
exit 1
:SUCCESS
exit 0
