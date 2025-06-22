/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.infastracture.local_db;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.irrigation.IrrigationEventDto;
import com.dkolovos.smart.farming.core.domain.repository.irrigation.IrrigationEventRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author dimitrioskolovos
 */
public class LocalIrrigationEventRepositoryImpl implements IrrigationEventRepository{
    
    private final List<IrrigationEventDto> localEvents = new ArrayList<>();
   
    @Override
    public Result<Void> saveIrrigationEvent(IrrigationEventDto irrigationEvent) {
        this.localEvents.add(irrigationEvent);
        return Result.success(null);
    }

    @Override
    public Result<Optional<List<IrrigationEventDto>>> getIrrifationEventsForZone(String zoneId) {
         try {
            List<IrrigationEventDto> zoneEvents = localEvents.stream()
                .filter(event -> event.getZoneId().equals(zoneId))
                .collect(Collectors.toList());

            return Result.success(Optional.of(zoneEvents));
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

    @Override
    public Result<Void> updateIrrigationEvent(IrrigationEventDto irrigationEvent) {
        return Result.failure(new UnsupportedOperationException("Update not implemented in local repo."));
    }

    @Override
    public Result<Void> deleteIrrigationEvent(IrrigationEventDto irrigationEvent) {
        return Result.failure(new UnsupportedOperationException("Delete not implemented in local repo."));
    }
    
}
