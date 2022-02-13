package trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// REFACTOR ME
public class GameBetter implements IGame {

    public static final int NUMBER_OF_CARDS = 50;
    public static final int NUMBER_OF_CELLS = 12;

    private final List<Player> players = new ArrayList<>();
    private final int[] purses = new int[6];
    private final List<String> popQuestions = new LinkedList<>();
    private final List<String> scienceQuestions = new LinkedList<>();
    private final List<String> sportsQuestions = new LinkedList<>();
    private final List<String> rockQuestions = new LinkedList<>();
    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;

    public GameBetter() {
        for (int i = 0; i < NUMBER_OF_CARDS; i++) {
            popQuestions.add("Pop Question " + i);
            scienceQuestions.add("Science Question " + i);
            sportsQuestions.add("Sports Question " + i);
            rockQuestions.add("Rock Question " + i);
        }
    }

    public boolean add(String playerName) {
        players.add(new Player(playerName));


        purses[howManyPlayers()] = 0;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (players.get(currentPlayer).isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");

                regularRoll(roll);
            } else {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            regularRoll(roll);
        }
    }

    private void regularRoll(int roll) {
        movePlayer(roll);
        System.out.println(players.get(currentPlayer) + "'s new location is " + players.get(currentPlayer).getPlace());
        System.out.println("The category is " + currentCategory());
        askQuestion();
    }

    private void movePlayer(int roll) {
        players.get(currentPlayer).moveToPlace(roll);

//        players.get(currentPlayer).getPlace() += roll;
//        if (players.get(currentPlayer).getPlace() >= NUMBER_OF_CELLS) {
//            players.get(currentPlayer).getPlace() -= NUMBER_OF_CELLS;
//        }
    }

    private void askQuestion() {
        if (currentCategory().equals("Pop")) {
            System.out.println(popQuestions.remove(0));
        }
        if (currentCategory().equals("Science")) {
            System.out.println(scienceQuestions.remove(0));
        }
        if (currentCategory().equals("Sports")) {
            System.out.println(sportsQuestions.remove(0));
        }
        if (currentCategory().equals("Rock")) {
            System.out.println(rockQuestions.remove(0));
        }
    }

    private String currentCategory() {
        if (players.get(currentPlayer).getPlace() == 0) return "Pop";
        if (players.get(currentPlayer).getPlace() == 4) return "Pop";
        if (players.get(currentPlayer).getPlace() == 8) return "Pop";
        if (players.get(currentPlayer).getPlace() == 1) return "Science";
        if (players.get(currentPlayer).getPlace() == 5) return "Science";
        if (players.get(currentPlayer).getPlace() == 9) return "Science";
        if (players.get(currentPlayer).getPlace() == 2) return "Sports";
        if (players.get(currentPlayer).getPlace() == 6) return "Sports";
        if (players.get(currentPlayer).getPlace() == 10) return "Sports";
        return "Rock";
    }

    public boolean wasCorrectlyAnswered() {
        if (players.get(currentPlayer).isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                purses[currentPlayer]++;
                players.get(currentPlayer).addCoin();
                System.out.println(players.get(currentPlayer)
                        + " now has "
                        + purses[currentPlayer]
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }


        } else {

            System.out.println("Answer was corrent!!!!");
            players.get(currentPlayer).addCoin();
            System.out.println(players.get(currentPlayer)
                    + " now has "
                    + players.get(currentPlayer).getCoins()
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        players.get(currentPlayer).setInPenaltyBox(true);

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }

}