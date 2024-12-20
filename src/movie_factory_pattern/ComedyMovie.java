package movie_factory_pattern;

/**
 * ComedyMovie class represents a concrete implementation of the Movie abstract class
 * Specifically handles comedy genre movies in the movie ticket booking system
 * Part of the Factory Method pattern implementation for movie creation
 */
public class ComedyMovie extends Movie {

    /**
     * Implementation of the abstract showDetails method from Movie class
     * Displays specific details related to comedy movies
     * This method is called when comedy movie information needs to be presented
     */
    @Override
    public void showDetails() {
        System.out.println("Comedy Movie details");
    }
}