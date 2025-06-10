package com.dkolovos.smart.farming.core.application.usecase.sensors;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.sensors.SoilSensorReading;
import com.dkolovos.smart.farming.core.domain.port.sensors.SensorReadingRepository;
import com.dkolovos.smart.farming.core.infrastructure.db.local.LocalSensorReadingRepositoryImpl;
import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordSensorReadingUseCaseTest {

    @Test
    void savesValidReading() {
        LocalSensorReadingRepositoryImpl<SoilSensorReading> repo = new LocalSensorReadingRepositoryImpl<>();
        RecordSensorReadingUseCase<SoilSensorReading> useCase = new RecordSensorReadingUseCase<>(repo);
        SoilSensorReading reading = new SoilSensorReading("d1", Instant.now(), 50, -20, 10f, 30f, 7f);
        Result<Boolean> result = useCase.execute(reading);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertTrue(result.getData());
    }

    @Test
    void failsOnDuplicateReading() {
        LocalSensorReadingRepositoryImpl<SoilSensorReading> repo = new LocalSensorReadingRepositoryImpl<>();
        SoilSensorReading reading = new SoilSensorReading("d1", Instant.now(), 50, -20, 10f, 30f, 7f);
        repo.save(reading);
        RecordSensorReadingUseCase<SoilSensorReading> useCase = new RecordSensorReadingUseCase<>(repo);
        Result<Boolean> result = useCase.execute(reading);
        Assertions.assertTrue(result.isFailure());
    }

    @Test
    void failsOnInvalidBatteryLevel() {
        LocalSensorReadingRepositoryImpl<SoilSensorReading> repo = new LocalSensorReadingRepositoryImpl<>();
        RecordSensorReadingUseCase<SoilSensorReading> useCase = new RecordSensorReadingUseCase<>(repo);
        SoilSensorReading invalid = new SoilSensorReading("d1", Instant.now(), -1, -20, 10f, 30f, 7f);
        Result<Boolean> result = useCase.execute(invalid);
        Assertions.assertTrue(result.isFailure());
    }
}
