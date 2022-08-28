package com.musala.droneproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.musala.droneproject.model.Drone;
import com.musala.droneproject.service.DroneService;

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

  /**
   * Task 4: checking available drones for loading
   * @param state
   * @return
   */
  @GetMapping()
  public List<Drone> getDrones(@RequestParam(required = false, name = "state") String state)
  {
    if (state != null && !state.isEmpty() && state.equals("available"))
    {
      return droneService.getAvailableDrones();
    }
    return droneService.getDrones();
  }

  /**
   * Task1: Register a drone
   *
   * @param drone
   */
  @PostMapping
  public void registerNewDrone(@RequestBody Drone drone)
  {
    droneService.registerNewDrone(drone);
  }
}
