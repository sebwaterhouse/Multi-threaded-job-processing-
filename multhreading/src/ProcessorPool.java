public class ProcessorPool {

    private final Processor[] pool;  // The processor population.
    private int processorCount = 0;  // Used to generate each processor's ID and to keep a record of the number of processors in the processor pool.

    Thread[] processorThreads;
            
    private final ProcessStack processStack;  // Reference to the process stack.
    private final ResourceStack resourceStack;  // Reference to the resource stack.
    
    // Constructor.
    public ProcessorPool(int size, ProcessStack theProcessStack, ResourceStack theResourceStack) {
        processStack = theProcessStack;
        resourceStack = theResourceStack;

        pool = new Processor[size];
        for(int i=0; i<pool.length; i++) {
            pool[i] = new Processor(processorCount, processStack, resourceStack);
            processorCount++;
        }

        processorThreads = new Thread[pool.length];
        for(int i=0; i<processorThreads.length; i++) {
            processorThreads[i] = new Thread(pool[i]);
        }
    }

    
    /// UNDER CONSTRUCTION /////////////////////////////////////////////////////

    
    // Starts all the processor threads.
    public void start() {
        ;
    }
    
    // Returns the number of busy processors.
    public int busyProcessorsCount() {
        return 0;
    }

    // Prints the job record of all workers.
    public void printProcessRecords() {
        ;
    }
    
}
