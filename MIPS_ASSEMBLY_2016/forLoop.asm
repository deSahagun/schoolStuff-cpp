#	.globl main 


	.text 		


main:
	        li $s2, 0 #this will be variable j		
            li $s3, 0 #load it with zero but then add ten
            li $s1, 0 #this is the value for b 
            addi $s3, $s3, 10


Loop:
            beq $s2, $s3, exit
                add $s1, $s1, $s2 #b = b+j
                addi $s2, $s2, 1 # j = j+1
                j Loop

exit:                 
            j exit #jump out of exit


    
            li $v0, 10 
            syscall 


	.data

	
value:	.word 12



