package trivia.domain;

import lombok.RequiredArgsConstructor;

import java.util.Map;


@RequiredArgsConstructor
public class QuestionService {

    private final Map<QuestionType, QuestionRepository> questionsRepository;

    public void askQuestion(QuestionType type) {
        System.out.println(questionsRepository.get(type).getNext());
    }
}
