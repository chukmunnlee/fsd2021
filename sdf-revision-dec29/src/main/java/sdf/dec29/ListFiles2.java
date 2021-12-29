package sdf.dec29;

import java.io.File;

public class ListFiles2{

    public static void main(String[] args) {

        CheckSuffix dotCrt = new CheckSuffix(".crt");
        CheckSuffix dotYaml = new CheckSuffix(".yaml");

        File dir = new File("/opt/tmp/k8s-stuff");

        /*
        File[] yamls = dir.listFiles(
            (f) -> {
                return dotCrt.hasSuffix(f);
            }
        );
        */
        File[] files = dir.listFiles(
            // public boolean hasSuffix(File)
            // :: method reference
            dotCrt::hasSuffix
        );

        CheckSuffix.isInDirectory(new File("/"), "fred.txt");

        for (File f: files)
            System.out.printf("%s\n", f.getName());

        files = dir.listFiles(
            // public boolean hasSuffix(File)
            // :: method reference
            dotYaml::hasSuffix
        );

        for (File f: files)
            System.out.printf("%s\n", f.getName());

    }
    
}
