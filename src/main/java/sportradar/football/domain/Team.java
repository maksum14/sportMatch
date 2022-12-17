package sportradar.football.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Team {
    private String name;
    private int score;

    public Team(String name){
        this.name = name;
    }
}
