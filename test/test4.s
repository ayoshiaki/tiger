	.text
.globl t_main
t_main:
t_main_framesize=80
sub $sp,t_main_framesize
sw $ra,-4+t_main_framesize($sp)
sw $s0,-8+t_main_framesize($sp)
sw $s1,-12+t_main_framesize($sp)
sw $s2,-16+t_main_framesize($sp)
sw $s3,-20+t_main_framesize($sp)
sw $s4,-24+t_main_framesize($sp)
sw $s5,-28+t_main_framesize($sp)
sw $s6,-32+t_main_framesize($sp)
sw $s7,-36+t_main_framesize($sp)
sw $s8,-40+t_main_framesize($sp)

L17:
