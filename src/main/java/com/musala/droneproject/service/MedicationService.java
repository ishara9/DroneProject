package com.musala.droneproject.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.droneproject.model.Drone;
import com.musala.droneproject.model.Medication;
import com.musala.droneproject.repository.DroneRepository;
import com.musala.droneproject.repository.MedicationRepository;

@Service
public class MedicationService
{

  private final MedicationRepository medicationRepository;

  private final DroneRepository droneRepository;

  @Autowired
  public MedicationService(MedicationRepository medicationRepository, DroneRepository droneRepository)
  {
    this.medicationRepository = medicationRepository;
    this.droneRepository = droneRepository;
  }

  public List<Medication> getMedications()
  {
    return medicationRepository.findAll();
  }

  public void assignDroneToMedication(Long medicationId, Long droneId)
  {
    Optional<Medication> optionalMedication = medicationRepository.findById(medicationId);
    if (optionalMedication.isPresent())
    {
      Optional<Drone> optionalDrone = droneRepository.findById(droneId);
      if (optionalDrone.isPresent())
      {
        Medication medication = optionalMedication.get();
        Drone drone = optionalDrone.get();
        medication.setDrone(drone);
        medicationRepository.save(medication);
      }
    }
  }

  public List<Medication> findMedicationByDroneId(Long droneId)
  {
    Optional<List<Medication>> medications = medicationRepository.findAllMedicationByDroneId(droneId);
    return medications.orElse(Collections.emptyList());
  }
}
