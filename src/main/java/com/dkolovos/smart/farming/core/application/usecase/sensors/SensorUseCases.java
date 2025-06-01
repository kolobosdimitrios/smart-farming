/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.application.usecase.sensors;

import com.dkolovos.smart.farming.core.domain.port.sensors.SensorReadingRepository;

/**
 *
 * @author dimitrioskolovos
 */
public class SensorUseCases {
    public final RecordSensorReadingUseCase recordSensorReading;
    public final CheckSensorReadingExistsUseCase checkSensorReadingExists;
    public final GetLatestSensorReadingUseCase getLatestSensorReading;
    public final GetSensorReadingsInRangeUseCase getSensorReadingsInRange;

    public SensorUseCases(SensorReadingRepository repository) {
        this.recordSensorReading = new RecordSensorReadingUseCase(repository);
        this.checkSensorReadingExists = new CheckSensorReadingExistsUseCase(repository);
        this.getLatestSensorReading = new GetLatestSensorReadingUseCase(repository);
        this.getSensorReadingsInRange = new GetSensorReadingsInRangeUseCase(repository);
    }
}