package com.example.getawaydrives.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer ownerId;
    private String type;
    private String licensePlateNumber;
    private String build; //make
    private String model;
    private int year;
    private String city;
    private String province;
    private double price;
    private String fuelConsumption;
    private String vin;
    private int longestTripDur;
    private int shortestTripDur;
    private String transmissionType;
    //private String vehicleType;
    private int odometer;
    private int status;
    private Integer createdBy;
    private Date createdOn;
    private Integer lastUpdatedBy;
    private Date lastUpdatedOn;
    @Transient
    private MultipartFile[] pics;
    @Transient
    private MultipartFile[] docs;
}
