// The orchestrator

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Chronos {

    private final ExecutorService executor;
    private final PriorityQueue<ScheduledJob> JobQueue
            = new PriorityQueue<>(
                    Comparator.comparing(ScheduledJob::getExecutionDateTime)
                            .thenComparing(sj -> sj.getJob().getPriority().getValue())
    );

    public Chronos() {
        this.executor = Executors.newFixedThreadPool(4);
    }

    public void addJob(ScheduledJob sj) {
            if (sj.getJob().getId()!=null)
                JobQueue.add(sj);
    }


    public ScheduledJob next() {
        return  JobQueue.poll();
    }


    public void shutdown() {
        executor.shutdown();
    }
}