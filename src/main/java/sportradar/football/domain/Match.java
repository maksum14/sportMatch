package sportradar.football.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Setter
@Getter
@EqualsAndHashCode(of = {"id"})
public class Match {
    private UUID id;
    private Team homeTeam;
    private Team awayTeam;
    private LocalDateTime startDate;

    public Match(String homeTeam, String awayTeam) {
        this.id = UUID.randomUUID();
        this.startDate = LocalDateTime.now();
        this.homeTeam = new Team(homeTeam);
        this.awayTeam = new Team(awayTeam);
    }

    public int getTotalScore() {
        return homeTeam.getScore() + awayTeam.getScore();
    }

    public void setAwayScore(int score) {
        awayTeam.setScore(score);
    }

    public void setHomeScore(int score) {
        homeTeam.setScore(score);
    }

    @Override
    public String toString() {
        return homeTeam.getName() + " " + homeTeam.getScore() + " - " + awayTeam.getName() + " " + awayTeam.getScore();
    }
}
