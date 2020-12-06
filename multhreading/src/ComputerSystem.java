import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ComputerSystem {

    private final int INITIAL_PROCESS_COUNT = 50;
    private final int MAX_NEW_PROCESSES_COUNT = 80;
    private final int MAX_PROCESS_COUNT = 100;
    private final int RESOURCE_COUNT = 50;  // Must be equal or greater than the largest requirement from a single process.
    private final int PROCESSOR_COUNT = 8;
    
    private final ProcessStack processes;
    private final ResourceStack resources;
    private final ProcessorPool processors;
    
    private final NewProcessesActionListener newProcessesActionListener = new NewProcessesActionListener();
    private final Timer newProcessesTimer = new Timer(1000, newProcessesActionListener);
    
    private boolean shutdownFlag = false;
    
    static ComputerSystem computerSystem = new ComputerSystem();
    
    // Constructor.
    public ComputerSystem() {
        processes = new ProcessStack(INITIAL_PROCESS_COUNT);
        resources = new ResourceStack(RESOURCE_COUNT);
        processors = new ProcessorPool(PROCESSOR_COUNT, processes, resources);
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("Processs in process stack: " + computerSystem.processes.getSize() + " (max. stack size is " + computerSystem.MAX_PROCESS_COUNT + ").");
        System.out.println("Resources in resources stack: " + computerSystem.resources.getSize() + " out of " + computerSystem.RESOURCE_COUNT + ".\n");

        computerSystem.processors.start();
        
        computerSystem.newProcessesTimer.start();
                
        while(computerSystem.shutdownFlag == false) {
            Thread.sleep(100);  // To allow other threads to carry on.
        }
        
        // Once shutdown is initiated wait until all processes are completed and processors are finished.
        while((computerSystem.processors.busyProcessorsCount() > 0) || (computerSystem.processes.getSize() > 0)) {
            Thread.sleep(100);  // To allow other threads to carry on.
        }
        
        computerSystem.processors.printProcessRecords();
        
        System.out.println("\nProcesss in process stack: " + computerSystem.processes.getSize() + " (max. stack size is " + computerSystem.MAX_PROCESS_COUNT + ").");
        System.out.println("Resources in resources stack: " + computerSystem.resources.getSize() + " out of " + computerSystem.RESOURCE_COUNT + ".");
        
        System.exit(0);
        
    }
    
    
    public class NewProcessesActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            
            Random rn = new Random();
            int newProcessesCount = rn.nextInt(MAX_NEW_PROCESSES_COUNT)+1;

            System.out.println("\nNew processes to be added to the stack: " + newProcessesCount);
            System.out.println("Current stack size: " + computerSystem.processes.getSize() + " (max. stack size is " + computerSystem.MAX_PROCESS_COUNT + ").");

            if((processes.getSize() + newProcessesCount) > MAX_PROCESS_COUNT) {
                System.out.println("Process stack overflow! Initiating controlled shutdown.\n");
                newProcessesTimer.stop();
                shutdownFlag = true;
            } else {
                processes.push(newProcessesCount);
                System.out.println("New stack size: " + computerSystem.processes.getSize() + " (max. stack size is " + computerSystem.MAX_PROCESS_COUNT + ").\n");
            }
        }
    }

}