/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.data.sensors;

import java.time.Instant;

/**
 *
 * @author dimitrioskolovos
 */
public class SnowStatusSensorReading extends SensorReading {
    
    private final boolean isSnowing;
    private final float precipitation;

    public SnowStatusSensorReading(boolean isSnowing, float precipitation, String deviceId, Instant timestamp, int batteryLevel, Integer signalLevel) {
        super(deviceId, timestamp, batteryLevel, signalLevel);
        this.isSnowing = isSnowing;
        this.precipitation = precipitation;
    }

    public boolean isSnowing() {
        return isSnowing;
    }

    public float getPrecipitation() {
        return precipitation;
    }
    
    
}
