public class TLB {
    static int VA[]=new int[8];
    static PageEntry mapping[]=new PageEntry[8];
    static int fifoindex[]=new int[8];
    static int currentindex=0;

    public static void initTLB(){
        for(int i=0;i<8;i++){
            VA[i]=-1;
        }
    }

    public static void write(String address,int val)throws Exception{
        int pageval=Integer.valueOf(address.substring(0,2),16);
        for(int i=0;i<8;i++){
            if(pageval==VA[i]){
                mapping[i].d=true;//marking dirty bit
                Output.adddata(address,1,0,0,1,1);
                MMU.writetoRAM(address,val,false);//confusion, might be pee.location rather than address
            }
        }
        PageEntry pee=MMU.read(address);
        storeinTLB(pageval,pee);
        MMU.writetoRAM(address,val,false);
    }

    public static int get(String address)throws Exception{
        int pageval=Integer.valueOf(address.substring(0,2),16);
        for(int i=0;i<8;i++){
            if(pageval==VA[i]){
                Output.adddata(address,0,0,0,1,0);
                return RAM.read((mapping[i].pagelocation*256)+(Integer.valueOf(address.substring(2,4),16)));
            }
        }
        PageEntry pee=MMU.read(address);
        storeinTLB(pageval,pee);
        return RAM.read((pee.pagelocation*256)+(Integer.valueOf(address.substring(2,4),16)));
    }
    public static void storeinTLB(int address,PageEntry pe){    //Implementing FIFO
        if(currentindex!=7){
            VA[currentindex]=address;
            mapping[currentindex]=pe;
            fifoindex[currentindex]=currentindex;
            currentindex++;
        }
        else{

            for(int i=0;i<8;i++){
                if(fifoindex[i]==0){
                    fifoindex[i]=8;
                    mapping[i]=pe;
                    VA[i]=address;
                }
                fifoindex[i]--;
            }

        }
    }
}
