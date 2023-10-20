package com.kgisl.maven1;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class Players {
    @CsvBindByName(column = "name")
    @CsvBindByPosition(position = 0)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @CsvBindByName(column="age")
    @CsvBindByPosition(position = 1)
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @CsvBindByName(column = "team")
    @CsvBindByPosition(position = 2)
    private String team;

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @CsvBindByName(column="jerseyNo")
    @CsvBindByPosition(position = 3)
    private int jerseyNo;

public int getJerseyNo() {
        return jerseyNo;
    }

    public void setJerseyNo(int jerseyNo) {
        this.jerseyNo = jerseyNo;
    }

@Override
    public String toString() {
        return "Players {" +
                "NAME='" + name + '\'' +
                ", AGE='" + age + '\'' +
                ", TEAM='" + team + '\'' +
                ", JERSEY NO='" + jerseyNo + '\'' +
                '}';
    }


}