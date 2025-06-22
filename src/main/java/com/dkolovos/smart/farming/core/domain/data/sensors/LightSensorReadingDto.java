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
public class LightSensorReadingDto extends SensorReadingDto{
    
    private final float intensity;

    public LightSensorReadingDto(float intensity, String deviceId, Instant timestamp, int batteryLevel, Integer signalLevel) {
        super(deviceId, timestamp, batteryLevel, signalLevel);
        this.intensity = intensity;
    }

    public float getIntensity() {
        return intensity;
    }
    
}
