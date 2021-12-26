package sdf.extra;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import sdf.extra.Constants.CodePeg;
import sdf.extra.Constants.KeyPeg;

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

    public static List<KeyPeg> evaluate(final Row guessRow, final Row codeRow) {
        final List<KeyPeg> outcome = new LinkedList<>();

        final Optional<List<CodePeg>> optGuess = guessRow.getPegs();
        final Optional<List<CodePeg>> optCode = codeRow.getPegs();

        if (optGuess.isPresent() && optCode.isPresent()) {
            final List<CodePeg> guessList = optGuess.get();
            final List<CodePeg> codeList = optCode.get();

            for (int i = 0; i < guessList.size(); i++) {
                final CodePeg g = guessList.get(i);
                if (g.equals(codeList.get(i))) {
                    outcome.add(KeyPeg.Black);
                    continue;
                }
                for (CodePeg c: codeList) {
                    if (g.equals(c)) {
                        outcome.add(KeyPeg.White);
                        break;
                    }
                }
            }
        }

        Collections.shuffle(outcome);
        return Collections.unmodifiableList(outcome);
    }
    
}
