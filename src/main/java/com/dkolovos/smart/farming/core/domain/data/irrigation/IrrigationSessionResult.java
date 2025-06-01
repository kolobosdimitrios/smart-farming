/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.data.irrigation;

import java.time.Duration;
import java.time.Instant;

/**
 *
 * @author dimitrioskolovos
 */
public class IrrigationSessionResult {
     private final String zoneId;
    private final Instant startTime;
    private final Instant endTime;
    private final Duration duration;
    private final float waterConsumption;

    public IrrigationSessionResult(String zoneId, Instant startTime, Instant endTime) {
        this.zoneId = zoneId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = Duration.between(startTime, endTime);
         this.waterConsumption = this.duration.getSeconds() * 10f; //replace with actual value. Liters per second.
    }

    public String getZoneId() {
        return zoneId;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public float getWaterConsumption() {
        return waterConsumption;
    }
    
    
}
