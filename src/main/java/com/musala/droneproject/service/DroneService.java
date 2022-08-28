package com.musala.droneproject.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.droneproject.model.Drone;
import com.musala.droneproject.model.DroneState;
import com.musala.droneproject.model.Medication;
import com.musala.droneproject.repository.DroneRepository;
import com.musala.droneproject.repository.MedicationRepository;

@Service
public class DroneService
{

  private final DroneRepository droneRepository;

  private final MedicationRepository medicationRepository;

  ExecutorService executorService = Executors.newFixedThreadPool(10);

  @Autowired
  public DroneService(DroneRepository droneRepository, MedicationRepository medicationRepository)
  {
    this.droneRepository = droneRepository;
    this.medicationRepository = medicationRepository;
  }

  public List<Drone> getDrones()
  {
    return droneRepository.findAll();
  }

  public void registerNewDrone(Drone drone)
  {
    Optional<Drone> droneBySerialNumber = droneRepository.findDroneBySerialNumber(drone.getSerialNumber());
    if (droneBySerialNumber.isPresent())
    {
      throw new IllegalStateException("Drones cannot have same serial number!");
    }
    if (drone.getWeightLimit() > 500.0)
    {
      throw new IllegalStateException("Drone's max weight has exceeded 500gr!");
    }
    droneRepository.save(drone);
  }

  public List<Drone> getAvailableDrones()
  {
    // It has assumed that drone in IDLE and LOADED state are, only available for loading
    Optional<List<Drone>> optionalDrones = droneRepository.findAllDronesByStateIn(
        Arrays.asList(DroneState.IDLE, DroneState.LOADED));
    return optionalDrones.orElse(Collections.emptyList());
  }

  public Integer getBatteryLevelById(Long droneId)
  {
    return droneRepository.findById(droneId).map(Drone::getBatteryCapacity).orElse(null);
  }

  @Transactional
  public void loadDroneWithMedications(Long droneId, List<Long> medicationIds)
  {
    Optional<Drone> optionalDrone = droneRepository.findById(droneId);
    if (optionalDrone.isPresent())
    {
      Drone drone = optionalDrone.get();
      drone.setState(DroneState.LOADING);

      final Double droneWeightLimit = drone.getWeightLimit();
      List<Medication> medicationList = medicationRepository.findAllById(medicationIds);
      double newTotalWeight =
          getDroneCurrentTotalWeight(new ArrayList<>(drone.getMedications())) + getDroneCurrentTotalWeight(
              medicationList);

      //Validations
      if (drone.getBatteryCapacity() <= 25)
      {
        throw new IllegalStateException(
            "Drone's battery Capacity is " + drone.getBatteryCapacity() + ", which is less than 25%");
      }
      if (newTotalWeight > droneWeightLimit)
      {
        throw new IllegalStateException(
            "Maximum drone capacity " + droneWeightLimit + " has exceeded with medication " + "total weight: "
                + newTotalWeight);
      }
      //Drone Operation
      medicationList.forEach(medication -> medication.setDrone(drone));
      drone.setState(DroneState.LOADED);
    }
  }

  private Double getDroneCurrentTotalWeight(List<Medication> medicationList)
  {
    return medicationList.stream().map(Medication::getWeight).reduce(0d, Double::sum);
  }
}
