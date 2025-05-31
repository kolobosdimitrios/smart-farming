/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.data;

import java.time.Instant;

/**
 *
 * @author dimitrioskolovos
 */
public class AirSensorReading extends SensorReading {
    
    private final float temperature;
    private final float humidity;
    private final Float co2Level;

    public AirSensorReading(float temperature, float humidity, Float co2Level, String deviceId, Instant timestamp, int batteryLevel, Integer signalLevel) {
        super(deviceId, timestamp, batteryLevel, signalLevel);
        this.temperature = temperature;
        this.humidity = humidity;
        this.co2Level = co2Level;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public Float getCo2Level() {
        return co2Level;
    }
    
    
   
    
}
