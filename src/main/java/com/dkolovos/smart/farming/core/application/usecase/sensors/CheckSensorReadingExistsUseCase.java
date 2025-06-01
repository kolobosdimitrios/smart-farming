/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.application.usecase.sensors;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.sensors.SensorReading;
import com.dkolovos.smart.farming.core.domain.port.sensors.SensorReadingRepository;

/**
 *
 * @author dimitrioskolovos
 */
public class CheckSensorReadingExistsUseCase {

    private final SensorReadingRepository repository;
    
    public CheckSensorReadingExistsUseCase(SensorReadingRepository repository) {
        this.repository = repository;
    }
    
    public Result<Boolean> execute(SensorReading reading) {
        try {
            return repository.exists(reading);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
