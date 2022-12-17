package sportradar.football.util;

import sportradar.football.exceptions.InvalidMatchAttributeException;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class MatchValidator {

    public static void validateScores(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new InvalidMatchAttributeException("Provided score values are invalid");
        }
    }

    public static void validateTeamNames(String homeTeamName, String awayTeamName) {
        if (isEmpty(homeTeamName) || isEmpty(awayTeamName)) {
            throw new InvalidMatchAttributeException("home or away team is not valid");
        }
    }
}
