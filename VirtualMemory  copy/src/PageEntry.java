public class PageEntry {
    public boolean v;
    public int r;
    public boolean d;
    public int pagelocation;

    public PageEntry(){
        v=false;        //true if it is in RAM
        r=0;            //0 if in RAM else tell the PID
        d=false;        //if the access is write then d is true
        pagelocation=0; //tell the RAM location
    }
    public PageEntry(boolean v1,int r1,boolean d1,int pl){
        v=v1;
        r=r1;
        d=d1;
        pagelocation=pl;
    }
}
