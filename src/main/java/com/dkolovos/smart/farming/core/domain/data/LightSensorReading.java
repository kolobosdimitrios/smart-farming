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
public class LightSensorReading extends SensorReading{
    
    private final float intensity;

    public LightSensorReading(float intensity, String deviceId, Instant timestamp, int batteryLevel, Integer signalLevel) {
        super(deviceId, timestamp, batteryLevel, signalLevel);
        this.intensity = intensity;
    }

    public float getIntensity() {
        return intensity;
    }
    
}
