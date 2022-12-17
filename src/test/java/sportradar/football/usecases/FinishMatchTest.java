package sportradar.football.usecases;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sportradar.football.exceptions.NoSuchMatchException;

import java.util.UUID;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;

class FinishMatchTest {
    private FinishMatch finishMatch;

    @BeforeEach
    void setup() {
        finishMatch = new FinishMatch();
    }

    @AfterEach
    void clean() {
        MatchHelper.clean();
    }

    @Test
    void verifyMatchDeletedSuccessfully() {
        //given
        var match = MatchHelper.createSimpleMatch();

        //when
        finishMatch.delete(match.getId());

        //then
        assertFalse(MatchHelper.existByMatchId(match.getId()));
    }

    @Test
    void verifyFailureWhenDeletingMatchThatDoesntExist() {
        //given
        var nonExistMatchId = UUID.randomUUID();

        //when, then
        var exception = assertThrows(
                NoSuchMatchException.class,
                () -> finishMatch.delete(nonExistMatchId));

        assertEquals(format("Failed to find match with id %s", nonExistMatchId), exception.getMessage());
    }
}