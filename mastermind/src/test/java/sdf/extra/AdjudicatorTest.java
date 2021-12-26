package sdf.extra;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sdf.extra.Constants.CodePeg;
import sdf.extra.Constants.KeyPeg;

public class AdjudicatorTest {

    private CodeRow codeRow;

    @Before
    public void init() {
        codeRow = new CodeRow();
        codeRow.setPegs(CodePeg.Blue, CodePeg.White, CodePeg.Red, CodePeg.Green);
    }

    @Test
    public void testNoMatch() {
        GuessRow guessRow = createGuessRow(CodePeg.Yellow, CodePeg.Black, CodePeg.Yellow, CodePeg.Black);
        List<KeyPeg> result = Adjudicator.evaluate(guessRow, codeRow);
        assertEquals("None match", 0, result.size());
    }

    @Test
    public void testAllWhite() {
        GuessRow guessRow = createGuessRow(CodePeg.White, CodePeg.Blue, CodePeg.Yellow, CodePeg.Black);
        List<KeyPeg> result = Adjudicator.evaluate(guessRow, codeRow);
        assertEquals("Should match 2", 2, result.size());
        assertEquals("Should have 2 whites", 2, Utils.countKeyPegs(result, KeyPeg.White));
    }

    @Test 
    public void testMixOfWhiteAndBlack() {
        GuessRow guessRow = createGuessRow(CodePeg.White, CodePeg.Blue, CodePeg.Yellow, CodePeg.Green);
        List<KeyPeg> result = Adjudicator.evaluate(guessRow, codeRow);
        assertEquals("Should match 3", 3, result.size());
        assertEquals("Should have 2 whites", 2, Utils.countKeyPegs(result, KeyPeg.White));
        assertEquals("Should have 1 black", 1, Utils.countBlackKeyPegs(result));
    }

    @Test
    public void testAllBlack() {
        List<KeyPeg> result = Adjudicator.evaluate(codeRow, codeRow);
        assertEquals("Should match 4", 4, result.size());
        assertEquals("Should have 4 black", 4, Utils.countBlackKeyPegs(result));
    }

    private GuessRow createGuessRow(CodePeg p0, CodePeg p1, CodePeg p2, CodePeg p3) {
        GuessRow guessRow = new GuessRow();
        guessRow.setPegs(p0, p1, p2, p3);
        return guessRow;
    }
}