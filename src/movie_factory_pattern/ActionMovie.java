package movie_factory_pattern;

/**
 * ActionMovie class represents a concrete implementation of the Movie abstract class
 * Specifically handles action genre movies in the movie ticket booking system
 * Part of the Factory Method pattern implementation for movie creation
 */
public class ActionMovie extends Movie {

    /**
     * Implementation of the abstract showDetails method from Movie class
     * Displays specific details related to action movies
     * This method is called when action movie information needs to be presented
     */
    @Override
    public void showDetails() {
        System.out.println("Action Movie details");
    }
}