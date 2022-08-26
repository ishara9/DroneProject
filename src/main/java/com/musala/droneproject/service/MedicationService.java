package com.musala.droneproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.droneproject.model.Medication;
import com.musala.droneproject.repository.MedicationRepository;

@Service
public class MedicationService
{

  private final MedicationRepository medicationRepository;

  @Autowired
  public MedicationService(MedicationRepository medicationRepository)
  {
    this.medicationRepository = medicationRepository;
  }

  public List<Medication> getMedications()
  {
    return medicationRepository.findAll();
  }
}
