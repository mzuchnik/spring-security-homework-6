package pl.mzuchnik.springsecurityhomework6.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import pl.mzuchnik.springsecurityhomework6.model.Car;
import pl.mzuchnik.springsecurityhomework6.model.CarColor;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitService {

    @Bean
    public List<Car> generateCarList()
    {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car("Honda", CarColor.BLACK));
        cars.add(new Car("Hyundai", CarColor.GREEN));
        cars.add(new Car("BMW", CarColor.RED));
        cars.add(new Car("MAZDA", CarColor.WHITE));
        cars.add(new Car("CITROEN", CarColor.YELLOW));

        return cars;
    }
}
