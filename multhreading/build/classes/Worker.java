
import java.util.ArrayList;

import java.util.Map;

import java.util.TreeMap;

public class Worker implements Runnable {

    private final int id;  // Unique worker ID.

    private final JobStack jobStack;  // Reference to the job stack.
    private final ResourceStack resourceStack;  // Reference to the resource stack.

    private Job job;  // Job being processed.
    private Resource[] resources;  // Resources being used for job being processed.

    private boolean busy;  // Indicates the status of the worker. True when they are working (executing jobs) and false when there are no more jobs left to execute.

    private final Map<Integer, ArrayList<Integer>> jobsCompleted;  // The job record of the worker. Stores each job's ID and the IDs of the resources used for each job.

    // Constructor.
    public Worker(int theId, JobStack theJobStack, ResourceStack theResourceStack) {
        id = theId;
        jobStack = theJobStack;
        resourceStack = theResourceStack;
        job = null;
        busy = true;
        jobsCompleted = new TreeMap<>();
    }

    /// UNDER CONSTRUCTION /////////////////////////////////////////////////////
    @Override
    public void run() {

        System.out.println("Worker " + id + " Started ");

        while (jobStack.getSize() > 0) {

            try {

                job = jobStack.pop();
                int jobResources = job.getResourceRequirement();
                long timeToComplete = job.getTimeToComplete();
                resources = resourceStack.pop(jobResources);
                resourceStack.push(resources);
                Thread.sleep(timeToComplete);

            } catch (InterruptedException e) {
                System.out.println(e.toString() + " (thread id: " + id + ")");
            }

            System.out.println("Worker " + id + " Completed " + job.getId());
            System.out.println("Worker " + id + " finished.");

            ArrayList<Integer> resourceIDS = new ArrayList<Integer>();

            for (int x = 0; x < resources.length; x++) {
                resourceIDS.add(resources[x].getId());
            }
            
            jobsCompleted.put(job.getId(), resourceIDS);

        }

    }

    public void printResult() {

        System.out.println("Worker" + id + ":");
        for (Integer key : jobsCompleted.keySet()) {
            System.out.print("Job: " + key + " Resources ");
            for (Integer res : jobsCompleted.get(key)) {
                System.out.print(res + ", ");
            }
            System.out.println();

        }

    }

}
