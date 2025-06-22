package com.dkolovos.smart.farming.core.application.usecase.crops;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.DiseaseAlertDto;
import com.dkolovos.smart.farming.core.domain.repository.crop.DiseaseAlertRepository;
import com.dkolovos.smart.farming.core.infastracture.local_db.LocalDiseaseAlertRepositoryImpl;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordDiseaseAlertUseCaseTest {

    @Test
    void insertsDiseaseAlertUsingRepository() {
        LocalDiseaseAlertRepositoryImpl repo = new LocalDiseaseAlertRepositoryImpl();
        RecordDiseaseAlertUseCase useCase = new RecordDiseaseAlertUseCase(repo);
        DiseaseAlertDto alert = new DiseaseAlertDto("p1", "rot", 3, "spray");
        Result<Void> result = useCase.execute(alert);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertFalse(repo.getDiseaseAlerts().getData().orElseThrow().isEmpty());
    }

    private static class FailingRepo implements DiseaseAlertRepository {
        @Override public Result<Void> insertDiseaseAlert(DiseaseAlertDto alert) { throw new RuntimeException("fail"); }
        @Override public Result<Void> updateDiseaseAlert(DiseaseAlertDto updatedAlert) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Void> deleteDiseaseAlert(DiseaseAlertDto alert) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Optional<java.util.List<DiseaseAlertDto>>> getDiseaseAlerts() { return Result.failure(new RuntimeException("fail")); }
    }

    @Test
    void returnsFailureWhenRepositoryThrows() {
        RecordDiseaseAlertUseCase useCase = new RecordDiseaseAlertUseCase(new FailingRepo());
        DiseaseAlertDto alert = new DiseaseAlertDto("p1", "rot", 3, "spray");
        Result<Void> result = useCase.execute(alert);
        Assertions.assertTrue(result.isFailure());
    }
}
