package com.musala.droneproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
   * <p> It has been assumed that drone in IDLE and LOADED state are, only available for loading </p>
   *
   * @param state
   * @return
   */
  @GetMapping()
  public ResponseEntity<List<Drone>> getDrones(@RequestParam(required = false, name = "state") String state)
  {
    if (state != null && state.equals("available"))
    {
      return new ResponseEntity<>(droneService.getAvailableDrones(), HttpStatus.OK);
    }
    return new ResponseEntity<>(droneService.getDrones(), HttpStatus.OK);
  }

  /**
   * Task1: Register a drone
   *
   * @param drone
   */
  @PostMapping
  public ResponseEntity<Drone> registerNewDrone(@RequestBody Drone drone)
  {
    return new ResponseEntity<>(droneService.registerNewDrone(drone), HttpStatus.CREATED);
  }

  @GetMapping("/{droneId}/battery")
  public ResponseEntity<Integer> getBatteryLevel(@PathVariable Long droneId)
  {
    return new ResponseEntity<>(droneService.getBatteryLevelById(droneId), HttpStatus.OK);
  }

  /**
   * Task 2: loading a drone with medication items
   *
   * @param droneId
   * @param ids
   */
  @PutMapping(value = "/{droneId}", params = "ids")
  public ResponseEntity<Boolean> loadDroneWithMedications(@PathVariable Long droneId, @RequestParam List<Long> ids)
  {
    boolean isLoaded = droneService.loadDroneWithMedications(droneId, ids);
    return new ResponseEntity<>(isLoaded, isLoaded ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
  }
}
