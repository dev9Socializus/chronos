import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@Data
public class ProcessKiller {

     private Job[] jobs;
     private final long millisToWait;

     private boolean atLeastOneIsRunning (){

        List<Job> joblist= Arrays.stream(jobs).filter(
                 j -> j.getResult().getStatus()==JobStatus.RUNNING
         ).toList();

         return joblist.isEmpty();
     }

     public void getToWork() throws InterruptedException {

         int round = 0;
         while (atLeastOneIsRunning()){
             for (Job j : jobs){
                 if( (Duration.between(
                         LocalDateTime.now(),
                         j.getResult().getStartPeriod() )
                         .toMillis() >= j.getTimeOut().toMillis()
                 ))
                 {
                     // We notify the chronos with 1
                 }
             }
             ++round;
             if (round<=5){
                 continue;
             }
             Thread.sleep(millisToWait);
         }
     }



}
