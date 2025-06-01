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
public class IrrigationEvent {

    private final String zoneId;
    private final Instant startTime;
    private final Instant endTime;
    private final Duration duration; // or computed on demand
    private final float waterVolumeUsed;
    private final String triggeredById;

    public IrrigationEvent(String zoneId, Instant startTime, Instant endTime, Duration duration, String triggeredById) {
        this.zoneId = zoneId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = Duration.between(startTime, endTime);
        this.waterVolumeUsed = this.duration.getSeconds() * 10f; //Replace 10f with given or computed value. Litres per second.
        this.triggeredById = triggeredById;
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

    public float getWaterVolumeUsed() {
        return waterVolumeUsed;
    }

    public String getTriggeredById() {
        return triggeredById;
    }
    
    
    
}
