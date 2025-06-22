/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.application.usecase.sensors;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.sensors.SensorReadingDto;
import com.dkolovos.smart.farming.core.domain.port.sensors.SensorReadingRepository;

/**
 *
 * @author dimitrioskolovos
 */
public class CheckSensorReadingExistsUseCase<T extends SensorReadingDto> {

    private final SensorReadingRepository<T> repository;
    
    public CheckSensorReadingExistsUseCase(SensorReadingRepository<T> repository) {
        this.repository = repository;
    }
    
    public Result<Boolean> execute(T reading) {
        try {
            return repository.exists(reading);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
