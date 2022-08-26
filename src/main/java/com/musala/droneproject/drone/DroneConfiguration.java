package com.musala.droneproject.drone;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroneConfiguration
{
  @Bean
  CommandLineRunner commandLineRunner(DroneRepository repository)
  {
    return args -> {
      Drone drone1 = new Drone(1L, "234", DroneModel.LIGHT_WEIGHT, 50.0D, 50.0D, DroneState.IDLE);
      Drone drone2 = new Drone(2L, "567", DroneModel.HEAVY_WEIGHT, 100.0D, 80.0D, DroneState.IDLE);
      repository.saveAll(Arrays.asList(drone1, drone2));
    };
  }
}
