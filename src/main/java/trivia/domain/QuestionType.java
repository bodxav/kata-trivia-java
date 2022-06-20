package trivia.domain;

public enum QuestionType {
    POP("Pop"),SCIENCE("Science"),SPORTS("Sports"),ROCK("Rock"),GEOGRAPHY("Geography");

    public final String label;
    QuestionType(String label) {
        this.label = label;
    }
}
