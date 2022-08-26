package com.musala.droneproject.drone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/drone")
public class DroneController
{

  private final DroneService droneService;

  @Autowired
  public DroneController(DroneService droneService)
  {
    this.droneService = droneService;
  }

  @GetMapping
  public List<Drone> getDrones()
  {
    return droneService.getDrones();
  }

  public void registerNewDrone(Drone drone)
  {
      droneService.registerNewDrone(drone);
  }
}
