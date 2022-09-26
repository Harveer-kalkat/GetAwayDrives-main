package com.example.getawaydrives.service;

import com.example.getawaydrives.entities.User;
import com.example.getawaydrives.entities.Vehicle;
import com.example.getawaydrives.repositories.VehicleRepository;

import java.util.Date;
import java.util.List;

public class VehicleServiceImpl {
    public static Vehicle addVehicle(Vehicle vehicle, VehicleRepository repository, Integer userId) {
        try {
            if (checkIfVehicleExists(vehicle.getLicensePlateNumber(), repository)) {
                throw new Exception("Vehicle already exists!");
            }
            vehicle.setStatus(1);
            vehicle.setOwnerId(userId);
            vehicle.setCreatedOn(new Date());
            vehicle.setLastUpdatedOn(new Date());

            Vehicle v = repository.save(vehicle);
            v.setCreatedBy(userId);
            v.setLastUpdatedBy(userId);
            repository.save(v);
            return v;
        } catch (Exception e) {
            System.out.println(e.getMessage()); //TODO show this message
        }
        return null;
    }

    private static boolean checkIfVehicleExists(String licensePlateNumber, VehicleRepository repo) {
        List<Vehicle> vehicles = repo.findVehicleByLicensePlateNumber(licensePlateNumber);
        if (vehicles.isEmpty()) {
            return false;
        }
        return true;
    }
}
