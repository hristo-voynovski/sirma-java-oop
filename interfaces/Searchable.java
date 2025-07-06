package interfaces;

import models.Car;
import java.util.List;

public interface Searchable {
    List<Car> searchByModel(String model);
    Car searchById(int id);
}