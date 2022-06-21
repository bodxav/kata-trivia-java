package trivia.domain;

import java.util.List;

public class BoardFactory {

    private final static List<QuestionType> cellsOrder = List.of(
            QuestionType.POP,
            QuestionType.SCIENCE,
            QuestionType.SPORTS,
            QuestionType.ROCK
    );


    public static Cell getCells() {

        var firsrtCell = new Cell(0, QuestionType.POP);
        var currentCell = firsrtCell;
        Cell nextCell;

        for (int i = 1; i < 12; i++) {
            var type = cellsOrder.get(i % cellsOrder.size());
            nextCell = new Cell(i, type);
            currentCell.setNext(nextCell);
            currentCell = nextCell;
        }
        currentCell.setNext(firsrtCell);

        return firsrtCell;
    }
}
