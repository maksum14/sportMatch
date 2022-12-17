package sportradar.football.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import sportradar.football.domain.Match;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScoreTable {

    public static final List<Match> DB_INSTANCE = new ArrayList<>();

}
