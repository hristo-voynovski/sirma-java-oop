package models;

public class Car {
    private int id;
    private String make;
    private String model;
    private int year;
    private String type;
    private String status;
    private String currentRenter;
    private String rentalStartDate;
    private String estimatedReturnDate;

    /**
     * Create a new car with all details
     */
    public Car(int id, String make, String model, int year, String type, String status, String currentRenter, String rentalStartDate, String estimatedReturnDate) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.type = type;
        this.status = status;
        this.currentRenter = currentRenter;
        this.rentalStartDate = rentalStartDate;
        this.estimatedReturnDate = estimatedReturnDate;
    }
    /**
     * Create a new car (without rental dates)
     */
    public Car(int id, String make, String model, int year, String type, String status, String currentRenter) {
        this(id, make, model, year, type, status, currentRenter, "", "");
    }

    /**
     * Get the car's unique id
     */
    public int getId() { return id; }
    /**
     * Get the car's make (brand)
     */
    public String getMake() { return make; }
    /**
     * Get the car's model
     */
    public String getModel() { return model; }
    /**
     * Get the car's production year
     */
    public int getYear() { return year; }
    /**
     * Get the car's type (e.g. sedan, SUV)
     */
    public String getType() { return type; }
    /**
     * Get the car's current status (Available, Rented, Removed)
     */
    public String getStatus() { return status; }
    /**
     * Get the name of the current renter (if any)
     */
    public String getCurrentRenter() { return currentRenter; }
    /**
     * Get the rental start date (if rented)
     */
    public String getRentalStartDate() { return rentalStartDate; }
    /**
     * Get the estimated return date (if rented)
     */
    public String getEstimatedReturnDate() { return estimatedReturnDate; }

    /**
     * Set the car's unique id
     */
    public void setId(int id) { this.id = id; }
    /**
     * Set the car's make (brand)
     */
    public void setMake(String make) { this.make = make; }
    /**
     * Set the car's model
     */
    public void setModel(String model) { this.model = model; }
    /**
     * Set the car's production year
     */
    public void setYear(int year) { this.year = year; }
    /**
     * Set the car's type (e.g. sedan, SUV)
     */
    public void setType(String type) { this.type = type; }
    /**
     * Set the car's status (Available, Rented, Removed)
     */
    public void setStatus(String status) { this.status = status; }
    /**
     * Set the name of the current renter
     */
    public void setCurrentRenter(String renter) { this.currentRenter = renter; }
    /**
     * Set the rental start date
     */
    public void setRentalStartDate(String date) { this.rentalStartDate = date; }
    /**
     * Set the estimated return date
     */
    public void setEstimatedReturnDate(String date) { this.estimatedReturnDate = date; }

    @Override
    public String toString() {
        return id + ": " + make + " " + model + " (" + year + ") [" + type + "] - " + status +
                (currentRenter != null && !currentRenter.isEmpty() ? " (Rented by " + currentRenter + ")" : "") +
                (rentalStartDate != null && !rentalStartDate.isEmpty() ? ", Start: " + rentalStartDate : "") +
                (estimatedReturnDate != null && !estimatedReturnDate.isEmpty() ? ", Est. Return: " + estimatedReturnDate : "");
    }

    /**
     * Convert the car to a CSV line for file storage
     */
    public String toCSV() {
        return id + "," + make + "," + model + "," + year + "," + type + "," + status + "," + currentRenter + "," + rentalStartDate + "," + estimatedReturnDate;
    }

    /**
     * Create a car object from a CSV line
     */
    public static Car fromCSV(String line) {
        String[] parts = line.split(",", -1);
        return new Car(
            Integer.parseInt(parts[0]), parts[1], parts[2], Integer.parseInt(parts[3]),
            parts[4], parts[5], parts.length > 6 ? parts[6] : "",
            parts.length > 7 ? parts[7] : "",
            parts.length > 8 ? parts[8] : ""
        );
    }
}