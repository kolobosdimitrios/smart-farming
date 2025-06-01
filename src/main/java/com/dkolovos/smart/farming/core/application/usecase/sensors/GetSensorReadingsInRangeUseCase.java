/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.application.usecase.sensors;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.sensors.SensorReading;
import com.dkolovos.smart.farming.core.domain.port.sensors.SensorReadingRepository;
import java.time.Instant;
import java.util.List;

/**
 *
 * @author dimitrioskolovos
 */
public class GetSensorReadingsInRangeUseCase<T extends SensorReading>{
    private final SensorReadingRepository<T> repository;

    public GetSensorReadingsInRangeUseCase(SensorReadingRepository<T> repository) {
        this.repository = repository;
    }

    public Result<List<T>> execute(String deviceId, Instant start, Instant end) {
        try{
            List<T> result = repository.findAllByDeviceIdAndTimeRange(deviceId, start, end).getData();
            return Result.success(result);
        }catch(Exception e){
            return Result.failure(e);
        } 
    }
}
