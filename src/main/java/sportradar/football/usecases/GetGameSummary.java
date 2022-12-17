package sportradar.football.usecases;

import sportradar.football.domain.Match;
import sportradar.football.repository.ScoreTable;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GetGameSummary {

    private final Comparator<Match> SUMMARY_MATCH_COMPARATOR = Comparator.comparing(Match::getTotalScore)
            .thenComparing(Match::getStartDate).reversed();

    public List<Match> getLiveScoreBoard() {
        return ScoreTable.DB_INSTANCE.stream()
                .sorted(SUMMARY_MATCH_COMPARATOR)
                .collect(Collectors.toList());
    }
}
