/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.port.irrigation;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.irrigation.IrrigationEvent;
import com.dkolovos.smart.farming.core.domain.data.irrigation.IrrigationSessionResult;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dimitrioskolovos
 */
public interface IrrigationEventRepository {
    
    Result<Void> saveIrrigationEvent(IrrigationEvent irrigationEvent);
    
    Result<Optional<List<IrrigationEvent>>> getIrrifationEventsForZone(String zoneId);
    
    Result<Void> updateIrrigationEvent(IrrigationEvent irrigationEvent);
    
    Result<Void> deleteIrrigationEvent(IrrigationEvent irrigationEvent);
}
