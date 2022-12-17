package sportradar.football.usecases;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import sportradar.football.exceptions.InvalidMatchAttributeException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class StartMatchTest {

    private StartMatch startMatch;

    @BeforeEach
    void setup() {
        startMatch = new StartMatch();
    }

    @AfterEach
    void clean() {
        MatchHelper.clean();
    }

    @Test
    void verifyThatMatchCreatedSuccessfully() {
        //given
        var homeTeam = "Canada";
        var awayTeam = "Brazil";
        //when
        var match = startMatch.create(homeTeam, awayTeam);

        //then
        assertNotNull(match.getId());
        assertEquals(0, match.getTotalScore());
        assertEquals(0, match.getHomeTeam().getScore());
        assertEquals("Canada", match.getHomeTeam().getName());
        assertEquals(0, match.getAwayTeam().getScore());
        assertEquals("Brazil", match.getAwayTeam().getName());
        assertNotNull(match.getStartDate());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidTeamNames")
    void verifyMatchCannotBeCreatedWithInvalidTeamNames(Pair<String, String> teamNames) {
        //when, then
        var exception = assertThrows(
                InvalidMatchAttributeException.class,
                () -> startMatch.create(teamNames.getLeft(), teamNames.getRight()));

        assertEquals("Home or away team name is not valid", exception.getMessage());
    }

    private static Stream<Pair<String, String>> provideInvalidTeamNames() {
        return Stream.of(
                Pair.of("Canada", ""),
                Pair.of("Canada", null),
                Pair.of("", "Brazil"),
                Pair.of(null, "Brazil")
        );
    }
}