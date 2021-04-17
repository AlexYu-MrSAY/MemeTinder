package com.company.syugai.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDate;
import java.util.Objects;

@DatabaseTable(tableName = "Memes")
public class Meme implements Model<Integer>{
    @DatabaseField(id = true)
    private int id;
    @DatabaseField(columnName = "link", canBeNull = false)
    private String link;
    @DatabaseField(columnName = "date", canBeNull = false)
    private LocalDate data;

    public Meme() {
    }

    public Meme(int id, String link, LocalDate data) {
        this.id = id;
        this.link = link;
        this.data = data;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDate getDate() {
        return data;
    }

    public void setDate(LocalDate date) {
        this.data = date;
    }

    public static final String ID = "id";
    public static final String LINK = "link";
    public static final String DATE = "date";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meme meme = (Meme) o;
        return id == meme.id && Objects.equals(link, meme.link) && Objects.equals(data, meme.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link, data);
    }

    @Override
    public String toString() {
        return "Meme{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", data=" + data +
                '}';
    }
}
