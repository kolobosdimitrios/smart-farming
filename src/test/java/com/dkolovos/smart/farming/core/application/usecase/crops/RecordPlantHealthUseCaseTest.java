package com.dkolovos.smart.farming.core.application.usecase.crops;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.PlantHealth;
import com.dkolovos.smart.farming.core.domain.port.crop.PlantHealthRepository;
import com.dkolovos.smart.farming.core.infrastructure.db.local.LocalPlantHealthRepositoryImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordPlantHealthUseCaseTest {

    @Test
    void insertsPlantHealthRecord() {
        LocalPlantHealthRepositoryImpl repo = new LocalPlantHealthRepositoryImpl();
        RecordPlantHealthUseCase useCase = new RecordPlantHealthUseCase(repo);
        PlantHealth health = new PlantHealth("p1", 80, List.of("a"), List.of());
        Result<Void> result = useCase.execute(health);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertFalse(repo.getPlantHealthRecords("p1").getData().orElseThrow().isEmpty());
    }

    private static class FailingRepo implements PlantHealthRepository {
        @Override public Result<Void> insertPlantHealthRecord(PlantHealth plantHealth) { throw new RuntimeException("fail"); }
        @Override public Result<Optional<List<PlantHealth>>> getPlantHealthRecords(String plantId) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Optional<List<PlantHealth>>> getAllPlantHealthRecords() { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Void> deletePlantHealthStatus(PlantHealth plantHealth) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Optional<List<PlantHealth>>> findByFieldOrCondition(String fieldId, String conditionType) { return Result.failure(new RuntimeException("fail")); }
    }

    @Test
    void returnsFailureWhenRepositoryThrows() {
        RecordPlantHealthUseCase useCase = new RecordPlantHealthUseCase(new FailingRepo());
        PlantHealth health = new PlantHealth("p1", 80, List.of("a"), List.of());
        Result<Void> result = useCase.execute(health);
        Assertions.assertTrue(result.isFailure());
    }
}
