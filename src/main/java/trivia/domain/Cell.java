package trivia.domain;

import lombok.Data;

@Data
public class Cell {

    private final String name;
    private final QuestionType category;

    private Cell next;

}
