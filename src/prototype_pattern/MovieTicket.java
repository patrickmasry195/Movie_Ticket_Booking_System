package prototype_pattern;

// MovieTicket class implements the TicketPrototype interface, supporting cloning of ticket objects
public class MovieTicket implements TicketPrototype {
    private String genre;        // Genre of the movie (e.g., Action, Drama)
    private String theaterType;  // Type of theater (e.g., IMAX, Standard)
    private int ticketNumber;    // Unique ticket number

    /**
     * Constructor to initialize a MovieTicket with its details.
     *
     * @param genre        The genre of the movie.
     * @param theaterType  The type of theater.
     * @param ticketNumber The unique ticket number.
     */
    public MovieTicket(String genre, String theaterType, int ticketNumber) {
        this.genre = genre;
        this.theaterType = theaterType;
        this.ticketNumber = ticketNumber;
    }

    /**
     * Creates a clone of the current MovieTicket instance.
     *
     * @return A new MovieTicket object with the same attributes as the original.
     */
    @Override
    public TicketPrototype cloneTicket() {
        return new MovieTicket(this.genre, this.theaterType, this.ticketNumber);
    }

    /**
     * Returns a string representation of the MovieTicket object.
     *
     * @return A string describing the ticket details.
     */
    @Override
    public String toString() {
        return "Ticket [Genre: " + genre + ", Theater: " + theaterType + ", Ticket Number: " + ticketNumber + "]";
    }
}
