package com.musala.droneproject.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.musala.droneproject.model.Drone;
import com.musala.droneproject.repository.DroneRepository;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class DroneBatteryCheckScheduler
{

  static final Logger log = LoggerFactory.getLogger(DroneBatteryCheckScheduler.class);

  private final DroneRepository droneRepository;

  @Autowired
  public DroneBatteryCheckScheduler(DroneRepository droneRepository)
  {
    this.droneRepository = droneRepository;
  }

  @Async
  @Scheduled(fixedRate = 30000)
  public void periodicTaskAsync() throws InterruptedException
  {
    List<Drone> drones = droneRepository.findAll();
    for (int i = 0; i < drones.size(); i++)
    {
      log.info("Battery info -- Drone SerialNumber:  {}  --  Battery Capacity:  {}%", drones.get(i).getSerialNumber(),
          drones.get(i).getBatteryCapacity());
    }
    Thread.sleep(30000);
  }
}
