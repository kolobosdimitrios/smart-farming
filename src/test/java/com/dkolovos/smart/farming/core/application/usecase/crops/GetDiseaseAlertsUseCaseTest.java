package com.dkolovos.smart.farming.core.application.usecase.crops;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.DiseaseAlert;
import com.dkolovos.smart.farming.core.domain.port.crop.DiseaseAlertRepository;
import com.dkolovos.smart.farming.core.infrastructure.db.local.LocalDiseaseAlertRepositoryImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetDiseaseAlertsUseCaseTest {

    @Test
    void returnsAlertsFromRepository() {
        LocalDiseaseAlertRepositoryImpl repo = new LocalDiseaseAlertRepositoryImpl();
        repo.insertDiseaseAlert(new DiseaseAlert("p1", "rot", 3, "spray"));
        GetDiseaseAlertsUseCase useCase = new GetDiseaseAlertsUseCase(repo);
        Result<Optional<List<DiseaseAlert>>> result = useCase.execute();
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertFalse(result.getData().orElseThrow().isEmpty());
    }

    private static class FailingRepo implements DiseaseAlertRepository {
        @Override public Result<Void> insertDiseaseAlert(DiseaseAlert alert) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Void> updateDiseaseAlert(DiseaseAlert updatedAlert) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Void> deleteDiseaseAlert(DiseaseAlert alert) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Optional<List<DiseaseAlert>>> getDiseaseAlerts() { throw new RuntimeException("fail"); }
    }

    @Test
    void returnsFailureWhenRepositoryThrows() {
        GetDiseaseAlertsUseCase useCase = new GetDiseaseAlertsUseCase(new FailingRepo());
        Result<Optional<List<DiseaseAlert>>> result = useCase.execute();
        Assertions.assertTrue(result.isFailure());
    }
}
