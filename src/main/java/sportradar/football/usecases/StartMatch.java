package sportradar.football.usecases;

import sportradar.football.domain.Match;
import sportradar.football.repository.ScoreTable;

import static sportradar.football.util.MatchValidator.validateTeamNames;


public class StartMatch {

    public Match create(String homeTeamName, String awayTeamName) {
        validateTeamNames(homeTeamName, awayTeamName);

        var match = new Match(homeTeamName, awayTeamName);
        ScoreTable.DB_INSTANCE.add(match);
        return match;
    }
}
