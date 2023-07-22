package org.example;

import java.util.List;

public class Actor {
    private int id;
    private String name;
    private int birthYear;

    public Actor(int id, String name, int birthYear) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
    }

    public Actor (List<String> line) {
        this.id = Integer.parseInt(line.get(0));
        this.name = line.get(1);
        this.birthYear = Integer.parseInt(line.get(2));
    }

    public int getId () {
        return this.id;
    }
    public String getName () {
        return this.name;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}
