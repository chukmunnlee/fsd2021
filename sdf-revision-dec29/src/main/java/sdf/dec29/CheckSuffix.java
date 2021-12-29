package sdf.dec29;

import java.io.File;

public class CheckSuffix {

    private final String suffix;

    public CheckSuffix(String suffix) {
        this.suffix = suffix;
    }

    public static boolean isInDirectory(File root, String fn) {
        File f = new File(root.getAbsolutePath() + "/" + fn);
        return f.exists();
    }

    public boolean hasSuffix(File f) {
        return f.getName().endsWith(suffix);
    }
    
}
