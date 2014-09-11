	.text
.globl t_main
t_main:
t_main_framesize=84
sub $sp,t_main_framesize
sw $ra,-8+t_main_framesize($sp)
sw $s0,-12+t_main_framesize($sp)
sw $s1,-16+t_main_framesize($sp)
sw $s2,-20+t_main_framesize($sp)
sw $s3,-24+t_main_framesize($sp)
sw $s4,-28+t_main_framesize($sp)
sw $s5,-32+t_main_framesize($sp)
sw $s6,-36+t_main_framesize($sp)
sw $s7,-40+t_main_framesize($sp)
sw $s8,-44+t_main_framesize($sp)

L4:
addu $t0,$sp,t_main_framesize
sw $a0,0($t0)
addu $t1,$sp,t_main_framesize
addi $t0,$0,999
sw $t0,-4($t1)
addu $t0,$sp,t_main_framesize
lw $t1,-4($t0)
addi $t0,$0,5
sra $t1,$t1,$t0
addi $t0,$0,0

bne $t1,$t2,L0
L5:
j L0
L1:
addi $t1,$0,999
move $a0,$t1
la $t0,printi
jal printi
L2:
j L3
L0:
addi $t1,$0,666
move $a0,$t1
la $t0,printi
jal printi
j L2
L3:

lw $ra,-8+t_main_framesize($sp)
lw $s0,-12+t_main_framesize($sp)
lw $s1,-16+t_main_framesize($sp)
lw $s2,-20+t_main_framesize($sp)
lw $s3,-24+t_main_framesize($sp)
lw $s4,-28+t_main_framesize($sp)
lw $s5,-32+t_main_framesize($sp)
lw $s6,-36+t_main_framesize($sp)
lw $s7,-40+t_main_framesize($sp)
lw $s8,-44+t_main_framesize($sp)
addu $sp,t_main_framesize

jr $ra

.end t_main
