/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.port.sensors;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.sensors.SensorReading;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dimitrioskolovos
 * This interface can be implemented by data sources like MySQL.
 */
public interface SensorReadingRepository {

    Result<Void> save(SensorReading reading);

    Result<Optional<SensorReading>> findLatestByDeviceId(String deviceId);

    Result<List<SensorReading>> findAllByDeviceIdAndTimeRange(String deviceId, Instant start, Instant end);

    Result<Boolean> exists(SensorReading reading);
}
