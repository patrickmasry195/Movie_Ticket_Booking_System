package adapter_pattern;

/**
 * Interface for booking movie tickets.
 * Provides a method to book tickets based on genre, theater type, and ticket number.
 */
public interface MovieBooking {

    /**
     * Books a ticket for a movie.
     *
     * @param genre the genre of the movie (e.g., Action, Comedy, Drama)
     * @param theaterType the type of theater (e.g., IMAX, Standard)
     * @param ticketNumber the number of tickets to book
     * @return a confirmation string for the booking
     */
    String bookTicket(String genre, String theaterType, int ticketNumber);
}
