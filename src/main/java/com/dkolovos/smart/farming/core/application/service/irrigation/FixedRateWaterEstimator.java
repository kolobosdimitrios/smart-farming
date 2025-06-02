/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.application.service.irrigation;


import com.dkolovos.smart.farming.core.domain.service.WaterConsumptionEstimator;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;

/**
 *
 * @author dimitrioskolovos
 */
public class FixedRateWaterEstimator implements WaterConsumptionEstimator {

    private final Map<String, Float> zoneFlowRates; // zoneId -> L/s

    public FixedRateWaterEstimator(Map<String, Float> zoneFlowRates) {
        this.zoneFlowRates = zoneFlowRates;
    }

    @Override
    public float estimate(String zoneId, Instant start, Instant end) {
        float rate = zoneFlowRates.getOrDefault(zoneId, 0f);
        long durationSeconds = Duration.between(start, end).getSeconds();
        return rate * durationSeconds;
    }
}
