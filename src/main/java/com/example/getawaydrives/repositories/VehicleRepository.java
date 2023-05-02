package com.example.getawaydrives.repositories;

import com.example.getawaydrives.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    List<Vehicle> findByModelContaining(String kw);

    List<Vehicle> findVehicleByLicensePlateNumber(String licensePlateNumber);
//    List<Vehicle> findByNameContaining (String kw);
    List<Vehicle> findByBuild(String build);
    List<Vehicle> findByType(String type);
}
