package com.company.syugai.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDate;
import java.util.Objects;

@DatabaseTable(tableName = "Users")
public class User implements Model<Integer>{
    @DatabaseField(id = true)
    private int id;
    @DatabaseField(columnName = "login", canBeNull = false)
    private String login;
    @DatabaseField(columnName = "password", canBeNull = false)
    private String password;
    @DatabaseField(columnName = "first_name", canBeNull = false)
    private String first_name;
    @DatabaseField(columnName = "last_name", canBeNull = false)
    private String last_name;
    @DatabaseField(columnName = "sex", canBeNull = false)
    private String sex;
    @DatabaseField(columnName = "country", canBeNull = false)
    private String country;
    @DatabaseField(columnName = "city", canBeNull = false)
    private String city;
    @DatabaseField(columnName = "birthday", canBeNull = false, dataType = DataType.SERIALIZABLE)
    private LocalDate birthday;
    @DatabaseField(columnName = "date", canBeNull = false, dataType = DataType.SERIALIZABLE)
    private LocalDate data;
    @DatabaseField(columnName = "phone", canBeNull = false)
    private String phone;
    @DatabaseField(columnName = "role", canBeNull = false)
    private UserRole role;

    public User(int id, String login, String password, String first_name, String last_name, String sex, String country, String city, LocalDate birthday, LocalDate date, String phone, UserRole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.sex = sex;
        this.country = country;
        this.city = city;
        this.birthday = birthday;
        this.data = date;
        this.phone = phone;
        this.role = role;
    }

    public User(){

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getData(){
        return data;
    }

    public void setData(LocalDate date) {
        this.data = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public static final String ID = "id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String SEX = "sex";
    public static final String COUNTRY = "country";
    public static final String CITY = "city";
    public static final String BIRTHDAY = "birthday";
    public static final String DATA = "data";
    public static final String PHONE = "phone";
    public static final String ROLE = "role";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(first_name, user.first_name) && Objects.equals(last_name, user.last_name) && Objects.equals(sex, user.sex) && Objects.equals(country, user.country) && Objects.equals(city, user.city) && Objects.equals(birthday, user.birthday) && Objects.equals(data, user.data) && Objects.equals(phone, user.phone) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, first_name, last_name, sex, country, city, birthday, data, phone, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", sex='" + sex + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", birthday=" + birthday +
                ", data=" + data +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                '}';
    }
}
