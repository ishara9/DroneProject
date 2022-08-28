package com.musala.droneproject.configuration;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.musala.droneproject.model.Drone;
import com.musala.droneproject.model.DroneModel;
import com.musala.droneproject.model.DroneState;
import com.musala.droneproject.model.Medication;
import com.musala.droneproject.repository.DroneRepository;
import com.musala.droneproject.repository.MedicationRepository;

@Configuration
public class DroneConfiguration
{
  @Bean
  CommandLineRunner commandLineRunner(DroneRepository droneRepository, MedicationRepository medicationRepository)
  {
    return args -> preLoadData(droneRepository, medicationRepository);
  }

  private void preLoadData(DroneRepository droneRepository, MedicationRepository medicationRepository)
  {
    Drone drone1 = new Drone(1L, "4CE041230E", DroneModel.LIGHT_WEIGHT, 50.0D, 50, DroneState.IDLE);
    Drone drone2 = new Drone(2L, "4C3044560F", DroneModel.HEAVY_WEIGHT, 200.0D, 80, DroneState.IDLE);
    Drone drone3 = new Drone(3L, "6DE046780G", DroneModel.HEAVY_WEIGHT, 200.0D, 100, DroneState.LOADED);
    Drone drone4 = new Drone(4L, "6EHI046780", DroneModel.HEAVY_WEIGHT, 50.0D, 20, DroneState.LOADED);
    droneRepository.saveAll(Arrays.asList(drone1, drone2, drone3, drone4));

    Medication medication1 = new Medication("med-123", 50.0d, "MED_123", new byte[0]);
    Medication medication2 = new Medication("med-245", 100.0d, "MED_456", new byte[0]);
    medicationRepository.saveAll(Arrays.asList(medication1, medication2));
  }

}
