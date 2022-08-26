package com.musala.droneproject.drone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  }
}
