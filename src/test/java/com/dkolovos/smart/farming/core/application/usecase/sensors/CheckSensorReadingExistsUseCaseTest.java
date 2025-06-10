package com.dkolovos.smart.farming.core.application.usecase.sensors;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.sensors.SoilSensorReading;
import com.dkolovos.smart.farming.core.infrastructure.db.local.LocalSensorReadingRepositoryImpl;
import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckSensorReadingExistsUseCaseTest {

    @Test
    void returnsTrueWhenReadingExists() {
        LocalSensorReadingRepositoryImpl<SoilSensorReading> repo = new LocalSensorReadingRepositoryImpl<>();
        SoilSensorReading reading = new SoilSensorReading("d1", Instant.now(), 80, -10, 10f, 20f, 7f);
        repo.save(reading);
        CheckSensorReadingExistsUseCase<SoilSensorReading> useCase = new CheckSensorReadingExistsUseCase<>(repo);
        Result<Boolean> result = useCase.execute(reading);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertTrue(result.getData());
    }
}
