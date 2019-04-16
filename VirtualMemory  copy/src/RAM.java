import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class RAM {


    static int totalpages=16;
    static int pagesize=256;
    static boolean replaceblock[]=new boolean[16];
    public static int memorysize=totalpages*pagesize;
    static byte [][]memorygrid=new byte[memorysize][1];
    static int currentindex=0;

    public static void initRAM(){
        for(int i=0;i<16;i++){
            replaceblock[i]=true;
        }
    }
    public static int read(int location){
        return (memorygrid[location][0] & 0xFF);
    }

    public static int write(int location, int val){
        try{
            memorygrid[location][0]=(byte)val;
            return location/256;//will return block
        }
        catch(Exception e){
            return -1;
        }
    }

    public static PageEntry fetchfromdisk(String address) throws Exception{

        int startingindex=clockreplacement();
        String path=Output.storepath;
        Scanner s = new Scanner(new File(path+address.substring(0,2)+".pg.txt"));
        //System.out.println("Reading from file"+address.substring(0,2));
        while (s.hasNextLine()) {
            String line = s.nextLine();
            write(startingindex++,Integer.parseInt(line));
        }
        s.close();
        return new PageEntry(true,0,false,(startingindex/256)-1);
    }
    static int clockreplacement(){
        //will check if a new block can be placed in RAM and will return starting value

        while(true){
            if(replaceblock[currentindex]==false){
                replaceblock[currentindex]=true;
                return currentindex*pagesize;
            }
            else{
                replaceblock[currentindex]=false;
                currentindex++;
                if(currentindex>=16){
                    currentindex=0;
                }
            }
        }
    }
}
