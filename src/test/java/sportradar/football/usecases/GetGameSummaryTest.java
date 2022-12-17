package sportradar.football.usecases;

import org.junit.jupiter.api.*;
import sportradar.football.domain.Match;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class GetGameSummaryTest {
    private GetGameSummary getGameSummary;

    @BeforeEach
    void setup() {
        getGameSummary = new GetGameSummary();
    }

    @AfterEach
    void clean() {
        MatchHelper.clean();
    }

    @Test
    void verifyEmptyMatchBoard() {
        //when
        var matches = getGameSummary.getLiveScoreBoard();
        //then
        assertTrue(matches.isEmpty());
    }

    @Test
    void verifyMatchBoardWithElementsSortedBasedOnTotalScore() {
        //given
        var match_MEXICO_CANADA = new Match("Mexico", "Canada");
        match_MEXICO_CANADA.setHomeScore(0);
        match_MEXICO_CANADA.setAwayScore(4);

        var match_SPAIN_BRAZIL = new Match("Spain", "Brazil");
        match_SPAIN_BRAZIL.setHomeScore(1);
        match_SPAIN_BRAZIL.setAwayScore(9);
        MatchHelper.createMatches(
                Set.of(match_SPAIN_BRAZIL, match_MEXICO_CANADA)
        );
        //when
        var matches = getGameSummary.getLiveScoreBoard();
        //then
        assertEquals(2, matches.size());
        assertEquals(match_SPAIN_BRAZIL.getId(), matches.get(0).getId());
        assertEquals(match_MEXICO_CANADA.getId(), matches.get(1).getId());
    }


    @Test
    void verifyMatchBoardWithElementsSortedBasedOnTotalScoreAndStartDate() {
        //given
        var match_MEXICO_CANADA = new Match("Mexico", "Canada");
        match_MEXICO_CANADA.setHomeScore(4);
        match_MEXICO_CANADA.setAwayScore(4);

        var match_SPAIN_BRAZIL = new Match("Spain", "Brazil");
        match_SPAIN_BRAZIL.setHomeScore(4);
        match_SPAIN_BRAZIL.setAwayScore(4);

        var match_GERMANY_FRANCE = new Match("Germany", "France");
        match_GERMANY_FRANCE.setHomeScore(5);
        match_GERMANY_FRANCE.setAwayScore(9);

        MatchHelper.createMatches(
                Set.of(match_MEXICO_CANADA, match_SPAIN_BRAZIL, match_GERMANY_FRANCE)
        );
        //when
        var matches = getGameSummary.getLiveScoreBoard();
        //then
        assertEquals(3, matches.size());
        assertEquals(match_GERMANY_FRANCE.getId(), matches.get(0).getId());
        assertEquals(match_SPAIN_BRAZIL.getId(), matches.get(1).getId());
        assertEquals(match_MEXICO_CANADA.getId(), matches.get(2).getId());
    }
}