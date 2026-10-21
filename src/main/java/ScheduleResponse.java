import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ScheduleResponse {
    private LocalDateTime nextDateTime;
    private SCHEDULE_STATUS status;
}
