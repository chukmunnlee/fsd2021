package sdf.extra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import sdf.extra.Constants.CodePeg;

public class GuessRow implements Row {

    private List<CodePeg> guess = Collections.emptyList();

    @Override
    public Optional<List<CodePeg>> getPegs() {
        if (0 == guess.size())
            return Optional.empty();
        return Optional.of(Collections.unmodifiableList(guess));
    }

    @Override
    public void setPegs(CodePeg p0, CodePeg p1, CodePeg p2, CodePeg p3) {
        guess = new ArrayList<>();
        guess.add(p0);
        guess.add(p1);
        guess.add(p2);
        guess.add(p3);
    }

    @Override
    public String toString() {
        return guess.toString();
    }

    public static Optional<GuessRow> parse(String line) {
        final String[] guesses = line.trim().split("\\s+");
        if (guesses.length != Constants.GUESS_NUM)
            return Optional.empty();
        final CodePeg[] codes = new CodePeg[Constants.GUESS_NUM];
        try {
        for (int i = 0; i < guesses.length; i++)
            codes[i] = CodePeg.valueOf(guesses[i]);
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
        final GuessRow row = new GuessRow();
        row.setPegs(codes[0], codes[1], codes[2], codes[3]);
        return Optional.of(row);
    }
}
