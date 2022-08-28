package com.musala.droneproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.musala.droneproject.model.Drone;
import com.musala.droneproject.model.dto.DroneDTO;

@Component
public interface DroneService
{
  List<Drone> getDrones();

  Drone registerNewDrone(DroneDTO droneDTO);

  List<Drone> getAvailableDrones();

  Integer getBatteryLevelById(Long droneId);

  @Transactional
  boolean loadDroneWithMedications(Long droneId, List<Long> medicationIds);
}
