	.text
.globl t_main
t_main:
t_main_framesize=88
sub $sp,t_main_framesize
sw $ra,-12+t_main_framesize($sp)
sw $s0,-16+t_main_framesize($sp)
sw $s1,-20+t_main_framesize($sp)
sw $s2,-24+t_main_framesize($sp)
sw $s3,-28+t_main_framesize($sp)
sw $s4,-32+t_main_framesize($sp)
sw $s5,-36+t_main_framesize($sp)
sw $s6,-40+t_main_framesize($sp)
sw $s7,-44+t_main_framesize($sp)
sw $s8,-48+t_main_framesize($sp)

L2:
addu $t0,$sp,t_main_framesize
sw $a0,0($t0)
addu $t1,$sp,t_main_framesize
addi $t0,$0,10
sw $t0,-4($t1)
addu $t1,$sp,t_main_framesize
la $t0,L0
sw $t0,-8($t1)
addu $t0,$sp,t_main_framesize
lw $t1,-8($t0)
move $a0,$t1
la $t0,print
jal print
j L1
L1:

lw $ra,-12+t_main_framesize($sp)
lw $s0,-16+t_main_framesize($sp)
lw $s1,-20+t_main_framesize($sp)
lw $s2,-24+t_main_framesize($sp)
lw $s3,-28+t_main_framesize($sp)
lw $s4,-32+t_main_framesize($sp)
lw $s5,-36+t_main_framesize($sp)
lw $s6,-40+t_main_framesize($sp)
lw $s7,-44+t_main_framesize($sp)
lw $s8,-48+t_main_framesize($sp)
addu $sp,t_main_framesize

jr $ra

.end t_main
.data
L0:.word 5
	.asciiz	"\"ABC\""
