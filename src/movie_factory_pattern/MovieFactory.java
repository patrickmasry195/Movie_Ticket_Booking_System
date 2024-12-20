package movie_factory_pattern;

// Factory class to create Movie objects based on genre
public class MovieFactory {

    /**
     * Factory method to create a Movie object based on the provided genre.
     *
     * @param genre The genre of the movie (e.g., "Action", "Comedy", "Drama").
     * @return A Movie object corresponding to the genre, or null if the genre is not recognized.
     */
    public static Movie createMovie(String genre) {
        // Check if the genre is "Action" (case-insensitive) and return an ActionMovie instance
        if (genre.equalsIgnoreCase("Action")) {
            return new ActionMovie();
        }
        // Check if the genre is "Comedy" (case-insensitive) and return a ComedyMovie instance
        else if (genre.equalsIgnoreCase("Comedy")) {
            return new ComedyMovie();
        }
        // Check if the genre is "Drama" (case-insensitive) and return a DramaMovie instance
        else if (genre.equalsIgnoreCase("Drama")) {
            return new DramaMovie();
        }
        // Return null if the genre is not recognized
        return null;
    }
}
