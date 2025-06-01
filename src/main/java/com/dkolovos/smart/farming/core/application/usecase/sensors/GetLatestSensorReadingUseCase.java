/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.application.usecase.sensors;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.sensors.SensorReading;
import com.dkolovos.smart.farming.core.domain.port.sensors.SensorReadingRepository;
import java.util.Optional;

/**
 *
 * @author dimitrioskolovos
 */
public class GetLatestSensorReadingUseCase<T extends SensorReading>{

    private final SensorReadingRepository<T> repository;

    public GetLatestSensorReadingUseCase(SensorReadingRepository<T> repository) {
        this.repository = repository;
    }

    public Result<T> execute(String deviceId) {
        try {
            Optional<T> result = repository.findLatestByDeviceId(deviceId).getData();
            if (result.isPresent()) {
                return Result.success(result.get());
            } else {
                return Result.failure(new IllegalArgumentException("Cannot find sensor data for id: " + deviceId));
            }
        }catch(Exception e){
            return Result.failure(e);
        }

    }
}
