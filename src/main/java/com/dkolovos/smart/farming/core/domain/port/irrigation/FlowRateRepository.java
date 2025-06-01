/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.port.irrigation;
import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.irrigation.FlowRateReading;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author dimitrioskolovos
 */


public interface FlowRateRepository {
    
    Result<Void> insertFlowRateReading(FlowRateReading flowRateReading);
    Result<Optional<List<FlowRateReading>>> getReadings(String zoneId, Instant start, Instant end);
    Result<Void> deleteFlowRateReading(FlowRateReading flowRateReading);
}
