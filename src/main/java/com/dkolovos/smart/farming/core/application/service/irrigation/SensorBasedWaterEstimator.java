/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.application.service.irrigation;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.irrigation.FlowRateReading;
import com.dkolovos.smart.farming.core.domain.port.irrigation.FlowRateRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dimitrioskolovos
 */
public class SensorBasedWaterEstimator implements WaterConsumptionEstimator {

    private final FlowRateRepository flowRateRepository;

    public SensorBasedWaterEstimator(FlowRateRepository flowRateRepository) {
        this.flowRateRepository = flowRateRepository;
    }

    @Override
    public float estimate(String zoneId, Instant start, Instant end) {
        Result<Optional<List<FlowRateReading>>> result = flowRateRepository.getReadings(zoneId, start, end);
        if (result.isSuccess()) {
            Optional<List<FlowRateReading>> optionalReadings = result.getData();
            if (optionalReadings.isPresent()) {
                float totalLiters = 0f;
                List<FlowRateReading> readings = optionalReadings.get();
                for (int i = 0; i < readings.size() - 1; i++) {
                    FlowRateReading current = readings.get(i);
                    FlowRateReading next = readings.get(i + 1);
                    long seconds = Duration.between(current.getTimestamp(), next.getTimestamp()).getSeconds();
                    totalLiters += current.getRateLitersPerSecond() * seconds;
                }
                return totalLiters;
            } else {
                throw new IllegalStateException("No readings found for zoneID");
            }
        } else {
            throw new IllegalStateException("Unable to fetch readings.");
        }
    }
}
