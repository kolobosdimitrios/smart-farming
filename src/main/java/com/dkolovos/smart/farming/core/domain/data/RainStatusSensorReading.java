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
public class RainStatusSensorReading extends SensorReading{
    
    private final boolean isRaining;
    private final float precipitation;

    public RainStatusSensorReading(boolean isRaining, float precipitation, String deviceId, Instant timestamp, int batteryLevel, Integer signalLevel) {
        super(deviceId, timestamp, batteryLevel, signalLevel);
        this.isRaining = isRaining;
        this.precipitation = precipitation;
    }

    public boolean isRaining() {
        return isRaining;
    }

    
    public float getPrecipitation() {
        return precipitation;
    }
    
    
    
}
