package com.musala.droneproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  /**
   * Task 3: checking loaded medication items for a given drone
   *
   * @param droneId
   * @return
   */
  @GetMapping(path = { "/", "/{droneId}" })
  public List<Medication> getMedications(@PathVariable @RequestParam(required = false, name = "droneId") Long droneId)
  {
    if (droneId != null)
    {
      return medicationService.findMedicationByDroneId(droneId);
    }
    return medicationService.getMedications();
  }

  /**
   * Task 2: loading a drone with medication item
   * @param medicationId
   * @param droneId
   */
  @PutMapping("/{medicationId}/{droneId}")
  public void assignDroneToMedication(@PathVariable Long medicationId, @PathVariable Long droneId)
  {
    medicationService.assignDroneToMedication(medicationId, droneId);
  }

}
