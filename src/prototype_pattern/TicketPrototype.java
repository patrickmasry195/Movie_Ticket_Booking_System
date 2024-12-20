package prototype_pattern;

// Interface defining the Prototype design pattern for ticket objects
public interface TicketPrototype {

    /**
     * Method to create a clone of the implementing ticket object.
     *
     * @return A new instance of the ticket object with the same properties as the original.
     */
    TicketPrototype cloneTicket();
}
