package sdf.extra;

import java.io.Console;
import java.util.Optional;

public class Main {
    public static void main( String[] args ) {
        final Game game = new Game();
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
            game.makeGuess(opt.get());

            System.out.printf("\tcross entropy: %.3f\n", Adjudicator.crossEntropy(opt.get(), game.revealCode()));
        }

    }
}