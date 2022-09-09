package model;

import lombok.Getter;
import lombok.Setter;
import service.SchedulerService;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class Job implements Runnable, Comparable<Job>{

    private final int jobId;
    private final Runnable task;
    private final Date startTime;
    private final long reschedulePeriod;
    private final TimeUnit timeUnit;
    private final TaskType taskType;


    public Job(int jobId, Runnable task, Date startTime) {
        this(jobId, task, startTime, -1, TimeUnit.SECONDS, TaskType.ONCE);
    }


    public Job(int jobId, Runnable task, Date startTime, long period, TimeUnit unit, TaskType taskType) {
        this.jobId = jobId;
        this.task = task;
        this.startTime = startTime;
        this.reschedulePeriod = period;
        this.timeUnit = unit;
        this.taskType = taskType;
    }

    @Override
    public void run(){
        if(TaskType.REPEAT_FIXED_RATE.equals(taskType)){
            SchedulerService.getInstance().scheduleAtFixedRate(this.task, reschedulePeriod, reschedulePeriod, timeUnit);
        }
        System.out.printf("Running main.entity.Job : %s at time %d%n", jobId, Calendar.getInstance().get(Calendar.SECOND));
        task.run();

        if(TaskType.REPEAT_FIXED_DELAY.equals(taskType)){
            SchedulerService.getInstance().scheduleWithFixedDelay(this.task, reschedulePeriod, reschedulePeriod, timeUnit);
        }
    }

    @Override
    public int compareTo(Job ohterJob){
        return this.startTime.compareTo(ohterJob.getStartTime());
    }
}
