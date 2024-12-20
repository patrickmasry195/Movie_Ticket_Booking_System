package singleton_pattern;

/**
 * singleton_pattern.TicketBookingSystem implements the core ticket booking functionality using the Singleton pattern.
 * Manages ticket inventory and booking status for different movie genres and theater types.
 * Uses multi-dimensional arrays to track available tickets and booking status.
 */
public class TicketBookingSystem {
    // Singleton instance
    private static TicketBookingSystem instance;

    // 2D array to track available tickets for each genre and theater type
    // First dimension (3): Movie genres (Action, Comedy, Drama)
    // Second dimension (2): Theater types (Cinema, IMAX)
    private int[][] availableTickets = new int[3][2];

    // 3D array to track individual ticket numbers' booking status
    // First dimension (3): Movie genres (Action, Comedy, Drama)
    // Second dimension (2): Theater types (Cinema, IMAX)
    // Third dimension (50): Individual ticket numbers (1-50)
    private boolean[][][] bookedTicketNumbers = new boolean[3][2][50];

    /**
     * Private constructor for Singleton pattern
     * Initializes the ticket inventory
     */
    private TicketBookingSystem() {
        initializeTickets();
    }

    /**
     * Gets the singleton instance of singleton_pattern.TicketBookingSystem
     * Creates new instance if none exists (lazy initialization)
     *
     * @return The singleton instance of singleton_pattern.TicketBookingSystem
     */
    public static TicketBookingSystem getInstance() {
        if (instance == null) {
            instance = new TicketBookingSystem();
        }
        return instance;
    }

    /**
     * Initializes the ticket inventory
     * Sets available tickets to 50 for each genre and theater type combination
     */
    private void initializeTickets() {
        for (int i = 0; i < 3; i++) {            // Iterate through genres
            for (int j = 0; j < 2; j++) {        // Iterate through theater types
                availableTickets[i][j] = 50;     // Set initial capacity to 50
            }
        }
    }

    /**
     * Processes a ticket booking request
     * Validates the request and updates booking status if successful
     *
     * @param genre        Movie genre ("Action", "Comedy", "Drama")
     * @param theaterType  Theater type ("Cinema", "IMAX")
     * @param ticketNumber Specific ticket number (1-50)
     * @return String message indicating booking status or error
     */
    public String bookTicket(String genre, String theaterType, int ticketNumber) {
        // Get array indices for genre and theater type
        int genreIndex = getGenreIndex(genre);
        int theaterIndex = getTheaterIndex(theaterType);

        // Validate genre and theater selection
        if (genreIndex == -1 || theaterIndex == -1) {
            return "Invalid selection.";
        }

        // Validate ticket number range
        if (ticketNumber < 1 || ticketNumber > 50) {
            return "Ticket number must be between 1 and 50.";
        }

        // Check if ticket is already booked
        if (bookedTicketNumbers[genreIndex][theaterIndex][ticketNumber - 1]) {
            return "This ticket number is already booked.";
        }

        // Process booking if tickets are available
        if (availableTickets[genreIndex][theaterIndex] > 0) {
            bookedTicketNumbers[genreIndex][theaterIndex][ticketNumber - 1] = true;
            availableTickets[genreIndex][theaterIndex]--;
            return "Ticket booked successfully!";
        } else {
            return "No remaining tickets for this selection.";
        }
    }

    /**
     * Gets the number of available tickets for a specific genre and theater type
     *
     * @param genre       Movie genre
     * @param theaterType Theater type
     * @return Number of available tickets, 0 if invalid selection
     */
    public int getAvailableTickets(String genre, String theaterType) {
        int genreIndex = getGenreIndex(genre);
        int theaterIndex = getTheaterIndex(theaterType);
        if (genreIndex == -1 || theaterIndex == -1) return 0;
        return availableTickets[genreIndex][theaterIndex];
    }

    /**
     * Gets the number of booked tickets for a specific genre and theater type
     *
     * @param genre       Movie genre
     * @param theaterType Theater type
     * @return Number of booked tickets, 0 if invalid selection
     */
    public int getBookedTickets(String genre, String theaterType) {
        int genreIndex = getGenreIndex(genre);
        int theaterIndex = getTheaterIndex(theaterType);

        if (genreIndex == -1 || theaterIndex == -1) return 0;

        // Count booked tickets
        int bookedCount = 0;
        for (int i = 0; i < 50; i++) {
            if (bookedTicketNumbers[genreIndex][theaterIndex][i]) {
                bookedCount++;
            }
        }
        return bookedCount;
    }

    /**
     * Converts genre string to array index
     *
     * @param genre Movie genre
     * @return Array index (0-2) for genre, -1 if invalid
     */
    private int getGenreIndex(String genre) {
        switch (genre) {
            case "Action":
                return 0;
            case "Comedy":
                return 1;
            case "Drama":
                return 2;
            default:
                return -1;
        }
    }

    /**
     * Converts theater type string to array index
     *
     * @param theater Theater type
     * @return Array index (0-1) for theater type, -1 if invalid
     */
    private int getTheaterIndex(String theater) {
        switch (theater) {
            case "Cinema":
                return 0;
            case "IMAX":
                return 1;
            default:
                return -1;
        }
    }
}