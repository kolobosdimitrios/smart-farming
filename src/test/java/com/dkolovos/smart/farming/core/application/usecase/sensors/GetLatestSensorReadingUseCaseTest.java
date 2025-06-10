package com.dkolovos.smart.farming.core.application.usecase.sensors;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.sensors.SoilSensorReading;
import com.dkolovos.smart.farming.core.infrastructure.db.local.LocalSensorReadingRepositoryImpl;
import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetLatestSensorReadingUseCaseTest {

    @Test
    void returnsMostRecentReading() {
        LocalSensorReadingRepositoryImpl<SoilSensorReading> repo = new LocalSensorReadingRepositoryImpl<>();
        SoilSensorReading older = new SoilSensorReading("d1", Instant.now().minusSeconds(60), 50, -20, 10f, 20f, 7f);
        SoilSensorReading newer = new SoilSensorReading("d1", Instant.now(), 60, -15, 11f, 21f, 7f);
        repo.save(older);
        repo.save(newer);
        GetLatestSensorReadingUseCase<SoilSensorReading> useCase = new GetLatestSensorReadingUseCase<>(repo);
        Result<SoilSensorReading> result = useCase.execute("d1");
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertEquals(newer.getTimestamp(), result.getData().getTimestamp());
    }
}
