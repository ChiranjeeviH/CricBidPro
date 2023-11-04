package com.auctioneer.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PlayerInfo {
    private boolean status;
    private Page page;
    private Data data;

    @lombok.Data
    public static class Page {
        @JsonProperty("serverdatetime")
        private long serverDateTime;
    }

    @lombok.Data
    public static class Data {
        private List<Player> players;
        private List<Team> teams;
        private List<Match> matches;
        private List<Tournament> tournaments;
    }

    @lombok.Data
    public static class Player {
        @JsonProperty("user_id")
        private long userId;
        private String name;
        @JsonProperty("city_id")
        private int cityId;
        @JsonProperty("city_name")
        private String cityName;
        @JsonProperty("player_skill")
        private String playerSkill;
        @JsonProperty("is_verified")
        private int isVerified;
        @JsonProperty("profile_photo")
        private String profilePhoto;
        @JsonProperty("playing_role")
        private String playingRole;
        @JsonProperty("is_player_pro")
        private int isPlayerPro;
        @JsonProperty("association_tag")
        private String associationTag;
    }

    @lombok.Data
    public static class Team {
        // Define fields for the "teams" data if needed
    }

    @lombok.Data
    public static class Match {
        // Define fields for the "matches" data if needed
    }

    @lombok.Data
    public static class Tournament {
        // Define fields for the "tournaments" data if needed
    }
}


