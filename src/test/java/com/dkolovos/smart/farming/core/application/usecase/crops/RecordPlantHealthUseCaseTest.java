package com.dkolovos.smart.farming.core.application.usecase.crops;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.PlantHealthDto;
import com.dkolovos.smart.farming.core.domain.repository.crop.PlantHealthRepository;
import com.dkolovos.smart.farming.core.infastracture.local_db.LocalPlantHealthRepositoryImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordPlantHealthUseCaseTest {

    @Test
    void insertsPlantHealthRecord() {
        LocalPlantHealthRepositoryImpl repo = new LocalPlantHealthRepositoryImpl();
        RecordPlantHealthUseCase useCase = new RecordPlantHealthUseCase(repo);
        PlantHealthDto health = new PlantHealthDto("p1", 80, List.of("a"), List.of());
        Result<Void> result = useCase.execute(health);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertFalse(repo.getPlantHealthRecords("p1").getData().orElseThrow().isEmpty());
    }

    private static class FailingRepo implements PlantHealthRepository {
        @Override public Result<Void> insertPlantHealthRecord(PlantHealthDto plantHealth) { throw new RuntimeException("fail"); }
        @Override public Result<Optional<List<PlantHealthDto>>> getPlantHealthRecords(String plantId) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Optional<List<PlantHealthDto>>> getAllPlantHealthRecords() { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Void> deletePlantHealthStatus(PlantHealthDto plantHealth) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Optional<List<PlantHealthDto>>> findByFieldOrCondition(String fieldId, String conditionType) { return Result.failure(new RuntimeException("fail")); }
    }

    @Test
    void returnsFailureWhenRepositoryThrows() {
        RecordPlantHealthUseCase useCase = new RecordPlantHealthUseCase(new FailingRepo());
        PlantHealthDto health = new PlantHealthDto("p1", 80, List.of("a"), List.of());
        Result<Void> result = useCase.execute(health);
        Assertions.assertTrue(result.isFailure());
    }
}
