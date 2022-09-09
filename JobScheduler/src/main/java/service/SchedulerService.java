package service;

import helper.JobExecutor;
import model.Job;
import model.TaskType;

import java.util.Calendar;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SchedulerService implements ISchedulerService {
    private static final SchedulerService INSTANCE = new SchedulerService(Runtime.getRuntime().availableProcessors() - 1);
    private final PriorityQueue<Job> jobPriorityQueue;
    private final Lock queueLock;
    private final Condition entryAdded;

    private SchedulerService(int threadCount){
        jobPriorityQueue = new PriorityQueue<Job>();
        queueLock = new ReentrantLock();
        entryAdded = queueLock.newCondition();

        Thread executor = new Thread(new JobExecutor(jobPriorityQueue, queueLock, entryAdded, threadCount));
        executor.start();
    }

    public static SchedulerService getInstance(){
        return INSTANCE;
    }

    private void addJobToQueue(Job job){
        queueLock.lock();
        try {
            jobPriorityQueue.offer(job);
            entryAdded.signal();
        }finally {
            queueLock.unlock();
        }
    }
    @Override
    public void schedule(Runnable task, long initialDelay, TimeUnit unit) {
        Date date = new Date(Calendar.getInstance().getTime().getTime() + unit.toMillis(initialDelay));
        Job job = new Job(UUID.randomUUID().hashCode(), task, date);

        addJobToQueue(job);
    }

    @Override
    public void scheduleAtFixedRate(Runnable task, long initialDelay, long recurringDelay, TimeUnit unit) {
        Date date = new Date(Calendar.getInstance().getTime().getTime() + unit.toMillis(initialDelay));
        Job job = new Job(UUID.randomUUID().hashCode(), task, date, recurringDelay, unit, TaskType.REPEAT_FIXED_RATE);

        addJobToQueue(job);
    }

    @Override
    public void scheduleWithFixedDelay(Runnable task, long initialDelay, long recurringDelay, TimeUnit unit) {
        Date date = new Date(Calendar.getInstance().getTime().getTime() + unit.toMillis(initialDelay));
        Job job = new Job(UUID.randomUUID().hashCode(), task, date, recurringDelay, unit, TaskType.REPEAT_FIXED_DELAY);

        addJobToQueue(job);
    }
}
