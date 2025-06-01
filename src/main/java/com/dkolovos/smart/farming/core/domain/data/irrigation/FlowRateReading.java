/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.data.irrigation;

import java.time.Instant;

/**
 *
 * @author dimitrioskolovos
 */
public class FlowRateReading {
    private final String zoneId;
    private final float rateLitersPerSecond;
    private final Instant timestamp;

    public FlowRateReading(String zoneId, float rateLitersPerSecond, Instant timestamp) {
        this.zoneId = zoneId;
        this.rateLitersPerSecond = rateLitersPerSecond;
        this.timestamp = timestamp;
    }

    public String getZoneId() {
        return zoneId;
    }

    public float getRateLitersPerSecond() {
        return rateLitersPerSecond;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
