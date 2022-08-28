package com.musala.droneproject.mapper;

import com.musala.droneproject.model.Drone;
import com.musala.droneproject.model.dto.DroneDTO;

public final class DroneDTOMapper
{

  private DroneDTOMapper()
  {
  }

  public static Drone map(DroneDTO droneDTO)
  {
    Drone drone = new Drone();
    drone.setSerialNumber(droneDTO.getSerialNumber());
    drone.setModel(droneDTO.getModel());
    drone.setWeightLimit(droneDTO.getWeightLimit());
    drone.setBatteryCapacity(droneDTO.getBatteryCapacity());
    drone.setState(droneDTO.getState());
    return drone;
  }
}
