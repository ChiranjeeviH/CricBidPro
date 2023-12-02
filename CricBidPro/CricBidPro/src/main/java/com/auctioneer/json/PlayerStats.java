package com.auctioneer.json;


import lombok.Data;

import java.util.List;

@Data
public class PlayerStats {
    private boolean status;
    private Data data;

    @lombok.Data
    public static class Data {
        private Statistics statistics;
    }

    @lombok.Data
    public static class Statistics {
        private List<StatisticsEntry> batting;
        private List<StatisticsEntry> captain;
        private List<StatisticsEntry> fielding;
        private List<StatisticsEntry> bowling;
    }

    @lombok.Data
    public static class StatisticsEntry {
        private String title;
        private String value;
        private int isUserProperty;
    }
}
