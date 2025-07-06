package interfaces;

public interface Rentable {
    void rent(int carId, String customerName, String rentalStartDate, String estimatedReturnDate);
    void rent(int carId, String customerName);
    void returnCar(int carId);
}