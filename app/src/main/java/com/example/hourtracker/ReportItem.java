package com.example.hourtracker;

public class ReportItem {

    private final String type;
    private final String timestamp;

    public ReportItem(String type, String timestamp) {
        this.type = type;
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
