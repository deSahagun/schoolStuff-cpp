
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Output{
    static StringBuilder sb = new StringBuilder();
    static String pickpath="Project2_test_and_page_files/page_files/";
    static String storepath="Simulation/";
    static String testfilepath="/home/hassan/IdeaProjects/untitled/Project2_test_and_page_files/test_files/test_2.txt";
    public static void output() throws Exception{

        sb.append("Address");
        sb.append(',');
        sb.append("Read/Write");
        sb.append(",");
        sb.append("Soft Miss");
        sb.append(",");
        sb.append("Hard Miss");
        sb.append(",");
        sb.append("A Hit");
        sb.append(",");
        sb.append("Dirty Bit on Page Fault");
        sb.append('\n');


    }

    public static void adddata(String add,int rw,int soft,int hard,int hit,int dirty){

        sb.append(add);
        sb.append(',');
        sb.append(rw);
        sb.append(",");
        sb.append(soft);
        sb.append(",");
        sb.append(hard);
        sb.append(",");
        sb.append(hit);
        sb.append(",");
        sb.append(dirty);
        sb.append('\n');
    }

    public static void end()throws Exception{
        PrintWriter pw = new PrintWriter(new File("output.csv"));

        pw.write(sb.toString());
        pw.close();
        System.out.println("Simulator Execution Completed!");
    }
}
