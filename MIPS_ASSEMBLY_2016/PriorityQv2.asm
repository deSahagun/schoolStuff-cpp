#this is the safe version incase anything goes wrong come back here

# Who:  Alex Sahagun
# What: lab3.asm
# Why:  to get familiar with the Stack and how to 
# When: Created: Tuesday, May 16, 2917? Thursday May18?
# How:  List the uses of registers
        #main:
            # $t7 value of integers in list
            # $t4 counter for print loop 
            # $s1 holds the value of ints as well
            # $s2 holds the value of the stack before it is manipulated 
            # $s0 shifts 
            # $a0 data from user
            # $a1-$a2 have values but i didnt need them in the subroutine

        #subRoutine:
            # $t5 is before(or after) pointer == should be pointing to the value one above stackpointer i called it before
            # $sp = stackpointer
            # $t1 stores value @ 0($t5)
            # $t2 stores value @ 0($sp)
            # small "methods" firstVal(loads the first val and increments the stackpointer), allSorted(once all sorted do a few things), do(the main sorting method)



.data
            welcome        :     .asciiz "Welcome this program stores a list in descending order \n"
            prompt         :     .asciiz "Enter the size of your list (please be moderate) \n"
            err_wrongInput :     .asciiz "Wrong input \n"
            enterNum       :     .asciiz "Enter the next number: "
            delimeter      :     .asciiz "  "
            sort_Prompt    :     .asciiz "Your sorted Ascending list is: "
            sort_Pdes      :     .asciiz "Your sorted descending list is: "
            newLine        :     .asciiz "\n"
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
            addi $t4, $t4, 0 #set this value to 0 

            sll $s0, $t7, 2 #shift left logical multiplies the value  $s0 = n*4            
            move $s2, $sp #put the value of stak pointer into $s2
            addi $t5, $sp, 0 #The before pointer
           # addi $t5, $sp, -4 



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
                move $a2, $t5  #pass in the before pointer
              #  move $a2, $t5  #this value should not change
                
                jal subRoute

                j getInput


printArray:
                #remember $s2 has adress of stack pointer before moving it 
                #$t7 has value of n. So does $s1
                move $sp, $s2

               la $a0, newLine
               li $v0, 4
               syscall

               la $a0, sort_Prompt
               li $v0, 4
               syscall

printLoop:
                beq $t4, $t7, end #end program
                    addi $t4, $t4, 1 #increment the counter 

                    lw $a0, 0($sp)
                    li $v0, 1
                    syscall

                    la $a0, delimeter
                    li $v0, 4
                    syscall

                    addi $sp, $sp, -4

                    j printLoop
                    
end:
li $v0, 10		# terminate the program
syscall         # end here 


.data 

        delimiter:  .asciiz ", " #was not used 
.text


subRoute:
            #stack pointer 
          move $t0, $sp #move the stackpointer to a temp


    do:        
          bgt $t5, $s2, allSorted  #if before pointer is greater than stackpointers top exit  
             beq $t5, $sp, firstVal  #if they are equal it is the first insertion
                #if they arent equal then compare values
                    sw $a0, 0($sp) #store the word into sp

                    lw $t1, 0($t5) #this is the before value in register $t1
                    lw $t2, 0($sp) #this is the one right after in register $t2

            #while $t5 is not greater than $s2
            #this loop will keep going until everything is sorted and the you have reached the top of the loop 
                blt $t1, $t2 , allSorted #if the value in #sp is bigger than swap these around
                    sw $t2, 0($t5) # if $t1 is greater than $t2 swap the values
                    sw $t1, 0($sp) #
                    #increment both to check 
                    addi $t5, $t5, 4
                    addi $sp, $sp, 4

                j do #do it again



firstVal:
            sw $a0, 0($sp)
            addi $sp, $sp, -4 #move it down 
            #now the before pointer will be one adress before
            #move the pointer for stack pointer so that it is one ahead

allSorted:
            
            move $sp, $t0
            addi $sp, $sp, -4 #put the stack pointer where it was before the loop than move it down one more

            move $t5, $t0   #put the before where stackpointer was before the do loop
            j exit


exit:
            jr $ra                # return



