import lombok.AllArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
public class OneTimeSchedule implements Schedule{

    private final LocalDateTime executionDate;
    @Override
    public ScheduleResponse nextExecution(LocalDateTime from) {
       if (executionDate.isAfter(from))
           return new ScheduleResponse(executionDate,
                   SCHEDULE_STATUS.SCHEDULED);
       return new ScheduleResponse(null,
               SCHEDULE_STATUS.NOT_SCHEDULED);
    }
}
