package org.example;

import java.util.List;

public class Movie {
    private int id;
    private String title;
    private Director director;
    private List<Actor> actors;
    private int releaseYear;
    private String genre;

    public Movie(int id, String title, Director director, List<Actor> actors, int releaseYear, String genre) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }
    public Movie(List<String> list){
        this.id=Integer.parseInt(list.get(0));
        this.title=list.get(1);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Director getDirector() {
        return director;
    }

    public List<Actor> getActors() {
        return actors;
    }



    public int getReleaseYear() {
        return releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                '}';
    }
}
