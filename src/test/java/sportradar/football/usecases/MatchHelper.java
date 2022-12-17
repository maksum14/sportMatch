package sportradar.football.usecases;

import sportradar.football.domain.Match;
import sportradar.football.repository.ScoreTable;

import java.util.Set;
import java.util.UUID;

public class MatchHelper {

    public static Match createSimpleMatch() {
        var match = new Match("homeTeam", "awayTeam");
        ScoreTable.DB_INSTANCE.add(match);
        return match;
    }

    public static Match findMatchById(UUID matchId) {
       return ScoreTable.DB_INSTANCE.stream()
               .filter(m -> m.getId().equals(matchId))
               .findFirst()
               .orElseThrow();
    }

    public static boolean existByMatchId(UUID matchId) {
        return ScoreTable.DB_INSTANCE.stream()
                .anyMatch(m -> m.getId().equals(matchId));
    }

    public static void createMatches(Set<Match> matches) {
        ScoreTable.DB_INSTANCE.addAll(matches);
    }

    public static void clean(){
        ScoreTable.DB_INSTANCE.clear();
    }
}
