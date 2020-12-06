public class Process {

    private final int id;
    private final int timeToComplete;  // Time required to run and complete the process (milliseconds).
    private final int resourceRequirement;  // Number of resources required to run the process.
            
    public Process(int theId, int theTimeToComplete, int theResourceRequirement) {
        id = theId;
        resourceRequirement = theResourceRequirement;
        timeToComplete = theTimeToComplete;
    }

    public int getId() {
        return id;
    }

    public int getTimeToComplete() {
        return timeToComplete;
    }
    
    public int getResourceRequirement() {
        return resourceRequirement;
    }
    
}
