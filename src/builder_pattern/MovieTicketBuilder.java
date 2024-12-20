package builder_pattern;

import prototype_pattern.MovieTicket;

/**
 * MovieTicketBuilder implements the Builder pattern to construct MovieTicket objects.
 * Provides a fluent interface for setting ticket properties and creating tickets.
 * This builder ensures a clean and flexible way to create movie tickets with all required attributes.
 */
public class MovieTicketBuilder {
    // Properties for building a MovieTicket
    private String genre;         // Movie genre (e.g., Action, Comedy, Drama)
    private String theaterType;   // Type of theater (e.g., Cinema, IMAX)
    private int ticketNumber;     // Unique ticket identifier

    /**
     * Sets the movie genre for the ticket being built
     * Uses method chaining for fluent interface
     *
     * @param genre The movie genre to set
     * @return The builder instance for method chaining
     */
    public MovieTicketBuilder setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    /**
     * Sets the theater type for the ticket being built
     * Uses method chaining for fluent interface
     *
     * @param theaterType The theater type to set
     * @return The builder instance for method chaining
     */
    public MovieTicketBuilder setTheaterType(String theaterType) {
        this.theaterType = theaterType;
        return this;
    }

    /**
     * Sets the ticket number for the ticket being built
     * Uses method chaining for fluent interface
     *
     * @param ticketNumber The ticket number to set
     * @return The builder instance for method chaining
     */
    public MovieTicketBuilder setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
        return this;
    }

    /**
     * Builds and returns a new MovieTicket instance with the configured properties
     * Creates a new ticket using the Prototype pattern via MovieTicket constructor
     *
     * @return A new MovieTicket instance with the specified properties
     */
    public MovieTicket build() {
        return new MovieTicket(genre, theaterType, ticketNumber);
    }
}