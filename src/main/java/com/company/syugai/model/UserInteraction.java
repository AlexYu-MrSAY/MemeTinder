package com.company.syugai.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDate;
import java.util.Objects;

@DatabaseTable(tableName = "UsersInteraction")
public class UserInteraction implements Model<Integer>{
    @DatabaseField(id = true)
    private int id;
    @DatabaseField(columnName = "source", canBeNull = false)
    private User source;
    @DatabaseField(columnName = "target", canBeNull = false)
    private User target;
    @DatabaseField(columnName = "reaction", canBeNull = false)
    private boolean reaction;
    @DatabaseField(columnName = "date", canBeNull = false)
    private LocalDate date;

    public UserInteraction() {
    }

    public UserInteraction(int id, User source, User target, boolean reaction, LocalDate date) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.reaction = reaction;
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

    public User getSource() {
        return source;
    }

    public void setSource(User source) {
        this.source = source;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public boolean isReaction() {
        return reaction;
    }

    public void setReaction(boolean reaction) {
        this.reaction = reaction;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static final String ID = "id";
    public static final String SOURCE = "source";
    public static final String TARGET = "target";
    public static final String REACTION = "reaction";
    public static final String DATE = "date";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInteraction that = (UserInteraction) o;
        return id == that.id && reaction == that.reaction && Objects.equals(source, that.source) && Objects.equals(target, that.target) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source, target, reaction, date);
    }

    @Override
    public String toString() {
        return "UserInteraction{" +
                "id=" + id +
                ", source=" + source +
                ", target=" + target +
                ", reaction=" + reaction +
                ", date=" + date +
                '}';
    }
}
