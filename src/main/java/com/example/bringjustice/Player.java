package com.example.bringjustice;

public class Player {
    private String name;
    private String password;
    private String captured;
    private String killed;

    public Player(String playerName) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptured() {
        return captured;
    }

    public void setCaptured(int captured) {
        this.captured = String.valueOf(captured);
    }

    public String getKilled() {
        return killed;
    }

    Player.kill(usama);
    public void setKilled(int killed) {
        this.killed = String.valueOf(killed);
    }
    public void kill(WantedPeople terrorist) {
        SQL.updateStatus(terrorist, "dead");
    }
}
