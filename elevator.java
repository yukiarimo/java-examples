class Elevator {
    // The current floor of the elevator
    private int currentFloor = 1;
    private boolean inServiceMode = false;
    private static final int MIN_FLOOR = 1;
    private static final int MAX_FLOOR = 20;

    // Constructor
    public Elevator() {
    }

    // Method to move the elevator to a specific floor
    public void goToFloor(int floor) {
        // Check if the elevator is in service mode, the floor is within the valid range, and the floor is different from the current floor
        if (inServiceMode || floor < MIN_FLOOR || floor > MAX_FLOOR || floor == currentFloor) {
            if (inServiceMode) {
                System.out.println("SERVICE MODE");
            }
            return;
        }

        System.out.println("The elevator moved to floor " + floor + ".");
        currentFloor = floor;
    }

    // Method to set the elevator to service mode
    public void setInServiceMode(boolean inServiceMode) {
        this.inServiceMode = inServiceMode;
    }

    // Method to get the current floor of the elevator
    @Override
    public String toString() {
        if (inServiceMode) {
            return "The elevator is in service mode on floor " + currentFloor + ".";
        } else {
            return "The elevator is on floor " + currentFloor + ".";
        }
    }
}

// SmartElevator class that extends the Elevator class
class SmartElevator extends Elevator {
    @Override
    public void goToFloor(int floor) {
        int currentFloor = getCurrentFloor();
        // Print the floors the elevator will pass through
        if (floor != currentFloor) {
            if (floor > currentFloor) {
                for (int i = currentFloor + 1; i <= floor; i++) {
                    System.out.print(i + " ");
                }
            } else {
                for (int i = currentFloor - 1; i >= floor; i--) {
                    System.out.print(i + " ");
                }
            }
            System.out.println(); // New line after printing floors
        }
        super.goToFloor(floor);
    }

    // Method to get the current floor of the elevator
    private int getCurrentFloor() {
        // Reflection to access the parent's private field
        try {
            // Get the field
            java.lang.reflect.Field field = Elevator.class.getDeclaredField("currentFloor");
            field.setAccessible(true);
            return field.getInt(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Elevator e = new Elevator();
        System.out.println(e);
        e.goToFloor(20);
        System.out.println(e);
        e.setInServiceMode(true);
        System.out.println(e);
        e.goToFloor(6);
        e.setInServiceMode(false);
        e.goToFloor(1);
        System.out.println(e);

        System.out.println("");

        // SmartElevator
        SmartElevator s = new SmartElevator();
        s.goToFloor(10);
        System.out.println(s);
        s.goToFloor(3);
        System.out.println(s);
    }
}
