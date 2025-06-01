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
public class LocalSensoReadingRepositoryImpl<T extends SensorReading> implements SensorReadingRepository<T> {

    private final Map<String, List<T>> store = new ConcurrentHashMap<>(); //In memory db;

    @Override
    public Result<Void> save(T reading) {
        store.computeIfAbsent(reading.getDeviceId(), k -> new ArrayList<>()).add(reading);
        return Result.success(null);
    }

    @Override
    public Result<Optional<T>> findLatestByDeviceId(String deviceId) {
        Optional<T> latest = store.getOrDefault(deviceId, Collections.emptyList()).stream()
                .max(Comparator.comparing(T::getTimestamp));
        return Result.success(latest);
    }

    @Override
    public Result<List<T>> findAllByDeviceIdAndTimeRange(String deviceId, Instant start, Instant end) {
        List<T> results = store.getOrDefault(deviceId, Collections.emptyList()).stream()
                .filter(r -> !r.getTimestamp().isBefore(start) && !r.getTimestamp().isAfter(end))
                .collect(Collectors.toList());
        return Result.success(results);
    }

    @Override
    public Result<Boolean> exists(T reading) {
        Boolean exists = store.getOrDefault(reading.getDeviceId(), Collections.emptyList()).stream()
                .anyMatch(r -> r.getTimestamp().equals(reading.getTimestamp()));
        return Result.success(exists);
    }

}
