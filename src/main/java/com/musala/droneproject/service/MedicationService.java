package com.musala.droneproject.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.musala.droneproject.model.Medication;

@Component
public interface MedicationService
{
  List<Medication> getMedications();

  List<Medication> findMedicationByDroneId(Long droneId);
}
