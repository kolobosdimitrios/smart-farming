/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.repository.irrigation;
import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.irrigation.FlowRateReadingDto;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author dimitrioskolovos
 */


public interface FlowRateRepository {
    
    Result<Void> insertFlowRateReading(FlowRateReadingDto flowRateReading);
    Result<Optional<List<FlowRateReadingDto>>> getReadings(String zoneId, Instant start, Instant end);
    Result<Void> deleteFlowRateReading(FlowRateReadingDto flowRateReading);
}
