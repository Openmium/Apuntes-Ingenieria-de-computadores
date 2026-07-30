vlib modelsim_lib/work
vlib modelsim_lib/msim

vlib modelsim_lib/msim/xil_defaultlib

vmap xil_defaultlib modelsim_lib/msim/xil_defaultlib

vcom -work xil_defaultlib  -93  \
"../../../Practica6_3.gen/sources_1/ip/xadc_wiz_0/xadc_wiz_0.vhd" \
"../../../Practica6_3.srcs/sources_1/new/Voltage.vhd" \


