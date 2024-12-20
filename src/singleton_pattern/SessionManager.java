package singleton_pattern;

/**
 * singleton_pattern.SessionManager class implements the Singleton pattern to manage user sessions
 * and authentication in the Movie Ticket Booking System.
 * This ensures only one session management instance exists throughout the application.
 */
public class SessionManager {
    // Single instance of singleton_pattern.SessionManager (Singleton pattern)
    private static SessionManager instance;

    // Stores the currently logged-in user's username
    private String loggedInUser;

    /**
     * Private constructor to prevent instantiation from outside
     * Part of Singleton pattern implementation
     */
    private SessionManager() {
    }

    /**
     * Gets the single instance of singleton_pattern.SessionManager
     * Creates a new instance if none exists (lazy initialization)
     *
     * @return The singleton instance of singleton_pattern.SessionManager
     */
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    /**
     * Authenticates user credentials and establishes a user session
     * Supports two types of users: admin and regular user
     *
     * @param username The username attempting to login
     * @param password The password for authentication
     * @return true if login successful, false otherwise
     */
    public boolean login(String username, String password) {
        // Check credentials for both admin and regular user
        if (("admin".equals(username) && "admin123".equals(password)) ||
                ("user".equals(username) && "user123".equals(password))) {
            loggedInUser = username;
            return true;
        }
        return false;
    }

    /**
     * Checks if there is currently a logged-in user
     *
     * @return true if a user is logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    /**
     * Gets the username of the currently logged-in user
     *
     * @return String containing the username, or null if no user is logged in
     */
    public String getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Checks if the given username has admin privileges
     *
     * @param username The username to check for admin status
     * @return true if the user is an admin, false otherwise
     */
    public boolean isAdmin(String username) {
        return "admin".equals(username);
    }

    /**
     * Ends the current user session
     * Clears the logged-in user information
     */
    public void logout() {
        loggedInUser = null;
    }
}