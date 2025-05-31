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
public class SensorReading {
    
    private final String deviceId;
    private final Instant timestamp;
    private final int batteryLevel;
    private final Integer signalLevel;

    public SensorReading(String deviceId, Instant timestamp, int batteryLevel, Integer signalLevel) {
        this.deviceId = deviceId;
        this.timestamp = timestamp;
        this.batteryLevel = batteryLevel;
        this.signalLevel = signalLevel;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public Integer getSignalLevel() {
        return signalLevel;
    }
    
    
    
}
