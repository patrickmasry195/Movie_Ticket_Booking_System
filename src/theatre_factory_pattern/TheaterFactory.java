package theatre_factory_pattern;

// Factory class to create Theater objects based on the specified type
public class TheaterFactory {

    /**
     * Factory method to create a Theater object based on the provided type.
     *
     * @param type The type of theater (e.g., "Cinema", "IMAX").
     * @return A Theater object corresponding to the type, or null if the type is not recognized.
     */
    public static Theater createTheater(String type) {
        // Check if the type is "Cinema" (case-insensitive) and return a CinemaHall instance
        if (type.equalsIgnoreCase("Cinema")) {
            return new CinemaHall();
        }
        // Check if the type is "IMAX" (case-insensitive) and return an IMAXTheater instance
        else if (type.equalsIgnoreCase("IMAX")) {
            return new IMAXTheater();
        }
        // Return null if the type is not recognized
        return null;
    }
}
