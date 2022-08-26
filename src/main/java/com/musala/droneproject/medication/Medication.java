package com.musala.droneproject.medication;

import java.util.Arrays;

public class Medication
{

  private String id;

  private String name;

  private Double weight;

  private String code;

  private Byte[] image;

  public Medication()
  {
  }

  public Medication(String id, String name, Double weight, String code, Byte[] image)
  {
    this.id = id;
    this.name = name;
    this.weight = weight;
    this.code = code;
    this.image = image;
  }

  public Medication(String name, Double weight, String code, Byte[] image)
  {
    this.name = name;
    this.weight = weight;
    this.code = code;
    this.image = image;
  }

  @Override
  public String toString()
  {
    return "Medication{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", weight=" + weight + ", code='" + code
        + '\'' + ", image=" + Arrays.toString(image) + '}';
  }
}
