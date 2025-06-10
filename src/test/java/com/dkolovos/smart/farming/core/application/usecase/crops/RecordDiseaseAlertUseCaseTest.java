package com.dkolovos.smart.farming.core.application.usecase.crops;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.DiseaseAlert;
import com.dkolovos.smart.farming.core.domain.port.crop.DiseaseAlertRepository;
import com.dkolovos.smart.farming.core.infrastructure.db.local.LocalDiseaseAlertRepositoryImpl;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordDiseaseAlertUseCaseTest {

    @Test
    void insertsDiseaseAlertUsingRepository() {
        LocalDiseaseAlertRepositoryImpl repo = new LocalDiseaseAlertRepositoryImpl();
        RecordDiseaseAlertUseCase useCase = new RecordDiseaseAlertUseCase(repo);
        DiseaseAlert alert = new DiseaseAlert("p1", "rot", 3, "spray");
        Result<Void> result = useCase.execute(alert);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertFalse(repo.getDiseaseAlerts().getData().orElseThrow().isEmpty());
    }

    private static class FailingRepo implements DiseaseAlertRepository {
        @Override public Result<Void> insertDiseaseAlert(DiseaseAlert alert) { throw new RuntimeException("fail"); }
        @Override public Result<Void> updateDiseaseAlert(DiseaseAlert updatedAlert) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Void> deleteDiseaseAlert(DiseaseAlert alert) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Optional<java.util.List<DiseaseAlert>>> getDiseaseAlerts() { return Result.failure(new RuntimeException("fail")); }
    }

    @Test
    void returnsFailureWhenRepositoryThrows() {
        RecordDiseaseAlertUseCase useCase = new RecordDiseaseAlertUseCase(new FailingRepo());
        DiseaseAlert alert = new DiseaseAlert("p1", "rot", 3, "spray");
        Result<Void> result = useCase.execute(alert);
        Assertions.assertTrue(result.isFailure());
    }
}
