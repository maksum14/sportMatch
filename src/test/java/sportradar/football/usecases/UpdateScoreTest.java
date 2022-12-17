package sportradar.football.usecases;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import sportradar.football.exceptions.InvalidMatchAttributeException;
import sportradar.football.exceptions.NoSuchMatchException;

import java.util.UUID;
import java.util.stream.Stream;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UpdateScoreTest {

    private UpdateScore updateScore;

    @BeforeEach
    void setup() {
        updateScore = new UpdateScore();
    }

    @AfterEach
    void clean() {
        MatchHelper.clean();
    }

    @Test
    void verifyMatchScoreHasBeenUpdatedSuccessfully() {
        //given
        var match = MatchHelper.createSimpleMatch();
        //when
        updateScore.update(match.getId(), 0, 1);

        //then

        var updatedMatch = MatchHelper.findMatchById(match.getId());

        assertEquals(1, updatedMatch.getTotalScore());
        assertEquals(0, updatedMatch.getHomeTeam().getScore());
        assertEquals(1, updatedMatch.getAwayTeam().getScore());
    }

    @Test
    void verifyFailureWhenUpdatingMatchThatDoesntExist() {
        //given
        var nonExistMatchId = UUID.randomUUID();

        //when, then
        var exception = assertThrows(
                NoSuchMatchException.class,
                () -> updateScore.update(nonExistMatchId, 0, 1));

        assertEquals(format("Failed to find match with id %s", nonExistMatchId), exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("provideNegativeMatchScoreData")
    void verifyFailureWhenUpdatingMatchScoreWithNegativeDigits(Pair<Integer, Integer> scoreData) {
        //given
        var nonExistMatchId = UUID.randomUUID();

        //when, then
        var exception = assertThrows(
                InvalidMatchAttributeException.class,
                () -> updateScore.update(nonExistMatchId, scoreData.getLeft(), scoreData.getRight()));

        assertEquals("Provided score values are invalid", exception.getMessage());
    }

    private static Stream<Pair<Integer, Integer>> provideNegativeMatchScoreData() {
        return Stream.of(
                Pair.of(1, -1),
                Pair.of(-5, 1),
                Pair.of(-5, -1)
        );
    }

}