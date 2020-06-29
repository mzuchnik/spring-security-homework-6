package pl.mzuchnik.springsecurityhomework6.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mzuchnik.springsecurityhomework6.model.Car;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SimpleApi {

    private List<Car> carList;

    @Autowired
    public SimpleApi(List<Car> carList) {
        this.carList = carList;
    }

    @GetMapping("/cars")
    public List<Car> getAllCars()
    {
        return carList;
    }

    @PostMapping("/cars")
    public void addNewCar(){

    }



}
