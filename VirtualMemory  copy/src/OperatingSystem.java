import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class OperatingSystem {
    static boolean init=false;


    public static void Process() throws Exception{
        Scanner s = new Scanner(new File(Output.testfilepath));
        duplicate();
        TLB.initTLB();
        MMU.initMMU();
        OperatingSystem.InitRAM();
        CPU.PerformInstructions(s);
        Output.end();
    }

    public static void duplicate(){

        String filenames[]=new String[256];
        File dir = new File(Output.pickpath);
        String path=Output.pickpath;
        int index=0;
        for (File file : dir.listFiles()) {
            filenames[index]=file.getName();
            index++;
        }
        (new File(Output.storepath)).mkdir();
        Arrays.sort(filenames);

        for(int i=0;i<256;i++){

            String filename=Output.storepath+filenames[i]+".txt";
            try (Writer bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filename), "utf-8"))) {
                Scanner s = new Scanner(new File(path+filenames[i]));
                int startingindex=0;
                while(s.hasNextLine()){
                    bw.write(s.nextLine()+"\n");
                }

                // no need to close it.
                bw.close();
                s.close();

            } catch (IOException e) {

                e.printStackTrace();

            }
        }

    }

    public static void InitRAM() throws Exception{
        init=true;
        RAM.initRAM();
        String filenames[]=new String[256];
        File dir = new File(Output.storepath);
        String path=Output.storepath;
        int index=0;
        for (File file : dir.listFiles()) {
            filenames[index]=file.getName();
            index++;
        }

        Arrays.sort(filenames);

        int filesindex=0;
        for (index=0;index<RAM.memorysize;index++) {
            Scanner s = new Scanner(new File(path+filenames[filesindex++]));
            while (s.hasNextLine()) {
                String line = s.nextLine();
                MMU.writetoRAM(converttohex(index),Integer.parseInt(line),init);
                index++;
            }
            index--;
            s.close();
        }
        init=false;
    }

    public static String converttohex(int val){
        String hexval=Integer.toHexString(val);
        int padsize=4-hexval.length();
        String empty="";
        for(int k=0;k<padsize;k++){
            empty+="0";
        }
        empty+=hexval;
        return empty;
    }
    public static boolean checkdirty(){
        boolean wasdirty=false;
        for(int i=0;i<256;i++){
            if(MMU.PageTable[i].d==true){
                wasdirty=true;
                MMU.writetodisk(MMU.PageTable[i].pagelocation,(converttohex(i).substring(2,4)).toUpperCase()+".pg.txt");
                MMU.PageTable[i].d=false;
                for(int j=0;j<8;j++){
                    if(TLB.VA[j]==i){
                        TLB.mapping[j].d=false;
                    }
                }
            }
        }
        return wasdirty;
    }


}
