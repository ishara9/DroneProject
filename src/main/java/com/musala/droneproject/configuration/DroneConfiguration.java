package com.musala.droneproject.configuration;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
  CommandLineRunner commandLineRunner(DroneRepository repository, MedicationRepository medicationRepository)
  {
    return args -> {
      Drone drone1 = new Drone(1L, "234", DroneModel.LIGHT_WEIGHT, 50.0D, 50, DroneState.IDLE);
      Drone drone2 = new Drone(2L, "567", DroneModel.HEAVY_WEIGHT, 100.0D, 80, DroneState.LOADED);
      repository.saveAll(Arrays.asList(drone1, drone2));

//      byte[] image = getImage();
      Medication medication1 = new Medication("med1", 50.0d, "UPERCASE_123", new byte[0]);
      Medication medication2 = new Medication("med2", 100.0d, "UPERCASE_456", new byte[0]);
      medicationRepository.saveAll(Arrays.asList(medication1, medication2));
    };
  }

  private byte[] getImage() throws IOException
  {
    String image = "src/main/resources/static/medi_pack.png";
    File imageFile = new File(image);
    InputStream inStream = new FileInputStream(imageFile);
    return readInputStream(inStream);
  }

  private static byte[] readInputStream(InputStream inStream) throws IOException
  {
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    byte[] buffer = new byte[10240];
    int len = 0;
    while ((len = inStream.read(buffer)) != -1) {
      outStream.write(buffer, 0, len);
    }
    inStream.close();
    return outStream.toByteArray();
  }
}
