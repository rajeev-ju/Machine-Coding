package driver;

import service.SchedulerService;

import java.util.concurrent.TimeUnit;

public class JobScheduler {

    public static void main(String[] args) {
        SchedulerService schedulerService = SchedulerService.getInstance();
        Runnable task1 = () -> System.out.println("Running Task 1(onetime)");
        Runnable task2 = () -> System.out.println("Running Task 2(onetime)");

        schedulerService.schedule(task1, 10, TimeUnit.SECONDS);
        schedulerService.schedule(task2, 5, TimeUnit.SECONDS);

        Runnable task3 = () -> System.out.println("Running Task 3(at fixed rate)");
        schedulerService.scheduleAtFixedRate(task3, 3, 2, TimeUnit.SECONDS);

        Runnable task4 = () -> {
            System.out.println("Running Task 4(with 1second processing delay)");
            try {
                Thread.sleep(200);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        };
        schedulerService.scheduleWithFixedDelay(task4, 1, 3, TimeUnit.SECONDS);

    }
}
