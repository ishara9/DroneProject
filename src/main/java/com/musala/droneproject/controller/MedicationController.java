package com.musala.droneproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musala.droneproject.model.Medication;
import com.musala.droneproject.service.MedicationService;

@RestController
@RequestMapping("api/v1/medication")
public class MedicationController
{

  private final MedicationService medicationService;

  @Autowired
  public MedicationController(MedicationService medicationService)
  {
    this.medicationService = medicationService;
  }

  @GetMapping
  public List<Medication> getMedications()
  {
    return medicationService.getMedications();
  }

}
