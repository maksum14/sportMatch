package sportradar.football.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import sportradar.football.domain.Match;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScoreTable {

    public static final Set<Match> DB_INSTANCE = new HashSet<>();
}
