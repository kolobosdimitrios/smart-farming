/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.infastructure.db.local;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.irrigation.FlowRateReading;
import com.dkolovos.smart.farming.core.domain.port.irrigation.FlowRateRepository;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 *
 * @author dimitrioskolovos
 */
public class LocalFlowRateRepositoryImpl implements FlowRateRepository {

    private final Map<String, List<FlowRateReading>> localMap = new ConcurrentHashMap<>();

    @Override
    public Result<Optional<List<FlowRateReading>>> getReadings(String zoneId, Instant start, Instant end) {
        try {
            List<FlowRateReading> readings = localMap.getOrDefault(zoneId, List.of())
                    .stream()
                    .filter(r -> !r.getTimestamp().isBefore(start) && !r.getTimestamp().isAfter(end))
                    .collect(Collectors.toList());

            return Result.success(readings.isEmpty() ? Optional.empty() : Optional.of(readings));
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

    @Override
    public Result<Void> insertFlowRateReading(FlowRateReading flowRateReading) {
        try {
            this.localMap.computeIfAbsent(flowRateReading.getZoneId(), k -> new ArrayList<>()).add(flowRateReading);
            return Result.success(null);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

    @Override
    public Result<Void> deleteFlowRateReading(FlowRateReading flowRateReading) {
        try {
            List<FlowRateReading> readingsToDelete = this.localMap.get(flowRateReading.getZoneId());
            if (readingsToDelete != null) {
                readingsToDelete.removeIf(r -> r.getTimestamp().equals(flowRateReading.getTimestamp()));
                return Result.success(null);
            } else {
                return Result.failure(new IllegalStateException("Cannot find records for given zoneId"));
            }
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}

