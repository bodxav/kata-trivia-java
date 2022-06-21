package trivia.domain;

import trivia.IGame;

import java.util.*;

import static trivia.domain.utils.PenaltyUtils.canGetOutOfPenalty;

public class GameRefactor implements IGame {

    private final List<Player> players = new ArrayList<>();
    private final QuestionService questionService;
    private final Cell firstCell;

    private Player current;

    public GameRefactor() {
        Map<QuestionType, QuestionRepository> repositories = new HashMap<>();
        Arrays.stream(QuestionType.values())
                .forEach(type -> repositories.computeIfAbsent(type, (k) -> new QuestionRepository(type)));
        questionService = new QuestionService(repositories);

        firstCell = BoardFactory.getCells();
    }


    @Override
    public boolean add(String playerName) {

        var player = Player.builder()
                .name(playerName)
                .currentCell(firstCell)
                .build();

        if (current == null) {
            current = player;
        }

        System.out.println(playerName+ " was added");
        System.out.println("They are player number "+ (players.size() +1));

        return players.add(player);
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

        if (current.isInPenaltyBox()) {
            nextPlayer();
            return true;
        }

        System.out.println("Answer was correct!!!!");
        current.addCoin();
        System.out.println(current
                + " now has "
                + current.getNbCoins()
                + " Gold Coins.");

        boolean winner = current.isAWinner();
        nextPlayer();

        //interface force to return a "not a winner" boolean
        return !winner;
    }

    @Override
    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(current + " was sent to the penalty box");
        current.setInPenaltyBox(true);
        nextPlayer();

        return true;
    }


    private void play() {
        var cell = current.getCurrentCell();
        System.out.println(current.getName()
                + "'s new location is "
                + cell);
        System.out.println("The category is " + cell.getCategory());
        questionService.askQuestion(cell.getCategory());
    }

    private void nextPlayer() {
        var next = players.indexOf(current) + 1;
        if (next == players.size()) {
            next = 0;
        }
        current = players.get(next);
    }

}
