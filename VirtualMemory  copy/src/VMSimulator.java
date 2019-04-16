import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class VMSimulator {
    public static void main(String args[])throws Exception{


        String temp=args[0];
        if(temp!=null) {
            Output.testfilepath = temp;
            (new File(Output.pickpath)).mkdir();
            OperatingSystem.Process();
        }
        else{
            System.out.println("Please give the test file path ");
        }

    }
}
