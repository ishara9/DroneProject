package com.musala.droneproject.model.dto;

import java.io.Serializable;

import com.musala.droneproject.model.DroneModel;
import com.musala.droneproject.model.DroneState;

public class DroneDTO implements Serializable
{
  private String serialNumber;

  private DroneModel model;

  private Double weightLimit;

  private Integer batteryCapacity;

  private DroneState state;

  public DroneDTO()
  {
  }

  public String getSerialNumber()
  {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber)
  {
    this.serialNumber = serialNumber;
  }

  public DroneModel getModel()
  {
    return model;
  }

  public void setModel(DroneModel model)
  {
    this.model = model;
  }

  public Double getWeightLimit()
  {
    return weightLimit;
  }

  public void setWeightLimit(Double weightLimit)
  {
    this.weightLimit = weightLimit;
  }

  public Integer getBatteryCapacity()
  {
    return batteryCapacity;
  }

  public void setBatteryCapacity(Integer batteryCapacity)
  {
    this.batteryCapacity = batteryCapacity;
  }

  public DroneState getState()
  {
    return state;
  }

  public void setState(DroneState state)
  {
    this.state = state;
  }
}
