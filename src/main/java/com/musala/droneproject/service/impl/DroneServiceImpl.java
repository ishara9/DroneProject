package com.musala.droneproject.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.droneproject.mapper.DroneDTOMapper;
import com.musala.droneproject.model.Drone;
import com.musala.droneproject.model.DroneState;
import com.musala.droneproject.model.Medication;
import com.musala.droneproject.model.dto.DroneDTO;
import com.musala.droneproject.repository.DroneRepository;
import com.musala.droneproject.repository.MedicationRepository;
import com.musala.droneproject.service.DroneService;

@Service
public class DroneServiceImpl implements DroneService
{

  private final DroneRepository droneRepository;

  private final MedicationRepository medicationRepository;

  @Autowired
  public DroneServiceImpl(DroneRepository droneRepository, MedicationRepository medicationRepository)
  {
    this.droneRepository = droneRepository;
    this.medicationRepository = medicationRepository;
  }

  @Override
  public List<Drone> getDrones()
  {
    return droneRepository.findAll();
  }

  @Override
  public Drone registerNewDrone(DroneDTO droneDTO)
  {
    Drone drone = DroneDTOMapper.map(droneDTO);
    Optional<Drone> droneBySerialNumber = droneRepository.findDroneBySerialNumber(drone.getSerialNumber());
    if (droneBySerialNumber.isPresent())
    {
      throw new IllegalStateException("Drones cannot have same serial number!");
    }
    if (drone.getWeightLimit() > 500.0)
    {
      throw new IllegalStateException("Drone's max weight has exceeded 500gr!");
    }
    return droneRepository.save(drone);
  }

  @Override
  public List<Drone> getAvailableDrones()
  {
    // It has assumed that drone in IDLE and LOADED state are, only available for loading
    Optional<List<Drone>> optionalDrones = droneRepository.findAllDronesByStateIn(
        Arrays.asList(DroneState.IDLE, DroneState.LOADED));
    return optionalDrones.orElse(Collections.emptyList());
  }

  @Override
  public Integer getBatteryLevelById(Long droneId)
  {
    return droneRepository.findById(droneId).map(Drone::getBatteryCapacity).orElse(null);
  }

  @Override
  @Transactional
  public boolean loadDroneWithMedications(Long droneId, List<Long> medicationIds)
  {
    Optional<Drone> optionalDrone = droneRepository.findById(droneId);
    if (optionalDrone.isPresent())
    {
      Drone drone = optionalDrone.get();
      if (drone.getBatteryCapacity() <= 25)
      {
        throw new IllegalStateException(
            "Drone's battery Capacity is " + drone.getBatteryCapacity() + ", which is less than 25%");
      }
      //Start Loading if battery is adequate
      drone.setState(DroneState.LOADING);

      final Double droneWeightLimit = drone.getWeightLimit();
      List<Medication> medicationList = medicationRepository.findAllById(medicationIds);
      double newTotalWeight =
          getDroneCurrentTotalWeight(new ArrayList<>(drone.getMedications())) + getDroneCurrentTotalWeight(
              medicationList);

      if (newTotalWeight > droneWeightLimit)
      {
        throw new IllegalStateException(
            "Maximum drone capacity " + droneWeightLimit + " has exceeded with medication " + "total weight: "
                + newTotalWeight);
      }
      //Drone Operation
      medicationList.forEach(medication -> medication.setDrone(drone));
      drone.setState(DroneState.LOADED);
      return true;
    }
    return false;
  }

  private Double getDroneCurrentTotalWeight(List<Medication> medicationList)
  {
    return medicationList.stream().map(Medication::getWeight).reduce(0d, Double::sum);
  }
}
