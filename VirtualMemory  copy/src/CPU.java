import java.util.Scanner;

public class CPU {
    static boolean init=false;

    public static void PerformInstructions(Scanner s)throws Exception{
        while(s.hasNextLine()){
            int code=Integer.parseInt(s.nextLine());
            if(code==0){
                String readaddress=s.nextLine();
                TLB.get(readaddress);
                //System.out.println(TLB.get(readaddress));
            }
            else if(code==1){
                TLB.write(s.nextLine(),Integer.valueOf(s.nextLine()));
            }
        }
    }
}
