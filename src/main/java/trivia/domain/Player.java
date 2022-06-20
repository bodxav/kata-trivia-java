package trivia.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Stream;

@Getter
@Setter
@EqualsAndHashCode
public class Player {
    private String name;
    private int nbCoins;
    private boolean isInPenaltyBox;
    //private int goodAnswersStreak;
    private Cell currentCell;

    public Player(String name, Cell currentCell) {
        this.name = name;
        this.currentCell = currentCell;
    }

    public void move(int roll){
        for(int i = 0; i< roll; i++){
            currentCell = currentCell.getNext();
        }
    }
}
