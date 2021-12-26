package sdf.extra;

import java.util.List;

import sdf.extra.Constants.KeyPeg;

public class Utils {

    public static int countBlackKeyPegs(List<KeyPeg> result) {
        return countKeyPegs(result, KeyPeg.Black);
    }

    public static int countKeyPegs(List<KeyPeg> result, KeyPeg peg) {
        return (int)result.stream()
                .filter(v -> peg.equals(v))
                .count();
    }
}
