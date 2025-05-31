/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dkolovos.smart.farming.core.infastructure.db.local;

import com.dkolovos.smart.farming.core.domain.data.SensorReading;
import com.dkolovos.smart.farming.core.domain.port.repository.SensorReadingRepository;
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
    public void save(SensorReading reading) {
        store.computeIfAbsent(reading.getDeviceId(), k -> new ArrayList<>()).add(reading);
    }

    @Override
    public Optional<SensorReading> findLatestByDeviceId(String deviceId) {
        return store.getOrDefault(deviceId, Collections.emptyList()).stream()
                .max(Comparator.comparing(SensorReading::getTimestamp));
    }

    @Override
    public List<SensorReading> findAllByDeviceIdAndTimeRange(String deviceId, Instant start, Instant end) {
        return store.getOrDefault(deviceId, Collections.emptyList()).stream()
                .filter(r -> !r.getTimestamp().isBefore(start) && !r.getTimestamp().isAfter(end))
                .collect(Collectors.toList());
    }

    @Override
    public boolean exists(SensorReading reading) {
        return store.getOrDefault(reading.getDeviceId(), Collections.emptyList()).stream()
                .anyMatch(r -> r.getTimestamp().equals(reading.getTimestamp()));
    }

}
