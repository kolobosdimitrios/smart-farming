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
public class RecordSensorReadingUseCase<T extends SensorReading> {

    private final SensorReadingRepository<T> sensorReadingRepository;

    public RecordSensorReadingUseCase(SensorReadingRepository<T> sensorReadingRepository) {
        this.sensorReadingRepository = sensorReadingRepository;
    }

    public Result<Boolean> execute(T sensorReading) {
        try {
            validate(sensorReading);

            if (!sensorReadingRepository.exists(sensorReading).getData()) {
                sensorReadingRepository.save(sensorReading);
                return Result.success(true);
            } else {
                // Optional: log or silently ignore duplicates
                System.out.println("Duplicate reading ignored");
                return Result.failure(new IllegalArgumentException("Cannot insert record to DB."));
            }
        } catch (IllegalArgumentException iae) {
            return Result.failure(iae);
        }
    }

    private void validate(SensorReading sensorReading) {
        if (sensorReading == null) {
            throw new IllegalArgumentException("Sensor reading cannot be null");
        }

        if (sensorReading.getBatteryLevel() < 0 || sensorReading.getBatteryLevel() > 100) {
            throw new IllegalArgumentException("Battery level out of range");
        }

        if (sensorReading.getSignalLevel() < -100 || sensorReading.getSignalLevel() > 0) {
            throw new IllegalArgumentException("Signal strength out of range");
        }
    }

}
