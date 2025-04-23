package DataAccess.Entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Record {
    private int id;
    private int userId;
    private int score;
    private LocalDate date;
    private LocalTime playTime;

    public Record(int id, int userId, int score, LocalDate date, LocalTime playTime) {
        this.id = id;
        this.userId = userId;
        this.score = score;
        this.date = date;
        this.playTime = playTime;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getPlayTime() { return playTime; }
    public void setPlayTime(LocalTime playTime) { this.playTime = playTime; }
}
