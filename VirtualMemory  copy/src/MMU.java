import java.io.*;

public class MMU {

    static PageEntry PageTable[]=new PageEntry[256];
    static String path=Output.storepath;
    public static void initMMU(){
        for(int i=16;i<256;i++){
            PageTable[i]=new PageEntry(false,-123,false,0);
        }
    }
    public static void writetoRAM(String address,int val,boolean init){


        int location=Integer.valueOf(address,16);
        int block=RAM.write(location,val);
        if(init==false) {
            location=Integer.parseInt(address.substring(0,2),16);//pagelocation
            location = PageTable[location].pagelocation;
            block=RAM.write((location*256)+Integer.valueOf(address.substring(2,4),16),val);
        }


        if(block==-1){
            System.out.println("Error Occurred while writing to RAM! ");
        }
        else{//marking dirty bit
            PageTable[block]=new PageEntry(true,0,!(init),block);//!init means true only when not initializing
        }
    }
    public static PageEntry read(String address)throws Exception{
        int pageval=Integer.valueOf(address.substring(0,2),16);
        if(PageTable[pageval].v==true) {//if it is valid(or in RAM)
            Output.adddata(address,0,1,0,0,0);
            return PageTable[pageval];
        }
        //if not in ram
        else{

            int dval=1;
            if(OperatingSystem.checkdirty()==false){
                dval=0;
            }
            Output.adddata(address,0,1,0,0,dval);

            PageEntry pe=RAM.fetchfromdisk(address);
            for(int i=0;i<256;i++){
                if(PageTable[i].pagelocation==pe.pagelocation){
                    PageTable[i].v=false;
                }
            }
            PageTable[Integer.valueOf(address.substring(0,2),16)]=pe;   //because now it is in ram
            return pe;
        }
        //readfromRAM(location);
    }
    public static void writetodisk(int blocknum,String filename){
        try (Writer bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(path+filename), "utf-8"))) {

            int startingindex=blocknum*256;
            for(int i=0;i<256;i++){
                bw.write(RAM.read(startingindex++)+"\n");
            }
            //System.out.println("Written to disk for filename"+filename);
            // no need to close it.
            bw.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
    public static int readfromRAM(int location){
        return RAM.read(location);
    }
}
