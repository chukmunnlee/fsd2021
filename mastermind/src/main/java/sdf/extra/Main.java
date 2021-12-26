package sdf.extra;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import sdf.extra.Constants.KeyPeg;

public class Main {
    public static void main( String[] args ) {
        final Game game = new Game();

        System.out.println("Has duplicate color: " + game.hasDuplicateColor());
        System.out.printf("Hidden code: %s\n", game.revealCode());

        final Console cons = System.console();

        while (!game.hasEnded()) {
            cons.printf("\nTurn %d: Please enter code [ Red, Blue, Green, Yellow, Black, White ]\n", game.currentRow());
            final String guess = cons.readLine();

            Optional<GuessRow> opt = GuessRow.parse(guess);
            if (!opt.isPresent()) {
                System.out.println("Your input is invalid");
                continue;
            }
            final List<KeyPeg> result = game.makeGuess(opt.get());

            System.out.printf("\t\t%s\n", result);
            System.out.printf("\t\tcross entropy: %.3f\n", Adjudicator.crossEntropy(opt.get(), game.revealCode()));
        }

        if (game.hasWon())
            System.out.println("Congratulations! You have broken the code");
        else
            System.out.printf("The code is %s.", game.revealCode());

    }
}