# Who:  Me
# What: proj_1a.asm
# Why:  Read ints and print delimited by a new line
# When: 2017-4-25 
# How:  Some info on how I'll use the registers
        # $t0    array size
        # $t1    array starting address
        # $t2    loop counter
        # $t3    array element pointer
.data
arraySz:        .word   20      # size of array (max 20)
initPrompt:     .asciiz "Please enter "
initPrompt2:    .asciiz " integer(s)\n"
readPrompt:     .asciiz "Enter an integer: "
delimiter:      .asciiz "\n"     # delimiter for int output

.text
.globl main

# program entry
main:
        lw $t0, arraySz         # init array control registers


        #**** here i am getting prompting the user **** 
        la $a0 , initPrompt     # display initial msg pt 1
        li $v0 , 4
        syscall



        move $a0, $t0           # display number of ints expected
        li $v0 , 1
        syscall



        la $a0 , initPrompt2    # display initial msg pt 2
        li $v0 , 4
        syscall




# read integers
        move $t2, $zero          #  init loop counter
        #move $t3, $t1           #  init array pointer 
        sll $t3, $t0, 2          #  what does this do??
                                 #  it shifts everything left twice 2^(2)=4, so 20*4=80

        sub $sp, $sp, $t3        #sp
        move $t3, $sp            #



readLoop:   
        la $a0 , readPrompt     # print readPrompt
        li $v0 , 4
        syscall



        li $v0 , 5              # read user input
        syscall

        sw $v0, 0($t3)          # store input in array



        addiu $t2, $t2, 1       # increment loop counter
        addiu $t3, $t3, 4       # increment array pointer

        blt $t2, $t0, readLoop  # if counter > size, loop

# print integers
        move $t2, $zero         # init loop counter
        move $t3, $sp           # init array pointer 



printLoop:   
        lw $a0, 0($t3)          # read input from array at index
        li $v0, 1
        syscall
        
        la $a0, delimiter       # print delimiter
        li $v0, 4
        syscall
        
        addiu $t2, $t2, 1       # increment loop counter
        addiu $t3, $t3, 4       # increment array pointer

        blt $t2, $t0, printLoop # if counter less than 
                                # array size, loop
        addiu $sp, $sp, 80

# terminate the program
li $v0, 10		
syscall