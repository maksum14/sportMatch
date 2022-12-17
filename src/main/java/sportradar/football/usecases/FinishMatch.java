package sportradar.football.usecases;

import sportradar.football.exceptions.NoSuchMatchException;
import sportradar.football.repository.ScoreTable;

import java.util.UUID;

import static java.lang.String.format;

public class FinishMatch {

    public void delete(final UUID matchId) {
        var match = ScoreTable.DB_INSTANCE.stream()
                .filter(g -> g.getId().equals(matchId))
                .findFirst()
                .orElseThrow(() -> new NoSuchMatchException(format("Failed to find match with id %s", matchId)));

        ScoreTable.DB_INSTANCE.remove(match);
    }
}
