package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.List;

@Controller
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String getCars(@RequestParam(name = "count", required = false, defaultValue = "-1") int count, Model model) {
        List<Car> cars;
        if (count >= 5) {
            cars = carService.getAllCars();
        } else if (count > 0) {
            cars = carService.getLimitedCars(count);
        } else {
            cars = carService.getAllCars();
        }
        model.addAttribute("cars", cars);
        return "cars";
    }
}
