package adapter_pattern;

import singleton_pattern.TicketBookingSystem;

/**
 * adapter_pattern.TicketBookingAdapter implements the Adapter pattern to provide a unified interface
 * for ticket booking operations. It adapts the singleton_pattern.TicketBookingSystem to the adapter_pattern.MovieBooking interface.
 * <p>
 * This adapter ensures compatibility between the adapter_pattern.MovieBooking interface requirements
 * and the existing singleton_pattern.TicketBookingSystem implementation.
 */
public class TicketBookingAdapter implements MovieBooking {
    // Reference to the singleton instance of singleton_pattern.TicketBookingSystem
    private TicketBookingSystem ticketBookingSystem;

    /**
     * Constructor initializes the adapter with a reference to the singleton_pattern.TicketBookingSystem
     * Uses singleton pattern to get the system instance
     */
    public TicketBookingAdapter() {
        this.ticketBookingSystem = TicketBookingSystem.getInstance();
    }

    /**
     * Adapts the ticket booking request to the singleton_pattern.TicketBookingSystem implementation
     * Implements the adapter_pattern.MovieBooking interface method
     *
     * @param genre        The movie genre for the ticket (e.g., "Action", "Comedy", "Drama")
     * @param theaterType  The type of theater (e.g., "Cinema", "IMAX")
     * @param ticketNumber The number of tickets to book
     * @return String containing the booking confirmation or error message
     */
    @Override
    public String bookTicket(String genre, String theaterType, int ticketNumber) {
        // Delegates the actual booking operation to the singleton_pattern.TicketBookingSystem
        return ticketBookingSystem.bookTicket(genre, theaterType, ticketNumber);
    }
}