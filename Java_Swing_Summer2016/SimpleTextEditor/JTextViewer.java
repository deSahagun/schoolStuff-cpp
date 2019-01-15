
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import java.awt.*;   
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alex
 */
public class JTextViewer implements ActionListener{

    JFrame frame = new JFrame("JTextArea Demo");
    JTextArea textarea = new JTextArea();

    JTextViewer(String fileName)
    {
  
        textarea.setFont(new Font("Courier", Font.BOLD, 14));

        JScrollPane scrollability = new JScrollPane(textarea);  
        frame.add(scrollability);

	//create the menu Bar
	JMenuBar jmb = new JMenuBar();

	//create the file system
	JMenu jmFile = new JMenu("File");
	jmFile.setMnemonic(KeyEvent.VK_F);
	

	//add items
	JMenuItem jmiOpen = new JMenuItem("Open", KeyEvent.VK_O);
        jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        
   
	jmFile.add(jmiOpen);
	
        JMenu jmFormat = new JMenu("format"); //this is the menu     
        JMenu jmiColorScheme = new JMenu("Color Scheme"); //this will be a subMenu
       
        jmFormat.add(jmiColorScheme);
                
        jmiColorScheme.addSeparator();
        JMenuItem jmiBlackWhite = new JMenuItem("Text White/Black Background");
        JMenuItem jmiWhiteBlue = new JMenuItem("Text White/Blue Background");
        
        jmiColorScheme.add(jmiBlackWhite);
        jmiColorScheme.add(jmiWhiteBlue);
        
        
        JMenu jmExit = new JMenu("Exit");
       	jmExit.setMnemonic(KeyEvent.VK_E);
        
        JMenuItem jmiExit = new JMenuItem("Exit", KeyEvent.VK_E); 
        jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));    

        JMenu jmAbout = new JMenu("About");
       	jmAbout.setMnemonic(KeyEvent.VK_A);
        
        JMenuItem jmiAbout = new JMenuItem("About", KeyEvent.VK_A);
        jmiAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));    

        
        jmAbout.add(jmiAbout);
        jmExit.add(jmiExit);
        
        
        //add it to the menu system...
	jmb.add(jmFile);
        jmb.add(jmFormat);
        jmb.add(jmExit);
        jmb.add(jmAbout);
        
        //add the menu to the frame
	 frame.setJMenuBar(jmb);    


	frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);



        if (fileName != null) {
            FileReader reader = null;
            try {
                try {
                    reader = new FileReader(fileName);
                } catch (FileNotFoundException ex) {
                    //not found...
                }
                try 
		{
		    textarea.read(reader, null);
		    textarea.append(fileName);    
                } 
                catch (IOException ex) {
                    //error reading...
                }
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ex) {
                        //error closing...
                    }
                }
            }
        }
        
         jmiBlackWhite.addActionListener(this);
         jmiWhiteBlue.addActionListener(this);
         jmiAbout.addActionListener(this);
         jmiOpen.addActionListener(this);
         jmiExit.addActionListener(this);
        
    }  
    
  
       // Handle menu item action events. 
  public void actionPerformed(ActionEvent ae) 
  {  
    // Get the action command from the menu selection. 
        String comStr = ae.getActionCommand(); //leave this
 
        if(comStr.equals("Open"))
        {
            System.out.println("Are you trying to open something...");            
            frame.setVisible(false); 
             PopupDialog pU = new PopupDialog();
        }
        
        else if(comStr.equals("Exit"))
        {
            System.out.println("Are you trying to Exit?????");
            System.exit(0);
        }
        
        else if(comStr.equals("About"))
        {
            JAbout bout = new JAbout(frame);
            
        }
        
         else if(comStr.equals("Text White/Blue Background"))
        {
         
            textarea.setBackground(Color.blue);
            textarea.setForeground(Color.WHITE);
            
            
            System.out.println("text white??");

        }
        
         else if(comStr.equals("Text White/Black Background"))
        {
             textarea.setBackground(Color.BLACK);
             textarea.setForeground(Color.WHITE);
            
            System.out.println("text black?");

        }
        
   
  } 

    
    
  //this is my pop class 
  //since i set the other frame to false there is no need to make it modall, although i can 
    private class PopupDialog
    {
        PopupDialog() //this is the constructor 
        {
            //what will trigger this??
            JDialog dialog = new JDialog(); 
            JFileChooser chooser = new JFileChooser();
            
            dialog.add(chooser);   
            dialog.setSize(640, 480);
            dialog.setVisible(true);
            
            chooser.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e) 
                {
                    System.out.println("Action");
                    
                    File file = chooser.getSelectedFile();
           
            
                   try //have to creat a text area!
                   {
                    
                     FileReader j;
                     JTextViewer load = new JTextViewer(file.getAbsolutePath());
                     dialog.setVisible(false);
                     dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                     
                     j = new FileReader(file.getAbsolutePath());
          
                    } 
        
                    catch(IOException ev) 
                   {
                        System.out.println("problem accessing file"+file.getAbsolutePath());
                   }
    
                  
                }
            
            
            });
            
        }
    
    
        private void jFileChooser1ActionPerformed(ActionEvent evt) 
        { 
            System.out.println("made it in here");
                
                
        }
    
    
    }
     
    class JAbout
    {
        
        JAbout(JFrame frame)
        {
           JDialog dialogAbout = new JDialog(frame, "About JTextViewer"); //set it up with its owner 
            
            
            //could use html
            JLabel labes = new JLabel("JTextViewer");
            JLabel labes2 = new JLabel("Version .1 Beta");
            JLabel labes3 = new JLabel("(c) Alejandro Sahagun");
            
            JPanel j = new JPanel(new FlowLayout());
            
            j.add(labes);
            j.add(labes2);
            j.add(labes3);
            
            dialogAbout.add(j);
            
            
            
            dialogAbout.setSize(200, 200);
            dialogAbout.setVisible(true);
            dialogAbout.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
           // j = JPanel(new flowLayout()); 
        }
        
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
         SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JTextViewer(args.length > 0 ? args[0] : null);
            }
        });
        
        
    }
    
}
