package com.example.getawaydrives.web;

import com.example.getawaydrives.entities.DocumentType;
import com.example.getawaydrives.entities.User;
import com.example.getawaydrives.entities.Vehicle;
import com.example.getawaydrives.repositories.DocumentRepository;
import com.example.getawaydrives.repositories.VehicleRepository;
import com.example.getawaydrives.service.UserServiceImpl;
import com.example.getawaydrives.service.VehicleServiceImpl;
import com.example.getawaydrives.utility.CommonMethods;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class VehicleController {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private DocumentRepository docRepo;

    @GetMapping(path = "/index")
    public String vehicles(Model model) {

        List<Vehicle> cars;
            cars = vehicleRepository.findAll();

        model.addAttribute("listCars", cars);
        return "index";
    }
    @GetMapping(path = "/byBuild")
    public String build(Model model, @RequestParam(name = "build", defaultValue = "")
    String keyword){
        List<Vehicle> cars;

        if (keyword.isEmpty()) {
            cars = vehicleRepository.findAll();
        } else {
            cars = vehicleRepository.findByBuild(keyword);
        }

        model.addAttribute("by_build",cars);
        return "byBuild";
    }
    @GetMapping(path = "/types")
    public String types(Model model, @RequestParam(name = "type", defaultValue = "")
    String keyword){
        List<Vehicle> cars;

        if (keyword.isEmpty()) {
            cars = vehicleRepository.findAll();
        } else {
            cars = vehicleRepository.findByType(keyword);
        }
        model.addAttribute("by_type",cars);
        return "types";
    }
    @GetMapping(path = "/details")
    public String details(Model model){

        return "details";
    }

    @GetMapping(path = "/listCar")
    public String listCar(Model model){

        return "listCar";
    }

    @GetMapping("/deleteCar")
    public String deleteCar(Long id){
        vehicleRepository.deleteById(id);

        return "redirect:/index";
    }

    @GetMapping(path = "/search")
    public String search(Model model, @RequestParam(name = "keyword", defaultValue = "")
    String keyword) {

        List<Vehicle> cars;
        if (keyword.isEmpty()) {
            cars = vehicleRepository.findAll();
        } else {
            String key = keyword;
            cars = vehicleRepository.findByModelContaining(key);
        }
        model.addAttribute("list_cars", cars);
        return "generalSearch";
    }

    @PostMapping(path = "/addVehicle")
    public String addVehicle(Model model, @Valid Vehicle vehicle, Integer id, BindingResult
            bindingResult, ModelMap mm, HttpSession session) {
        id = 7;
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "index";
        } else {
            Vehicle v = VehicleServiceImpl.addVehicle(vehicle, vehicleRepository, id);
            if(v != null && vehicle.getPics() != null){
                CommonMethods.createDocument(docRepo, vehicle.getPics(), id, v.getId(),
                        DocumentType.VEHICLE_PHOTOS.getId());
            }
            if(v != null && vehicle.getDocs() != null){
                CommonMethods.createDocument(docRepo, vehicle.getDocs(), id, v.getId(),
                        DocumentType.INSURANCE.getId());
            }
            return "redirect:index";
        }

    }

}
