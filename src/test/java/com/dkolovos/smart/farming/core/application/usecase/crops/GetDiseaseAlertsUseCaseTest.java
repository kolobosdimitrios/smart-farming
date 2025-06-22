package com.dkolovos.smart.farming.core.application.usecase.crops;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.DiseaseAlertDto;
import com.dkolovos.smart.farming.core.domain.repository.crop.DiseaseAlertRepository;
import com.dkolovos.smart.farming.core.infastracture.local_db.LocalDiseaseAlertRepositoryImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetDiseaseAlertsUseCaseTest {

    @Test
    void returnsAlertsFromRepository() {
        LocalDiseaseAlertRepositoryImpl repo = new LocalDiseaseAlertRepositoryImpl();
        repo.insertDiseaseAlert(new DiseaseAlertDto("p1", "rot", 3, "spray"));
        GetDiseaseAlertsUseCase useCase = new GetDiseaseAlertsUseCase(repo);
        Result<Optional<List<DiseaseAlertDto>>> result = useCase.execute();
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertFalse(result.getData().orElseThrow().isEmpty());
    }

    private static class FailingRepo implements DiseaseAlertRepository {
        @Override public Result<Void> insertDiseaseAlert(DiseaseAlertDto alert) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Void> updateDiseaseAlert(DiseaseAlertDto updatedAlert) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Void> deleteDiseaseAlert(DiseaseAlertDto alert) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Optional<List<DiseaseAlertDto>>> getDiseaseAlerts() { throw new RuntimeException("fail"); }
    }

    @Test
    void returnsFailureWhenRepositoryThrows() {
        GetDiseaseAlertsUseCase useCase = new GetDiseaseAlertsUseCase(new FailingRepo());
        Result<Optional<List<DiseaseAlertDto>>> result = useCase.execute();
        Assertions.assertTrue(result.isFailure());
    }
}
