package com.dkolovos.smart.farming.core.demo;

import com.dkolovos.smart.farming.core.domain.service.irrigation.InMemoryIrrigationSessionManager;
import com.dkolovos.smart.farming.core.domain.service.irrigation.SoilMoistureIrrigationScheduler;
import com.dkolovos.smart.farming.core.application.usecase.fields.RegisterFieldUseCase;
import com.dkolovos.smart.farming.core.application.usecase.sensors.RecordSensorReadingUseCase;
import com.dkolovos.smart.farming.core.domain.data.field.AreaDto;
import com.dkolovos.smart.farming.core.domain.data.field.FieldDto;
import com.dkolovos.smart.farming.core.domain.data.irrigation.IrrigationSessionResultDto;
import com.dkolovos.smart.farming.core.domain.data.sensors.SoilSensorReadingDto;
import com.dkolovos.smart.farming.core.infastracture.local_db.LocalFieldRepositoryImpl;
import com.dkolovos.smart.farming.core.infastracture.local_db.LocalIrrigationEventRepositoryImpl;
import com.dkolovos.smart.farming.core.infastracture.local_db.LocalSensorReadingRepositoryImpl;
import java.time.Instant;

/**
 * Simple demo showcasing the library using in-memory repositories.
 */
public class SmartFarmingDemo {
    public static void main(String[] args) throws InterruptedException {
        // Setup repositories
        LocalFieldRepositoryImpl fieldRepo = new LocalFieldRepositoryImpl();
        LocalSensorReadingRepositoryImpl<SoilSensorReadingDto> sensorRepo = new LocalSensorReadingRepositoryImpl<>();
        LocalIrrigationEventRepositoryImpl eventRepo = new LocalIrrigationEventRepositoryImpl();
        InMemoryIrrigationSessionManager sessionManager = new InMemoryIrrigationSessionManager(eventRepo);

        // Register a field
        RegisterFieldUseCase registerField = new RegisterFieldUseCase(fieldRepo);
        
        AreaDto.RectangularArea rectangularArea = new AreaDto.RectangularArea(new double[] {10.0, 20.0, 30.0 ,50.0}, new double[] {30.0, 50.0, 60.0 , 10.0});
        FieldDto field = new FieldDto("field-1", "Main Field", rectangularArea);
        registerField.execute(field);

        // Record a soil sensor reading
        RecordSensorReadingUseCase<SoilSensorReadingDto> recordReading = new RecordSensorReadingUseCase<>(sensorRepo);
        SoilSensorReadingDto reading = new SoilSensorReadingDto(
                "sensor-1",
                Instant.now(),
                90,
                -30,
                15.0f,
                25.0f,
                6.8f
        );
        recordReading.execute(reading);

        // Schedule irrigation based on soil moisture
        SoilMoistureIrrigationScheduler scheduler = new SoilMoistureIrrigationScheduler(sensorRepo, sessionManager, 30.0f);
        scheduler.checkAndIrrigate("sensor-1", "zone-1");

        // Simulate some irrigation duration
        Thread.sleep(1000);

        // Stop irrigation and print result
        var result = sessionManager.stop("zone-1");
        if (result.isSuccess()) {
            IrrigationSessionResultDto session = result.getData();
            System.out.println("Irrigation duration: " + session.getDuration().toSeconds() + " seconds");
            System.out.println("Water used: " + session.getWaterConsumption() + " liters");
        } else {
            System.err.println("Failed to stop irrigation: " + result.getError());
        }
    }
}
