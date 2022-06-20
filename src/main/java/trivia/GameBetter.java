package trivia;

import java.util.ArrayList;
import java.util.LinkedList;

// REFACTOR ME
public class GameBetter implements IGame {
   //TODO define class for player defined by a name, a purse, isInPenaltyBox and a current Cell (another class)
   //TODO define a class Cell that has a category of question
   //TODO define a class/interface Question that has a category,a riddle and an answer
   //TODO define a game that has a list of Cells, a list of Players and a diction of Questions (per category),a currentPlayer

   ArrayList players = new ArrayList();
   int[] places = new int[6];
   int[] purses = new int[6];
   boolean[] inPenaltyBox = new boolean[6];

   LinkedList popQuestions = new LinkedList();
   LinkedList scienceQuestions = new LinkedList();
   LinkedList sportsQuestions = new LinkedList();
   LinkedList rockQuestions = new LinkedList();

   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

   public GameBetter() {
      for (int i = 0; i < 50; i++) {
         popQuestions.addLast("Pop Question " + i);
         scienceQuestions.addLast(("Science Question " + i));
         sportsQuestions.addLast(("Sports Question " + i));
         rockQuestions.addLast(createRockQuestion(i));
      }
   }

   public String createRockQuestion(int index) {
      return "Rock Question " + index;
   }


   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   //Refactor name to make it obvious that we are adding a player
   public boolean add(String playerName) {
      players.add(playerName);
      places[howManyPlayers()] = 0;
      purses[howManyPlayers()] = 0;
      inPenaltyBox[howManyPlayers()] = false;

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   //Refactor name to getPlayersCount()
   public int howManyPlayers() {
      return players.size();
   }

   public void roll(int roll) {
      System.out.println(players.get(currentPlayer) + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (inPenaltyBox[currentPlayer]) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;

            System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
            places[currentPlayer] = places[currentPlayer] + roll;
            if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

            System.out.println(players.get(currentPlayer)
                               + "'s new location is "
                               + places[currentPlayer]);
            System.out.println("The category is " + currentCategory());
            askQuestion();
         } else {
            System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }

      } else {

         places[currentPlayer] = places[currentPlayer] + roll;
         if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

         System.out.println(players.get(currentPlayer)
                            + "'s new location is "
                            + places[currentPlayer]);
         System.out.println("The category is " + currentCategory());
         askQuestion();
      }

   }

   private void askQuestion() {
      switch (currentCategory())
      {
         case "Pop":
            System.out.println(popQuestions.removeFirst());
            break;
         case "Science":
            System.out.println(scienceQuestions.removeFirst());
            break;
         case "Sports":
            System.out.println(sportsQuestions.removeFirst());
            break;
         case "Rock":
            System.out.println(rockQuestions.removeFirst());
            break;
         default:
            System.out.println("Unknown question type");
      }
   }
   //TODO
   // See if a Cell is defined otherwise
   // use a switch case and a modulo to determine the type of question to draw from the list
   private String currentCategory() {
      if (places[currentPlayer] == 0) return "Pop";
      if (places[currentPlayer] == 4) return "Pop";
      if (places[currentPlayer] == 8) return "Pop";
      if (places[currentPlayer] == 1) return "Science";
      if (places[currentPlayer] == 5) return "Science";
      if (places[currentPlayer] == 9) return "Science";
      if (places[currentPlayer] == 2) return "Sports";
      if (places[currentPlayer] == 6) return "Sports";
      if (places[currentPlayer] == 10) return "Sports";
      return "Rock";
   }


//Refactor usage of this function --> Imply changing playGame
   public boolean wasCorrectlyAnswered() {
      if (inPenaltyBox[currentPlayer]) {
         if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            purses[currentPlayer]++;
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

         System.out.println("Answer was correct!!!!");
         purses[currentPlayer]++;
         System.out.println(players.get(currentPlayer)
                            + " now has "
                            + purses[currentPlayer]
                            + " Gold Coins.");

         boolean winner = didPlayerWin();
         //If !winner

         //Else


         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;
         return winner;
      }
   }

   //Why always returning true ?
   //Usage of the function is not clear to me
   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
      inPenaltyBox[currentPlayer] = true;

      //TODO use a modulo to find the next player or a linked list
      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
      return true;
   }


   //Refactor name to isAWinner
   private boolean didPlayerWin() {
      return !(purses[currentPlayer] == 6);
   }
}
