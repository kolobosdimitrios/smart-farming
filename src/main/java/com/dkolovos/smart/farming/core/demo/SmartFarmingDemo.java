package com.dkolovos.smart.farming.core.demo;

import com.dkolovos.smart.farming.core.application.service.irrigation.InMemoryIrrigationSessionManager;
import com.dkolovos.smart.farming.core.application.service.irrigation.SoilMoistureIrrigationScheduler;
import com.dkolovos.smart.farming.core.application.usecase.fields.RegisterFieldUseCase;
import com.dkolovos.smart.farming.core.application.usecase.sensors.RecordSensorReadingUseCase;
import com.dkolovos.smart.farming.core.domain.data.field.Field;
import com.dkolovos.smart.farming.core.domain.data.irrigation.IrrigationSessionResult;
import com.dkolovos.smart.farming.core.domain.data.sensors.SoilSensorReading;
import com.dkolovos.smart.farming.core.infrastructure.db.local.LocalFieldRepositoryImpl;
import com.dkolovos.smart.farming.core.infrastructure.db.local.LocalIrrigationEventRepositoryImpl;
import com.dkolovos.smart.farming.core.infrastructure.db.local.LocalSensorReadingRepositoryImpl;
import java.time.Instant;

/**
 * Simple demo showcasing the library using in-memory repositories.
 */
public class SmartFarmingDemo {
    public static void main(String[] args) throws InterruptedException {
        // Setup repositories
        LocalFieldRepositoryImpl fieldRepo = new LocalFieldRepositoryImpl();
        LocalSensorReadingRepositoryImpl<SoilSensorReading> sensorRepo = new LocalSensorReadingRepositoryImpl<>();
        LocalIrrigationEventRepositoryImpl eventRepo = new LocalIrrigationEventRepositoryImpl();
        InMemoryIrrigationSessionManager sessionManager = new InMemoryIrrigationSessionManager(eventRepo);

        // Register a field
        RegisterFieldUseCase registerField = new RegisterFieldUseCase(fieldRepo);
        Field field = new Field("field-1", "Main Field", 120.0);
        registerField.execute(field);

        // Record a soil sensor reading
        RecordSensorReadingUseCase<SoilSensorReading> recordReading = new RecordSensorReadingUseCase<>(sensorRepo);
        SoilSensorReading reading = new SoilSensorReading(
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
            IrrigationSessionResult session = result.getData();
            System.out.println("Irrigation duration: " + session.getDuration().toSeconds() + " seconds");
            System.out.println("Water used: " + session.getWaterConsumption() + " liters");
        } else {
            System.err.println("Failed to stop irrigation: " + result.getError());
        }
    }
}
