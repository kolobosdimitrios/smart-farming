package com.dkolovos.smart.farming.core.application.usecase.sensors;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.sensors.SoilSensorReadingDto;
import com.dkolovos.smart.farming.core.domain.port.sensors.SensorReadingRepository;
import com.dkolovos.smart.farming.core.infastracture.local_db.LocalSensorReadingRepositoryImpl;
import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordSensorReadingUseCaseTest {

    @Test
    void savesValidReading() {
        LocalSensorReadingRepositoryImpl<SoilSensorReadingDto> repo = new LocalSensorReadingRepositoryImpl<>();
        RecordSensorReadingUseCase<SoilSensorReadingDto> useCase = new RecordSensorReadingUseCase<>(repo);
        SoilSensorReadingDto reading = new SoilSensorReadingDto("d1", Instant.now(), 50, -20, 10f, 30f, 7f);
        Result<Boolean> result = useCase.execute(reading);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertTrue(result.getData());
    }

    @Test
    void failsOnDuplicateReading() {
        LocalSensorReadingRepositoryImpl<SoilSensorReadingDto> repo = new LocalSensorReadingRepositoryImpl<>();
        SoilSensorReadingDto reading = new SoilSensorReadingDto("d1", Instant.now(), 50, -20, 10f, 30f, 7f);
        repo.save(reading);
        RecordSensorReadingUseCase<SoilSensorReadingDto> useCase = new RecordSensorReadingUseCase<>(repo);
        Result<Boolean> result = useCase.execute(reading);
        Assertions.assertTrue(result.isFailure());
    }

    @Test
    void failsOnInvalidBatteryLevel() {
        LocalSensorReadingRepositoryImpl<SoilSensorReadingDto> repo = new LocalSensorReadingRepositoryImpl<>();
        RecordSensorReadingUseCase<SoilSensorReadingDto> useCase = new RecordSensorReadingUseCase<>(repo);
        SoilSensorReadingDto invalid = new SoilSensorReadingDto("d1", Instant.now(), -1, -20, 10f, 30f, 7f);
        Result<Boolean> result = useCase.execute(invalid);
        Assertions.assertTrue(result.isFailure());
    }
}
