package trivia.domain.utils;

public class PenaltyUtils {

    public static boolean canGetOutOfPenalty(int roll){
        return roll % 2 != 0;
    }
}
