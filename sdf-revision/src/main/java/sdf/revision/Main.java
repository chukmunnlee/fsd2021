package sdf.revision;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.security.SecureRandom;

public class Main {
    public static void main(String... args) {
        final Random rand = new SecureRandom();
        ExecutorService thrPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            ThrMain mainThr = new ThrMain(i, rand.nextInt(100));
            thrPool.submit(mainThr);
            //Thread thr = new Thread(mainThr);
            //thr.start();
            //mainThr.run();
        }
        System.out.println(">>> shutting down threadpool");
        //thrPool.shutdown();
        List<Runnable> threads = thrPool.shutdownNow();
        System.out.println(">>> threads: " + threads);
    }
    
}
