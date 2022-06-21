package trivia.domain;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class Player {

    @EqualsAndHashCode.Include
    private String name;

    private int nbCoins;
    private boolean isInPenaltyBox;
    private Cell currentCell;

    public void move(int roll) {
        for (int i = 0; i < roll; i++) {
            currentCell = currentCell.getNext();
        }
    }

    public void addCoin(){
        nbCoins++;
    }

    public boolean isAWinner(){
        return nbCoins==6;
    }

    @Override
    public String toString() {
        return name;
    }
}
