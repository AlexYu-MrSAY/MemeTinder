package com.company.syugai.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDate;
import java.util.Objects;

@DatabaseTable(tableName = "MemeReview")
public class MemeReview implements Model<Integer>{
    @DatabaseField(id = true)
    private int id;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User user;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Meme meme;
    @DatabaseField(columnName = "rating", canBeNull = false)
    private boolean rating;
    @DatabaseField(columnName = "data", canBeNull = false, dataType = DataType.SERIALIZABLE)
    private LocalDate date;


    public MemeReview() {
    }

    public MemeReview(int id, User user, Meme meme, boolean rating, LocalDate date) {
        this.id = id;
        this.user = user;
        this.meme = meme;
        this.rating = rating;
        this.date = date;

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Meme getMeme() {
        return meme;
    }

    public void setMeme(Meme meme) {
        this.meme = meme;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isRating() {
        return rating;
    }

    public void setRating(boolean rating) {
        this.rating = rating;
    }

    public static final String ID = "id";
    public static final String USER = "user";
    public static final String MEME = "meme";
    public static final String RATING = "rating";
    public static final String DATE = "date";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemeReview that = (MemeReview) o;
        return id == that.id && rating == that.rating && Objects.equals(user, that.user) && Objects.equals(meme, that.meme) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, meme, rating, date);
    }

    @Override
    public String toString() {
        return "MemeReview{" +
                "id=" + id +
                ", user=" + user +
                ", meme=" + meme +
                ", rating=" + rating +
                ", date=" + date +
                '}';
    }
}
