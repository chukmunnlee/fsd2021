package sdf.dec29;

import java.io.File;

public class ListFiles {

    public static void main(String[] args) {

        File dir = new File("/opt/tmp/k8s-stuff");

            // Not using type inferencing
            File[] yamls = dir.listFiles(
                (File f) -> {
                    System.out.println(">>> in listFiles: " + f.getAbsolutePath());
                    return f.getName().endsWith(".yaml");
                }
            );
        // Leverage type inferencing
        /*
        File[] yamls = dir.listFiles(
            (f) -> f.getName().endsWith(".yaml")
        );
        */

        for (File f: yamls)
            System.out.printf("%s\n", f.getName());

    }
    
}
