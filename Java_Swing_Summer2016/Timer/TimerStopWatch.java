// This version of the stopwatch uses the Timer class. 
 
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.util.Calendar; 
  
class TimerStopWatch { 
 
  JLabel jlab; // display the elapsed time 
 
  JButton jbtnStart; // start the stopwatch 
  JButton jbtnStop;  // stop the stopwatch 
 
  long start; // holds the start time in milliseconds 
 
  Timer swTimer; // the timer for the stopwatch 
 
  TimerStopWatch() { 
 
    // Create a new JFrame container. 
    JFrame jfrm = new JFrame("Timer-based Stopwatch"); 
 
    // Specify FlowLayout for the layout manager. 
    jfrm.getContentPane().setLayout(new FlowLayout()); 
 
    // Give the frame an initial size. 
    jfrm.setSize(230, 90); 
 
    // Terminate the program when the user closes the application. 
    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
 
    // Create the elapsed-time label. 
    jlab = new JLabel("Press Start to begin timing."); 
 
    // Make the Start and Stop buttons. 
    jbtnStart = new JButton("Start"); 
    jbtnStop = new JButton("Stop"); 
    jbtnStop.setEnabled(false); 
 
    // This action listener is called when the timer 
    // goes off. 
    ActionListener timerAL = new ActionListener() { 
      public void actionPerformed(ActionEvent ae) { 
        updateTime(); 
      } 
    }; 
 
    // Create a timer that goes off every tenth of a second. 
    swTimer = new Timer(100, timerAL); 
 
    // Add the action listeners for the start and 
    // stop buttons. 
    jbtnStart.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent ae) { 
 
        // Store start time. 
        start = Calendar.getInstance().getTimeInMillis(); 
 
        // Reverse the state of the buttons. 
        jbtnStop.setEnabled(true); 
        jbtnStart.setEnabled(false); 
 
        // Start the stopwatch. 
        swTimer.start(); 
      } 
    }); 
 
    jbtnStop.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent ae) { 
        long stop = Calendar.getInstance().getTimeInMillis(); 
 
        // Compute the elapsed time. 
        jlab.setText("Elapsed time is " 
             + (double) (stop - start)/1000); 
 
        // Reverse the state of the buttons. 
        jbtnStart.setEnabled(true); 
        jbtnStop.setEnabled(false); 
 
        // Stop the stopwatch. 
        swTimer.stop(); 
      } 
    }); 
 
    // Add the buttons and label to the content pane. 
    jfrm.getContentPane().add(jbtnStart);  
    jfrm.getContentPane().add(jbtnStop);  
    jfrm.getContentPane().add(jlab); 
 
    // Display the frame. 
    jfrm.setVisible(true); 
  } 
 
  // Update the elapsed time display. Notice 
  // that the running variable is no longer  
  // needed. 
  void updateTime() { 
    long temp = Calendar.getInstance().getTimeInMillis(); 
    jlab.setText("Elapsed time is " + 
                 (double) (temp - start)/1000); 
  } 
 
  public static void main(String args[]) { 
    // Create the frame on the event dispatching thread. 
    SwingUtilities.invokeLater(new Runnable() { 
      public void run() { 
        new TimerStopWatch(); 
      } 
    }); 
  } 
}
