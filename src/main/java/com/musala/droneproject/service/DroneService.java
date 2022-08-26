package com.musala.droneproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.droneproject.model.Drone;
import com.musala.droneproject.repository.DroneRepository;

@Service
public class DroneService
{

  private final DroneRepository droneRepository;

  @Autowired
  public DroneService(DroneRepository droneRepository)
  {
    this.droneRepository = droneRepository;
  }

  public List<Drone> getDrones()
  {
    return droneRepository.findAll();
  }

  public void registerNewDrone(Drone drone)
  {
    Optional<Drone> droneBySerialNumber = droneRepository.findDroneBySerialNumber(drone.getSerialNumber());
    if(droneBySerialNumber.isPresent())
    {
      throw new IllegalStateException("Drones cannot have same serial number!");
    }
    droneRepository.save(drone);
  }
}
