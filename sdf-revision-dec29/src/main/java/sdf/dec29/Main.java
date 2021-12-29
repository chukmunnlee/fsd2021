package sdf.dec29;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        final ExecutorService svc = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            String text = "[%d]".formatted(i);
            svc.submit(() -> {
                String thrName = Thread.currentThread().getName();
                System.out.printf("Thread name: %s, text: %s\n", thrName, text);
            });
            //text = "after submitting the thread";
        }

        svc.shutdown();
    }
    
}
