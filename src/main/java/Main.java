import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) {
        final Chronos chronos = new Chronos();


        Callable<JobResult> yessir = new Callable<JobResult>() {
            @Override
            public JobResult call() throws Exception {
                System.out.println("YESSIR");
                return new JobResult();
            }
        };

        Callable<JobResult> printer = new Callable<JobResult>() {
            @Override
            public JobResult call() throws Exception {
                System.out.println("HELLO WORLD");
                return new JobResult();
            }
        };

        Job job1 = Job.builder()
                .id("S0001")
                .name("Print hello world")
                .schedule(new OneTimeSchedule(LocalDateTime.of(LocalDate.now(), LocalTime.of(20,12))))
                .action(printer)
                .priority(Priority.LOW)
                .timeOut(Duration.ofMillis(5000))
                .build();


        Job job2 = Job.builder()
                .id("S0002")
                .name("Print YESSIR")
                .schedule(new OneTimeSchedule(LocalDateTime.of(LocalDate.now(), LocalTime.of(20,12))))
                .action(yessir)
                .timeOut(Duration.ofMillis(5000))
                .priority(Priority.HIGH)
                .build();


        chronos.addJob(new ScheduledJob(job1));
        chronos.addJob(new ScheduledJob(job2));


        System.out.println("The next to be executed is: \n" +
                chronos.next().getJob());

    }

}
