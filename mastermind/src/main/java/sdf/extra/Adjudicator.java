package sdf.extra;

import java.util.List;

import sdf.extra.Constants.CodePeg;

public class Adjudicator {

    public static Double crossEntropy(final Row guessRow, final Row codeRow) {
        final List<CodePeg> guess = guessRow.getPegs().get();
        final List<CodePeg> code = codeRow.getPegs().get();

        Double crossEntropy = 0d;
        for (int i = 0; i < guess.size(); i++) {
            if (guess.get(i).equals(code.get(i)))
                crossEntropy += (Math.log10(.999) / Math.log10(2d));
            else
                crossEntropy += (Math.log10(.001) / Math.log10(2d));
        }
        return -crossEntropy;
    }
    
}
