package com.dkolovos.smart.farming.core.application.usecase.sensors;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.sensors.SoilSensorReading;
import com.dkolovos.smart.farming.core.infrastructure.db.local.LocalSensorReadingRepositoryImpl;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetSensorReadingsInRangeUseCaseTest {

    @Test
    void returnsReadingsWithinRange() {
        LocalSensorReadingRepositoryImpl<SoilSensorReading> repo = new LocalSensorReadingRepositoryImpl<>();
        Instant now = Instant.now();
        SoilSensorReading r1 = new SoilSensorReading("d1", now.minusSeconds(120), 50, -20, 10f, 20f, 7f);
        SoilSensorReading r2 = new SoilSensorReading("d1", now.minusSeconds(60), 50, -20, 11f, 21f, 7f);
        SoilSensorReading r3 = new SoilSensorReading("d1", now, 50, -20, 12f, 22f, 7f);
        repo.save(r1); repo.save(r2); repo.save(r3);
        GetSensorReadingsInRangeUseCase<SoilSensorReading> useCase = new GetSensorReadingsInRangeUseCase<>(repo);
        Result<List<SoilSensorReading>> result = useCase.execute("d1", now.minusSeconds(90), now);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertEquals(2, result.getData().size());
    }
}
