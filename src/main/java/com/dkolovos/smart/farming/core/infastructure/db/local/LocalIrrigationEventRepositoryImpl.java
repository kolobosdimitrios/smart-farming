/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.infastructure.db.local;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.irrigation.IrrigationEvent;
import com.dkolovos.smart.farming.core.domain.port.irrigation.IrrigationEventRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author dimitrioskolovos
 */
public class LocalIrrigationEventRepositoryImpl implements IrrigationEventRepository{
    
    private final List<IrrigationEvent> localEvents = new ArrayList<>();
   
    @Override
    public Result<Void> saveIrrigationEvent(IrrigationEvent irrigationEvent) {
        this.localEvents.add(irrigationEvent);
        return Result.success(null);
    }

    @Override
    public Result<Optional<List<IrrigationEvent>>> getIrrifationEventsForZone(String zoneId) {
         try {
            List<IrrigationEvent> zoneEvents = localEvents.stream()
                .filter(event -> event.getZoneId().equals(zoneId))
                .collect(Collectors.toList());

            return Result.success(Optional.of(zoneEvents));
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

    @Override
    public Result<Void> updateIrrigationEvent(IrrigationEvent irrigationEvent) {
        return Result.failure(new UnsupportedOperationException("Update not implemented in local repo."));
    }

    @Override
    public Result<Void> deleteIrrigationEvent(IrrigationEvent irrigationEvent) {
        return Result.failure(new UnsupportedOperationException("Delete not implemented in local repo."));
    }
    
}
