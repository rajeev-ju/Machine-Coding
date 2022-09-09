package model;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class ScheduledTask {
    private final Runnable runnable;
    private final int taskType;
    private final Long period;
    private final Long delay;
    private final TimeUnit timeUnit;
    private Long scheduledTime;

    public ScheduledTask(Runnable runnable, int taskType, Long period, Long delay, TimeUnit timeUnit) {
        this.runnable = runnable;
        this.taskType = taskType;
        this.period = period;
        this.delay = delay;
        this.timeUnit = timeUnit;
    }
}
