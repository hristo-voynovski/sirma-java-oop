import managers.RentalManager;
import services.CarRentalService;
import services.FileService;

public class CarRentalApp {
    public static void main(String[] args) {
        FileService fileService = new FileService("cars.csv");
        CarRentalService service = new CarRentalService(fileService.readCars());
        RentalManager manager = new RentalManager(service, fileService);
        manager.start();
    }
}