package com.dkolovos.smart.farming.core.domain.service.irrigation;

import com.dkolovos.smart.farming.core.domain.data.sensors.SoilSensorReadingDto;
import com.dkolovos.smart.farming.core.domain.repository.irrigation.IrrigationSessionPort;
import com.dkolovos.smart.farming.core.domain.port.sensors.SensorReadingRepository;

public class SoilMoistureIrrigationScheduler {
    private final SensorReadingRepository<SoilSensorReadingDto> sensorRepo;
    private final IrrigationSessionPort sessionPort;
    private final float threshold;

    public SoilMoistureIrrigationScheduler(
            SensorReadingRepository<SoilSensorReadingDto> sensorRepo,
            IrrigationSessionPort sessionPort,
            float threshold
    ) {
        this.sensorRepo = sensorRepo;
        this.sessionPort = sessionPort;
        this.threshold = threshold;
    }

    public void checkAndIrrigate(String deviceId, String zoneId) {
        sensorRepo.findLatestByDeviceId(deviceId).getData()
                .filter(r -> r.getMoisture() < threshold)
                .ifPresent(r -> sessionPort.start(zoneId));
    }
}
