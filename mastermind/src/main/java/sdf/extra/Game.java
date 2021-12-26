package sdf.extra;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.security.SecureRandom;
import java.util.Random;

import sdf.extra.Constants.CodePeg;
import sdf.extra.Constants.KeyPeg;

public class Game {

    private final CodeRow code;
    private final List<GuessRow> guesses = new LinkedList<>();
    private final boolean duplicateColor;
    private boolean won = false;

    public Game() {
        this(false);
    }

    public Game(boolean dup) {
        duplicateColor = dup;
        if (dup)
            code = createDuplicateCode();
        else
            code = createNonDuplicateCode();
    }

    public boolean hasDuplicateColor() {
        return duplicateColor;
    }

    public boolean hasEnded() {
        return won || (guesses.size() >= Constants.ROWS);
    }

    public boolean hasWon() {
        return (won);
    }

    public Integer currentRow() {
        return guesses.size() + 1;
    }

    public List<KeyPeg> makeGuess(GuessRow guessRow) {
        this.guesses.add(guessRow);
        List<KeyPeg> result = Adjudicator.evaluate(guessRow, code);
        won = Utils.countKeyPegs(result, KeyPeg.Black) == Constants.GUESS_NUM;
        return result;
    }

    public Row revealCode() {
        return code;
    }

    private CodeRow createNonDuplicateCode() {
        final List<Integer> nums = new LinkedList<>();
        for (int i = 0; i < CodePeg.values().length; i++)
            nums.add(i);
        Collections.shuffle(nums);
        final CodeRow code = new CodeRow();
        code.setPegs(
            CodePeg.values()[nums.get(0)],
            CodePeg.values()[nums.get(1)],
            CodePeg.values()[nums.get(2)],
            CodePeg.values()[nums.get(3)]
        );
        return (code);
    }

    private CodeRow createDuplicateCode() {
        final Random rnd = new SecureRandom();
        final CodePeg[] p = new CodePeg[Constants.GUESS_NUM];
        for (int i = 0; i < Constants.GUESS_NUM; i++) {
            int ord = rnd.nextInt(CodePeg.values().length);
            p[i] = CodePeg.values()[ord];
        }
        final CodeRow code = new CodeRow();
        // varags would make this easier
        code.setPegs(p[0], p[1], p[2], p[3]);
        return (code);
    }

    @Override
    public String toString() {
        return code.toString();
    }

}
