package sdf.dec29;

import java.io.File;

public class ListFiles3 {

    public static void main(String[] args) {

        File dir = new File("/opt/tmp/k8s-stuff");

        File[] yamls = dir.listFiles( CheckSuffix::isInDirectory );

        for (File f: yamls)
            System.out.printf("%s\n", f.getName());

    }
    
}
