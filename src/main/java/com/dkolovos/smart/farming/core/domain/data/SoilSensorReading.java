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
    private final float pH;

    public SoilSensorReading(
            String deviceId,
            Instant timestamp,
            int batteryLevel,
            Integer signalLevel,
            float temperature,
            float moisture,
            float pH
    ) {
        super(deviceId, timestamp, batteryLevel, signalLevel);
        this.temperature = temperature;
        this.moisture = moisture;
        this.pH = pH;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getMoisture() {
        return moisture;
    }

    public float getpH() {
        return pH;
    }
    
    
    
    
    
}
