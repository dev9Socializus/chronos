import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public class ScheduledJob {

    @Getter
    private final Job job;
    private LocalDateTime executionDateTime;

    ScheduledJob(Job job){
        this.job = job;
    }

    public LocalDateTime getExecutionDateTime()
    {
        ScheduleResponse sch = job.getSchedule().nextExecution(LocalDateTime.now());
        return  sch.getNextDateTime();
    }
}