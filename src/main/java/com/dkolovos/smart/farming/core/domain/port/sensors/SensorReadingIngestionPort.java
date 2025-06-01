/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.port.sensors;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.sensors.SensorReading;
import java.util.List;

/**
 *
 * @author dimitrioskolovos
 * This interface can be implemented by infrastructure code that uses MQTT clients or HTTP push handlers.
 */
public interface SensorReadingIngestionPort {
    Result<Void> ingest(SensorReading reading);
    Result<Void> ingestBatch(List<SensorReading> readings);
}
