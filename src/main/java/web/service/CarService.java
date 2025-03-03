package web.service;

import web.model.Car;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CarService {
    private final List<Car> cars = Arrays.asList(
            new Car("Toyota", "Camry", 2020),
            new Car("Honda", "Civic", 2019),
            new Car("Ford", "Focus", 2018),
            new Car("BMW", "X5", 2021),
            new Car("Audi", "A4", 2022)
    );

    public List<Car> getCars(int count) {
        return cars.subList(0, Math.min(count, cars.size()));
    }
}
