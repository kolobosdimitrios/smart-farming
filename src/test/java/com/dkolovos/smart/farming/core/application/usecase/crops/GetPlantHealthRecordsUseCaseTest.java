package com.dkolovos.smart.farming.core.application.usecase.crops;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.PlantHealth;
import com.dkolovos.smart.farming.core.domain.port.crop.PlantHealthRepository;
import com.dkolovos.smart.farming.core.infrastructure.db.local.LocalPlantHealthRepositoryImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetPlantHealthRecordsUseCaseTest {

    @Test
    void returnsRecordsFromRepository() {
        LocalPlantHealthRepositoryImpl repo = new LocalPlantHealthRepositoryImpl();
        repo.insertPlantHealthRecord(new PlantHealth("p1", 90, List.of(), List.of()));
        GetPlantHealthRecordsUseCase useCase = new GetPlantHealthRecordsUseCase(repo);
        Result<Optional<List<PlantHealth>>> result = useCase.execute("p1");
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertFalse(result.getData().orElseThrow().isEmpty());
    }

    private static class FailingRepo implements PlantHealthRepository {
        @Override public Result<Void> insertPlantHealthRecord(PlantHealth plantHealth) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Optional<List<PlantHealth>>> getPlantHealthRecords(String plantId) { throw new RuntimeException("fail"); }
        @Override public Result<Optional<List<PlantHealth>>> getAllPlantHealthRecords() { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Void> deletePlantHealthStatus(PlantHealth plantHealth) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Optional<List<PlantHealth>>> findByFieldOrCondition(String fieldId, String conditionType) { return Result.failure(new RuntimeException("fail")); }
    }

    @Test
    void returnsFailureWhenRepositoryThrows() {
        GetPlantHealthRecordsUseCase useCase = new GetPlantHealthRecordsUseCase(new FailingRepo());
        Result<Optional<List<PlantHealth>>> result = useCase.execute("p1");
        Assertions.assertTrue(result.isFailure());
    }
}
