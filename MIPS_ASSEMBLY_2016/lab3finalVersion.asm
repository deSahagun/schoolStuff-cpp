#this is the safe version incase anything goes wrong come back here

# Who:  Alex Sahagun
# What: lab3.asm
# Why:  A template to be used for all CS264 labs
# When: Created: Tuesday, May 16, 2917? Thursday May18?
# How:  List the uses of registers

#for now just store them on the stack in the subroutine and print them in the main
#

.data
            welcome        :     .asciiz "Welcome this program stores a list in descending order \n"
            prompt         :     .asciiz "Enter the size of your list (please be moderate) \n"
            err_wrongInput :     .asciiz "Wrong input \n"
            enterNum       :     .asciiz "Enter the next number: "
            delimeter      :     .asciiz "  "
            sort_Prompt    :     .asciiz "Your sorted array is: \n"
            adressed       :     .asciiz "adress ["
            backBckt       :     .asciiz "] "
            value          :     .asciiz "value "
            here           :     .asciiz "here: " #used for debugging
.text
.globl main


main:
            la $a0, welcome
            li $v0, 4
            syscall

            la $a0, prompt
            li $v0, 4
            syscall 

            #get the value of n
            li $v0, 5
            syscall

            
            move $a0, $v0 #store the value of $v0 inside $a0 for jal
            move $t7, $a0 #value of n
            move $s1, $t7 
            addi $t6, $zero, 0

            addiu $t4, $sp, -4   #

            sll $s0, $t7, 2 #shift left logical multiplies the value  $s0 = n*4
            
            move $s2, $sp #put the value of stak pointer into $s2
           # addi $t5, $sp, -4 

            addi $t0, $zero, 0 #remove this in a bit

getInput:  #this loop is working the way it should
            #while 

            beq  $t6, $s1, printArray
               
                addi $t6, $t6, 1    
                
                la $a0, enterNum
                li $v0, 4
                syscall

                li $v0, 5
                syscall

                #move stuff into the argument registers (like parameters)
                move $a0, $v0 #move data input into a register 
                move $a1, $t7  #move the value of n 
              #  move $a2, $t5  #this value should not change
                
                jal subRoute

                j getInput



 printArray:           
                lw $a0, 0($sp)      # read input from stack
                li $v0, 1
                syscall

                la $a0, delimiter   # print delimiter
                li $v0, 4
                syscall

                addiu $t0, $t0, 1   # increment loop counter
                addiu $sp, $sp, 4   # increment stack pointer

                blt $t0, $t7, printArray


exit:
li $v0, 10		# terminate the program
syscall         # end here 



.data 

        delimiter:  .asciiz ", "
.text

#handles the insertion of the input and shifts it into right stack slot
subRoute:  
            addiu $sp, $sp, -4
            sw $a0, 0($sp) #Store the word inside a0 into stackpointer location

            move $t5, $sp

            whileL:
                    beq $t4, $sp , placeB #While they aren't equal do this loop 

                        lw $t1, 0($sp) #load a word from adress into a reg 
                        addiu $sp, $sp, 4
                        lw $t2, 0($sp) #set the pointer ahead


                        blt $t1, $t2, whileL    #branch if greater than else do the body
                            sw $t1, 0($sp)
                            sw $t2, -4($sp)
        
                        bne $t4, $sp, whileL
                            j exitLoop 

                    #remeber to decrement stack pointer

placeB:
                move $sp, $t5

exitLoop:       
                #exit the subroutine 
                jr $ra                # return