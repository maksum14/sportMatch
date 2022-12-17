package sportradar.football.usecases;

import sportradar.football.exceptions.NoSuchMatchException;
import sportradar.football.repository.ScoreTable;

import java.util.UUID;

import static java.lang.String.format;
import static sportradar.football.util.MatchValidator.validateScores;

public class UpdateScore {
    public void update(final UUID matchId, int homeScore, int awayScore) {
        validateScores(homeScore, awayScore);

        var game = ScoreTable.DB_INSTANCE.stream()
                .filter(g -> g.getId().equals(matchId))
                .findFirst()
                .orElseThrow(() -> new NoSuchMatchException(format("Failed to find match with id %s", matchId)));

        game.setHomeScore(homeScore);
        game.setAwayScore(awayScore);
    }
}
