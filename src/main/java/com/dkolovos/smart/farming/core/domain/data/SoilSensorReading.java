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
public class SoilSensorReading extends SensorReading{
    
    private final float temperature;
    private final float moisture;

    public SoilSensorReading(
            String deviceId,
            Instant timestamp,
            int batteryLevel,
            Integer signalLevel,
            float temperature,
            float moisture
    ) {
        super(deviceId, timestamp, batteryLevel, signalLevel);
        this.temperature = temperature;
        this.moisture = moisture;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getMoisture() {
        return moisture;
    }
    
    
    
}
