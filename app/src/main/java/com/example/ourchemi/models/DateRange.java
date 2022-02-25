package com.example.ourchemi.models;

public class DateRange {
    DateObj start;
    DateObj end;

    public DateRange(DateObj start, DateObj end) {
        this.start = start;
        this.end = end;
    }

    public DateObj getStart() {
        return start;
    }

    public void setStart(DateObj start) {
        this.start = start;
    }

    public DateObj getEnd() {
        return end;
    }

    public void setEnd(DateObj end) {
        this.end = end;
    }
}
