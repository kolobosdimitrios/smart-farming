/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.repository.irrigation;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.irrigation.IrrigationEventDto;
import com.dkolovos.smart.farming.core.domain.data.irrigation.IrrigationSessionResultDto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dimitrioskolovos
 */
public interface IrrigationEventRepository {
    
    Result<Void> saveIrrigationEvent(IrrigationEventDto irrigationEvent);
    
    Result<Optional<List<IrrigationEventDto>>> getIrrifationEventsForZone(String zoneId);
    
    Result<Void> updateIrrigationEvent(IrrigationEventDto irrigationEvent);
    
    Result<Void> deleteIrrigationEvent(IrrigationEventDto irrigationEvent);
}
