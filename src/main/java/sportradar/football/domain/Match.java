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

    @Override
    public String toString() {
        return homeTeam.getName() + " " + homeTeam.getScore() + " - " + awayTeam.getName() + " " + awayTeam.getScore();
    }
}
