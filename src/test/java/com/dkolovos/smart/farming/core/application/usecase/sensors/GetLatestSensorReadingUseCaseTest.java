package com.dkolovos.smart.farming.core.application.usecase.sensors;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.sensors.SoilSensorReadingDto;
import com.dkolovos.smart.farming.core.infastracture.local_db.LocalSensorReadingRepositoryImpl;
import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetLatestSensorReadingUseCaseTest {

    @Test
    void returnsMostRecentReading() {
        LocalSensorReadingRepositoryImpl<SoilSensorReadingDto> repo = new LocalSensorReadingRepositoryImpl<>();
        SoilSensorReadingDto older = new SoilSensorReadingDto("d1", Instant.now().minusSeconds(60), 50, -20, 10f, 20f, 7f);
        SoilSensorReadingDto newer = new SoilSensorReadingDto("d1", Instant.now(), 60, -15, 11f, 21f, 7f);
        repo.save(older);
        repo.save(newer);
        GetLatestSensorReadingUseCase<SoilSensorReadingDto> useCase = new GetLatestSensorReadingUseCase<>(repo);
        Result<SoilSensorReadingDto> result = useCase.execute("d1");
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertEquals(newer.getTimestamp(), result.getData().getTimestamp());
    }
}
