/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.application.service.irrigation;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.irrigation.IrrigationEvent;
import com.dkolovos.smart.farming.core.domain.data.irrigation.IrrigationSessionResult;
import com.dkolovos.smart.farming.core.domain.port.irrigation.IrrigationEventRepository;
import com.dkolovos.smart.farming.core.domain.port.irrigation.IrrigationSessionPort;
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
    public Result<IrrigationSessionResult> stop(String zoneId) {
        Instant start = this.active.remove(zoneId);
        if(start == null){
            return Result.failure(new IllegalStateException("No active irrigation"));
        }
        
        Instant end = Instant.now();
        IrrigationEvent event = new IrrigationEvent(zoneId, start, end, Duration.ZERO, "User Id");
        
        Result result = repository.saveIrrigationEvent(event);
        
        if(result.isSuccess()){
            return Result.success(new IrrigationSessionResult(zoneId, start, end));
        }else{
            return Result.failure(new IllegalStateException("Unable to complete the termination of the event."));
        }
    }

}
