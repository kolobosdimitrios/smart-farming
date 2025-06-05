package com.dkolovos.smart.farming.core.application.service.irrigation;

import com.dkolovos.smart.farming.core.domain.data.sensors.SoilSensorReading;
import com.dkolovos.smart.farming.core.domain.port.irrigation.IrrigationSessionPort;
import com.dkolovos.smart.farming.core.domain.port.sensors.SensorReadingRepository;
import java.util.Optional;

public class SoilMoistureIrrigationScheduler {
    private final SensorReadingRepository<SoilSensorReading> sensorRepo;
    private final IrrigationSessionPort sessionPort;
    private final float threshold;

    public SoilMoistureIrrigationScheduler(
            SensorReadingRepository<SoilSensorReading> sensorRepo,
            IrrigationSessionPort sessionPort,
            float threshold
    ) {
        this.sensorRepo = sensorRepo;
        this.sessionPort = sessionPort;
        this.threshold = threshold;
    }

    public void checkAndIrrigate(String deviceId, String zoneId) {
        sensorRepo.findLatestByDeviceId(deviceId).getData()
                .flatMap(Optional::stream)
                .filter(r -> r.getMoisture() < threshold)
                .ifPresent(r -> sessionPort.start(zoneId));
    }
}
