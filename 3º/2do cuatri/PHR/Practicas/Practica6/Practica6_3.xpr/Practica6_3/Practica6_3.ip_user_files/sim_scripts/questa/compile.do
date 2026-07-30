vlib questa_lib/work
vlib questa_lib/msim

vlib questa_lib/msim/xil_defaultlib

vmap xil_defaultlib questa_lib/msim/xil_defaultlib

vcom -work xil_defaultlib  -93  \
"../../../Practica6_3.gen/sources_1/ip/xadc_wiz_0/xadc_wiz_0.vhd" \
"../../../Practica6_3.srcs/sources_1/new/Voltage.vhd" \


