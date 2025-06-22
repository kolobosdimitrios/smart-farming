/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.service.irrigation;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.irrigation.IrrigationEventDto;
import com.dkolovos.smart.farming.core.domain.data.irrigation.IrrigationSessionResultDto;
import com.dkolovos.smart.farming.core.domain.repository.irrigation.IrrigationEventRepository;
import com.dkolovos.smart.farming.core.domain.repository.irrigation.IrrigationSessionPort;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author dimitrioskolovos
 */
public class InMemoryIrrigationSessionManager implements IrrigationSessionPort {

    private final Map<String, Instant> active = new ConcurrentHashMap<>();
    private final IrrigationEventRepository repository;

    public InMemoryIrrigationSessionManager(IrrigationEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public void start(String zoneId) {
        this.active.put(zoneId, Instant.now());
    }

    @Override
    public Result<IrrigationSessionResultDto> stop(String zoneId) {
        Instant start = this.active.remove(zoneId);
        if(start == null){
            return Result.failure(new IllegalStateException("No active irrigation"));
        }
        
        Instant end = Instant.now();
        IrrigationEventDto event = new IrrigationEventDto(zoneId, start, end, Duration.ZERO, "User Id");
        
        Result<Void> result = repository.saveIrrigationEvent(event);
        
        if(result.isSuccess()){
            return Result.success(new IrrigationSessionResultDto(zoneId, start, end));
        }else{
            return Result.failure(new IllegalStateException("Unable to complete the termination of the event."));
        }
    }

}
