package com.winbro.penelope;

public class FixtureType {

    private final int total;
    private final long id;
    private String name;

    public FixtureType(long id, String name, int total) {
        this.id = id;
        this.name = name;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }
}
