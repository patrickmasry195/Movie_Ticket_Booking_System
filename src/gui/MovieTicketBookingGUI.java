package gui;

import builder_pattern.MovieTicketBuilder;
import movie_factory_pattern.Movie;
import movie_factory_pattern.MovieFactory;
import prototype_pattern.MovieTicket;
import singleton_pattern.SessionManager;
import singleton_pattern.TicketBookingSystem;
import theatre_factory_pattern.Theater;
import theatre_factory_pattern.TheaterFactory;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Main GUI class for the Movie Ticket Booking System
 * Implements a user interface with login, user, and admin functionalities
 */
public class MovieTicketBookingGUI {
    // GUI Components
    private JFrame frame;
    private JTextField ticketNumberField, usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> genreComboBox, theaterComboBox;
    private JLabel bookedTicketsLabel;

    // System Components using Singleton pattern
    private TicketBookingSystem ticketBookingSystem = TicketBookingSystem.getInstance();
    private SessionManager sessionManager = SessionManager.getInstance();

    /**
     * Constructor: Initializes the main application window
     */
    public MovieTicketBookingGUI() {
        frame = new JFrame("Movie Ticket Booking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        showLogPanel();  // Display log panel first
        frame.setLayout(null);
        frame.setVisible(true);
    }

    /**
     * Creates and displays the login panel with username and password fields
     */
    private void showLogPanel() {
        JPanel logPanel = createPanel();

        // Username label and text field
        logPanel.add(new JLabel("Username:")).setBounds(50, 50, 100, 20);
        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 150, 20);
        logPanel.add(usernameField);

        // Password label and password field
        logPanel.add(new JLabel("Password:")).setBounds(50, 80, 100, 20);
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 80, 150, 20);
        logPanel.add(passwordField);

        // Login button
        addButton(logPanel, "Login", 150, 110, e -> loginUser());

        frame.setContentPane(logPanel);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Validates user credentials and proceeds to login panel if successful
     */
    private void loginUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (sessionManager.login(username, password)) {
            JOptionPane.showMessageDialog(frame, "Login successful!");
            showLoginPanel();
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays the main menu panel after successful login
     * Shows options for User and Admin access
     */
    private void showLoginPanel() {
        JPanel panel = createPanel();
        addButton(panel, "User", 150, 50, e -> showUserPanel());
        addButton(panel, "Admin", 150, 100, e -> showAdminPanel());
        addButton(panel, "Logout", 150, 150, e -> logoutUser());

        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Handles user logout and returns to login screen
     */
    private void logoutUser() {
        sessionManager.logout(); // Clear the session
        JOptionPane.showMessageDialog(frame, "Logged out successfully!");
        showLogPanel(); // Show the log panel again
    }

    /**
     * Creates a new panel with null layout
     *
     * @return JPanel with null layout
     */
    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        return panel;
    }

    /**
     * Helper method to add buttons with specific properties
     *
     * @param panel    Panel to add button to
     * @param text     Button text
     * @param x        X coordinate
     * @param y        Y coordinate
     * @param listener Action listener for button
     */
    private void addButton(JPanel panel, String text, int x, int y, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 150, 30);
        button.addActionListener(listener);
        panel.add(button);
    }

    /**
     * Displays the user panel with ticket booking options
     */
    private void showUserPanel() {
        JPanel userPanel = createPanel();

        genreComboBox = addComboBox(userPanel, "Genre:", new String[]{"Action", "Comedy", "Drama"}, 50, 50);
        theaterComboBox = addComboBox(userPanel, "Theater Type:", new String[]{"Cinema", "IMAX"}, 50, 80);
        ticketNumberField = addTextField(userPanel, "Ticket Number:", 50, 110);
        bookedTicketsLabel = new JLabel("Booked Tickets: 0");
        bookedTicketsLabel.setBounds(50, 140, 200, 20);
        userPanel.add(bookedTicketsLabel);

        addButton(userPanel, "Book Ticket", 150, 170, e -> bookTicketForUser());
        addButton(userPanel, "Back", 10, 10, e -> showLoginPanel());

        frame.setContentPane(userPanel);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Helper method to add text fields with labels
     *
     * @param panel     Panel to add field to
     * @param labelText Label text
     * @param x         X coordinate
     * @param y         Y coordinate
     * @return Created JTextField
     */
    private JTextField addTextField(JPanel panel, String labelText, int x, int y) {
        panel.add(new JLabel(labelText)).setBounds(x, y, 100, 20);
        JTextField textField = new JTextField();
        textField.setBounds(x + 100, y, 150, 20);
        panel.add(textField);
        return textField;
    }

    /**
     * Helper method to add combo boxes with labels
     *
     * @param panel     Panel to add combo box to
     * @param labelText Label text
     * @param items     Combo box items
     * @param x         X coordinate
     * @param y         Y coordinate
     * @return Created JComboBox
     */
    private JComboBox<String> addComboBox(JPanel panel, String labelText, String[] items, int x, int y) {
        panel.add(new JLabel(labelText)).setBounds(x, y, 100, 20);
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setBounds(x + 100, y, 150, 20);
        panel.add(comboBox);
        return comboBox;
    }

    /**
     * Displays the admin panel with inventory management option
     */
    private void showAdminPanel() {
        JPanel adminPanel = createPanel();
        addButton(adminPanel, "Manage Inventory", 150, 100, e -> manageTicketInventory());
        addButton(adminPanel, "Back", 10, 10, e -> showLoginPanel());
        frame.setContentPane(adminPanel);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Displays the inventory management panel for admins
     */
    private void manageTicketInventory() {
        JPanel inventoryPanel = createPanel();

        // Add IMAX title
        JLabel cinemaTitleLabel = new JLabel("IMAX");
        cinemaTitleLabel.setBounds(260, 17, 70, 20);
        cinemaTitleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 13));
        inventoryPanel.add(cinemaTitleLabel);

        // Add Cinema title
        JLabel imaxTitleLabel = new JLabel("Cinema");
        imaxTitleLabel.setBounds(147, 15, 70, 20);
        imaxTitleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        inventoryPanel.add(imaxTitleLabel);

        // Add ticket information for each genre and theater type
        String[] genres = {"Action", "Comedy", "Drama"};
        int yPos = 50;
        for (String genre : genres) {
            addTicketInfo(inventoryPanel, genre + "-Cinema", 50, yPos);
            addTicketInfo(inventoryPanel, genre + "-IMAX", 150, yPos);
            yPos += 80;
        }

        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 70, 20);
        backButton.addActionListener(e -> showAdminPanel());
        inventoryPanel.add(backButton);

        frame.setContentPane(inventoryPanel);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Adds ticket information display for a specific genre and theater type
     *
     * @param panel     Panel to add information to
     * @param ticketKey Combined key of genre and theater type
     * @param x         X coordinate
     * @param y         Y coordinate
     */
    private void addTicketInfo(JPanel panel, String ticketKey, int x, int y) {
        // Extract genre from ticketKey (before "-")
        String genre = ticketKey.split("-")[0];
        // Extract theater type from ticketKey (after "-")
        String theaterType = ticketKey.split("-")[1];
        // Create label for movie genre
        JLabel label = new JLabel(genre);
        label.setBounds(x, y, 100, 20);
        panel.add(label);
        // Get available tickets for this type
        int availableTickets = ticketBookingSystem.getAvailableTickets(genre, theaterType);
        // Create text field to display available tickets
        JTextField ticketField = new JTextField(String.valueOf(availableTickets));
        ticketField.setBounds(x + 100, y, 50, 20);
        ticketField.setEditable(false);
        panel.add(ticketField);
    }

    /**
     * Handles the ticket booking process for users
     * Implements the Builder pattern for ticket creation
     */
    private void bookTicketForUser() {
        String genre = (String) genreComboBox.getSelectedItem();
        String theaterType = (String) theaterComboBox.getSelectedItem();
        int ticketNumber;

        try {
            ticketNumber = Integer.parseInt(ticketNumberField.getText());
            if (ticketNumber < 1 || ticketNumber > 50) {
                showError("Ticket number must be between 1 and 50.");
                return;
            }
        } catch (NumberFormatException e) {
            showError("Please enter a valid ticket number.");
            return;
        }

        // Create movie and theater using respective factories
        Movie movie = MovieFactory.createMovie(genre);
        Theater theater = TheaterFactory.createTheater(theaterType);

        if (movie != null) movie.showDetails();
        if (theater != null) theater.showDetails();

        // Use the Builder pattern to create MovieTicket
        MovieTicket movieTicket = new MovieTicketBuilder()
                .setGenre(genre)
                .setTheaterType(theaterType)
                .setTicketNumber(ticketNumber)
                .build();

        String message = ticketBookingSystem.bookTicket(genre, theaterType, ticketNumber);
        JOptionPane.showMessageDialog(frame, message);

        // Update display of booked tickets
        updateBookedTicketsLabel(genre, theaterType);
    }

    /**
     * Displays error message dialog
     *
     * @param message Error message to display
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Updates the label showing number of booked tickets
     *
     * @param genre       Movie genre
     * @param theaterType Theater type
     */
    private void updateBookedTicketsLabel(String genre, String theaterType) {
        int bookedCount = ticketBookingSystem.getBookedTickets(genre, theaterType);
        bookedTicketsLabel.setText("Booked Tickets: " + bookedCount);
    }

    /**
     * Main method to launch the application
     */
    public static void main(String[] args) {
        new MovieTicketBookingGUI();
    }
}