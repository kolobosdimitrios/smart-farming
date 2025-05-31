/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.application.usecase;

import com.dkolovos.smart.farming.core.domain.data.SensorReading;
import com.dkolovos.smart.farming.core.domain.port.repository.SensorReadingRepository;

/**
 *
 * @author dimitrioskolovos
 */
public class SecordSensorReadingUseCase {

    private final SensorReadingRepository sensorReadingRepository;

    public SecordSensorReadingUseCase(SensorReadingRepository sensorReadingRepository) {
        this.sensorReadingRepository = sensorReadingRepository;
    }

    public void execute(SensorReading sensorReading) {
        validate(sensorReading);

        if (!sensorReadingRepository.exists(sensorReading)) {
            sensorReadingRepository.save(sensorReading);
        } else {
            // Optional: log or silently ignore duplicates
            System.out.println("Duplicate reading ignored");
        }
    }

    private void validate(SensorReading sensorReading) {
        if (sensorReading == null) {
            throw new IllegalArgumentException("Sensor reading cannot be null");
        }

        if (sensorReading.getBatteryLevel() < 0 || sensorReading.getBatteryLevel() > 100) {
            throw new IllegalArgumentException("Battery level out of range");
        }

        if (sensorReading.getSignalLevel()< -100 || sensorReading.getSignalLevel()> 0) {
            throw new IllegalArgumentException("Signal strength out of range");
        }
    }

}
