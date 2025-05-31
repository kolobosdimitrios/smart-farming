/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.port.repository;

import com.dkolovos.smart.farming.core.domain.data.SensorReading;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dimitrioskolovos
 */
public interface SensorReadingRepository {

    void save(SensorReading reading);

    Optional<SensorReading> findLatestByDeviceId(String deviceId);

    List<SensorReading> findAllByDeviceIdAndTimeRange(String deviceId, Instant start, Instant end);

    boolean exists(SensorReading reading);
}
