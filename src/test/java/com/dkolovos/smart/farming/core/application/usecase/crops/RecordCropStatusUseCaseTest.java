package com.dkolovos.smart.farming.core.application.usecase.crops;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.CropStage;
import com.dkolovos.smart.farming.core.domain.data.crop.CropStatus;
import com.dkolovos.smart.farming.core.domain.port.crop.CropStatusRepository;
import com.dkolovos.smart.farming.core.infrastructure.db.local.LocalCropStatusRepositoryImpl;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordCropStatusUseCaseTest {

    @Test
    void insertsStatusUsingRepository() {
        LocalCropStatusRepositoryImpl repo = new LocalCropStatusRepositoryImpl();
        RecordCropStatusUseCase useCase = new RecordCropStatusUseCase(repo);
        CropStatus status = new CropStatus("f1", CropStage.SEEDLING, 5, LocalDate.now(), "p1");
        Result<Void> result = useCase.execute(status);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertTrue(repo.getFieldsCropStatus("f1").getData().isPresent());
    }

    private static class FailingRepo implements CropStatusRepository {
        @Override public Result<Void> insertCropStatus(CropStatus cropStatus) { throw new RuntimeException("fail"); }
        @Override public Result<Optional<CropStatus>> getFieldsCropStatus(String fieldId) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<java.util.List<CropStatus>> getAllCropsStatus() { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Void> updateCropStatus(CropStatus cropStatus) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Void> deleteCropStatus(CropStatus cropStatus) { return Result.failure(new RuntimeException("fail")); }
    }

    @Test
    void returnsFailureWhenRepositoryThrows() {
        RecordCropStatusUseCase useCase = new RecordCropStatusUseCase(new FailingRepo());
        CropStatus status = new CropStatus("f1", CropStage.SEEDLING, 5, LocalDate.now(), "p1");
        Result<Void> result = useCase.execute(status);
        Assertions.assertTrue(result.isFailure());
    }
}
