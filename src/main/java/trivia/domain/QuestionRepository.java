package trivia.domain;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class QuestionRepository {


    private final List<String> questions = new ArrayList<>();
    private int lastIndex = -1;

    public QuestionRepository(QuestionType type) {
        for(int i = 0; i<50; i++){
            questions.add(type + " Question "+ i );
        }
    }

    public String getNext(){
        if(lastIndex== questions.size() -1){
            lastIndex = -1;
        }

        return questions.get(++lastIndex);
    }

}
