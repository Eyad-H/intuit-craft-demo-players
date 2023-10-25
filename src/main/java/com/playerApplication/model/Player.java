package com.playerApplication.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

@Component
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Player {
    private String playerID;
    private String birthYear;
    private String birthMonth;
    private String birthDay;
    private String birthCountry;
    private String birthState;
    private String birthCity;
    private String deathYear;
    private String deathMonth;
    private String deathDay;
    private String deathCountry;
    private String deathState;
    private String deathCity;
    private String nameFirst;
    private String nameLast;
    private String nameGiven;
    private String weight;
    private String height;
    private String bats;
    @JsonProperty("throws")
    private String playerThrows;
    private String debut;
    private String finalGame;
    private String retroID;
    private String bbrefID;
}
