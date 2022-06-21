package trivia.domain;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
public class Cell {

    private final int index;

    private final QuestionType category;

    private Cell next;

    @Override
    public String toString() {
        return index+""; // js YOLO style conversion;
    }
}
