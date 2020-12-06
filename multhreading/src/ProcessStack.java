import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProcessStack {

    private final int MAX_TIME_REQUIREMENT_PER_PROCESS = 500;  // Milliseconds
    private final int MAX_RESOURCE_COUNT_PER_PROCESS = 10;
    
    private final LinkedList<Process> stack; 
    private int processCount = 0;  // To generate each process' ID.

    private final Lock processStackChangeLock;

    private final Random rn = new Random();
    
    
    // Constructor to create the stack with some initial processes in it.
    public ProcessStack(int size) {
        processStackChangeLock = new ReentrantLock();

        stack = new LinkedList<>();
        for(int i=0; i<size; i++) {
            stack.push(new Process(processCount, (rn.nextInt(MAX_TIME_REQUIREMENT_PER_PROCESS)+1), (rn.nextInt(MAX_RESOURCE_COUNT_PER_PROCESS)+1)));
            processCount++;
        }
    }

    // Will add a number of new processes onto the process stack.
    public void push(int size) {
        processStackChangeLock.lock();
        try {
            for(int i=0; i<size; i++) {
                stack.push(new Process(processCount, (rn.nextInt(MAX_TIME_REQUIREMENT_PER_PROCESS)+1), (rn.nextInt(MAX_RESOURCE_COUNT_PER_PROCESS)+1)));
                processCount++;
            }
        } finally {
            processStackChangeLock.unlock();
        }
    }
    
    // Will return the next process on the stack or null to signal that there are no more processes left on the stack.
    public Process pop() {
        Process returnValue = null;
        processStackChangeLock.lock();
        try {
            if(!stack.isEmpty()) {
                returnValue = stack.pop();
            }
        } finally {
            processStackChangeLock.unlock();
        }
        return returnValue;
    }
    
    public int getSize() {
        int returnValue = 0;
        processStackChangeLock.lock();
        try {
            returnValue = stack.size();
        } finally {
            processStackChangeLock.unlock();
        }
        return returnValue;
    }
    
}
