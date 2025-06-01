/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dkolovos.smart.farming.core.infastructure.db.local;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.sensors.SensorReading;
import com.dkolovos.smart.farming.core.domain.port.sensors.SensorReadingRepository;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 *
 * @author dimitrioskolovos
 */
public class LocalSensoReadingRepositoryImpl implements SensorReadingRepository {

    private final Map<String, List<SensorReading>> store = new ConcurrentHashMap<>(); //In memory db;

    @Override
    public Result<Void> save(SensorReading reading) {
        store.computeIfAbsent(reading.getDeviceId(), k -> new ArrayList<>()).add(reading);
        return Result.success(null);
    }

    @Override
    public Result<Optional<SensorReading>> findLatestByDeviceId(String deviceId) {
        Optional<SensorReading> latest = store.getOrDefault(deviceId, Collections.emptyList()).stream()
                .max(Comparator.comparing(SensorReading::getTimestamp));
        return Result.success(latest);
    }

    @Override
    public Result<List<SensorReading>> findAllByDeviceIdAndTimeRange(String deviceId, Instant start, Instant end) {
        List<SensorReading> results = store.getOrDefault(deviceId, Collections.emptyList()).stream()
                .filter(r -> !r.getTimestamp().isBefore(start) && !r.getTimestamp().isAfter(end))
                .collect(Collectors.toList());
        return Result.success(results);
    }

    @Override
    public Result<Boolean> exists(SensorReading reading) {
        Boolean exists = store.getOrDefault(reading.getDeviceId(), Collections.emptyList()).stream()
                .anyMatch(r -> r.getTimestamp().equals(reading.getTimestamp()));
        return Result.success(exists);
    }

}
