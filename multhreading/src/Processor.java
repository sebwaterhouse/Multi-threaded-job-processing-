
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Processor implements Runnable {

    private final int id;  // Unique processor ID.
    
    private final ProcessStack processStack;  // Reference to the process stack.
    private final ResourceStack resourceStack;  // Reference to the resource stack.
    
    private Process process;  // Process being processed.
    private Resource[] resources;  // Resources being used for process being processed.
    
    private boolean busy = false;  // Indicates the status of the processor. True when they are working (executing processs) and false when there are no more processs left to execute.

    private final Map<Integer, ArrayList<Integer>> processesCompleted;  // The process record of the processor. Stores each process' ID and the IDs of the resources used for that process.

    
    // Constructor.
    public Processor(int theId, ProcessStack theProcessStack, ResourceStack theResourceStack) {
        id = theId;
        processStack = theProcessStack;
        resourceStack = theResourceStack;
        process = null;
        processesCompleted = new TreeMap<>();
    }

    
    /// UNDER CONSTRUCTION /////////////////////////////////////////////////////

    
    @Override
    public void run() {
        ;
    }
    
}
