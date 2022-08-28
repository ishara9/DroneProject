package com.musala.droneproject.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.droneproject.model.Medication;
import com.musala.droneproject.repository.MedicationRepository;
import com.musala.droneproject.service.MedicationService;

@Service
public class MedicationServiceImpl implements MedicationService
{

  private final MedicationRepository medicationRepository;

  @Autowired
  public MedicationServiceImpl(MedicationRepository medicationRepository)
  {
    this.medicationRepository = medicationRepository;
  }

  @Override
  public List<Medication> getMedications()
  {
    return medicationRepository.findAll();
  }

  @Override
  public List<Medication> findMedicationByDroneId(Long droneId)
  {
    Optional<List<Medication>> medications = medicationRepository.findAllMedicationByDroneId(droneId);
    return medications.orElse(Collections.emptyList());
  }
}
