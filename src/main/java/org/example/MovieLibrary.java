package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

public class MovieLibrary {
    private List<Movie> movies;

    public MovieLibrary() {
        List<Director> directors = Utils.readFile("directors").stream().map(Director::new).toList();
        List<Actor> actors = Utils.readFile("actors").stream().map(Actor::new).toList();
        this.movies = Utils.readFile("movies")
                .stream()
                .map(item -> {
                    int id = Integer.parseInt(item.get(0));
                    String title = item.get(1);
                    Director director = directors.stream().filter(dir -> dir.getId() == Integer.parseInt(item.get(2))).findFirst().orElse(null);//אם הבמאי נימצא
                    int actor1IdFromFile = Integer.parseInt(item.get(3));
                    int actor2IdFromFile = Integer.parseInt(item.get(4));
                    List<Actor> actorsList = actors.stream().filter(act -> act.getId() == actor1IdFromFile || act.getId() == actor2IdFromFile).distinct().toList();
                    int year = Integer.parseInt(item.get(5));
                    String genre = item.get(6);
                    return new Movie(id, title, director, actorsList, year, genre);
                }).toList();

        //List<String> names = findDirectorsWithAtLeastNMovies2(5);
        //List<String> year = findMoviesByYear(2012);
        //List<String> genre= findMoviesByGenre("Drama");
        //List<String>actorsInGenre=findActorsInGenre2("Drama");
        //findAverageReleaseYearForDirector("Ang Lee");
         findTopNActors(5);
    }


    //    // 1. Find all movies released in a specific year.
//    // Return a list of movie titles.
    public List<String> findMoviesByYear(int year) {
        List<String> moviesByYear = movies.stream()
                .filter(item -> item.getReleaseYear() == year)// מסנן מרשימת הסרטים את השנה הנתונה
                .map(Movie::getTitle)// בונה את הרשימה לפי הכותרות של הסרטים
                .toList();
        return moviesByYear;
    }


//
//    // 2. Find all movies of a specific genre.
//    // Return a list of movie titles.
    public List<String> findMoviesByGenre(String genre) {
        List<String> moviesByGenre=movies.stream()
                .filter(item->item.getGenre().equals(genre)) // מסנן מרשימת הסרטים את השמות זהים בג'אנר
                .map(Movie::getTitle)// לוקח את השמות של הסרטים ומכניס לרשימה
                .toList();
        return moviesByGenre;
    }
//
//    // 3. Find all directors who have directed at least N movies.
//    // Return a list of director names.
    public List<String> findDirectorsWithAtLeastNMovies(int n) {
        Map<String, Long> directorsMoviesCount = movies.stream()
                .filter(item -> item.getDirector() != null)// מסנן את כל הסרטים שיש להם בנאי
                .collect(Collectors.groupingBy(item -> item.getDirector().getName(), counting()));// תיצור קבוצה של שם הבמאי ומספר פעמים שמופיע
        return directorsMoviesCount.entrySet().stream()
                .filter(item -> item.getValue() >= n)
                .map(Map.Entry::getKey).toList();

    }
    public List<String> findDirectorsWithAtLeastNMovies2(int n){
       Map<String,Long>directors=movies.stream().filter(item->item.getDirector()!=null)
            //    .collect(Collectors.groupingBy(item->item.getDirector().getName(),Collectors.counting()));
         .collect(Collectors.groupingBy(item->item.getDirector().getName(), counting()));
       System.out.println(directors);
       List<String>directorsNames=directors.entrySet().stream().filter(item-> item.getValue()>=n).map(item->item.getKey()).toList();
       System.out.println(directorsNames);
       return directorsNames;
    }
//    // 4. Find all actors who have appeared in movies of a specific genre.
//    // Return a list of actor names.
    public List<String> findActorsInGenre(String genre) {

        List<List<Actor>> actorsInGenre=movies.stream()
                .filter(item->item.getGenre().equals(genre))
                .map(Movie::getActors)
                .toList();

            return actorsInGenre.stream()
                    .flatMap(List::stream)//רשימה אחת של שחקינים
                    .map(Actor::getName)//יחזיר שמות
                    .toList();
    }
    public List<String> findActorsInGenre2(String genre){
        List<List<Actor>> actors=movies.stream().filter(x->x.getGenre().equals(genre)).map(item->item.getActors()).toList();
        System.out.println(actors);
        List<String>actorsFinal=actors.stream().flatMap(x->x.stream()).map(Actor::getName).distinct().toList();// מוחק גם כפיליות
        System.out.println(actorsFinal);
        return actorsFinal;
    }
//    // 5. Find the average release year of movies for a specific director.
//    // Return a double value.
    public double findAverageReleaseYearForDirector(String directorName) {
        Double finisResult=null;
        try {
            List<Integer> result=movies.stream()
                    .filter(x-> x.getDirector()!=null && x.getDirector().getName().equals(directorName)).map(Movie::getReleaseYear)
                   .toList();
            System.out.println(result);
            Integer resultFinal=result.stream().reduce(Integer::sum).orElse(0);

             finisResult=(double)resultFinal/result.size();
            System.out.println(finisResult);
        }catch (Exception e){
            e.printStackTrace();
        }

        return finisResult;
    }
//
//    // 6. Find the top N actors who have appeared in the most movies.
//    // Return a list of actor names.
    public List<String> findTopNActors(int n) {

       return null;
    }
//
//    // 7. Find all movies where a specific actor and director have worked together.
//    // Return a list of movie titles.
//    public List<String> findMoviesByActorAndDirector(String actorName, String directorName) {
//        // Implement using declarative programming
//    }
//
//    // 8. Find the most common genre for each actor.
//    // Return a map with actor names as keys and the most common genre as values.
//    public Map<String, String> findMostCommonGenrePerActor() {
//        // Implement using declarative programming
//    }
//
//    // 9. Find the director with the highest average movie rating (1-5).
//    // Assume there is a method: double getMovieRating(Movie movie), which returns the rating of a movie.
//    // Return the director name.
//    public String findDirectorWithHighestAverageRating() {
//        // Implement using declarative programming
//    }


}
