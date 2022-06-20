package trivia.domain;

import lombok.RequiredArgsConstructor;
import trivia.IGame;

import java.util.List;

import static trivia.domain.utils.PenaltyUtils.canGetOutOfPenalty;

@RequiredArgsConstructor
public class Game implements IGame {

    private final List<Player> players;

    private Player current;


    @Override
    public boolean add(String playerName) {
        return false;
    }

    @Override
    public void roll(int roll) {
        System.out.println(current.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (current.isInPenaltyBox() && !canGetOutOfPenalty(roll)) {
            System.out.println(current + " is not getting out of the penalty box");
            return;
        }

        if (current.isInPenaltyBox()) {
            current.setInPenaltyBox(false);
            System.out.println(current + " is getting out of the penalty box");
        }

        current.move(roll);
        play();

    }

    @Override
    public boolean wasCorrectlyAnswered() {
        return false;
    }

    @Override
    public boolean wrongAnswer() {
        return false;
    }


    private void play() {
        var cell = current.getCurrentCell();
        System.out.println(current.getName()
                + "'s new location is "
                + cell.getName());
        System.out.println("The category is " + cell.getCategory());
        askQuestion();
    }


}
