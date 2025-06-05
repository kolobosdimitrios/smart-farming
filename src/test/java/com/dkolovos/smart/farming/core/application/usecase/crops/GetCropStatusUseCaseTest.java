package com.dkolovos.smart.farming.core.application.usecase.crops;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.CropStage;
import com.dkolovos.smart.farming.core.domain.data.crop.CropStatus;
import com.dkolovos.smart.farming.core.domain.port.crop.CropStatusRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GetCropStatusUseCaseTest {

    private static class FailingRepository implements CropStatusRepository {
        @Override public Result<Void> insertCropStatus(CropStatus cropStatus) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Optional<CropStatus>> getFieldsCropStatus(String fieldId) { throw new RuntimeException("fail"); }
        @Override public Result<java.util.List<CropStatus>> getAllCropsStatus() { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Void> updateCropStatus(CropStatus cropStatus) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Void> deleteCropStatus(CropStatus cropStatus) { return Result.failure(new RuntimeException("fail")); }
    }

    @Test
    void returnsCropStatusWhenRepositorySucceeds() {
        CropStatusRepository repo = new CropStatusRepository() {
            private CropStatus status;
            @Override public Result<Void> insertCropStatus(CropStatus cropStatus) { status = cropStatus; return Result.success(null); }
            @Override public Result<Optional<CropStatus>> getFieldsCropStatus(String fieldId) { return Result.success(Optional.ofNullable(status)); }
            @Override public Result<java.util.List<CropStatus>> getAllCropsStatus() { return Result.success(java.util.List.of(status)); }
            @Override public Result<Void> updateCropStatus(CropStatus cropStatus) { status = cropStatus; return Result.success(null); }
            @Override public Result<Void> deleteCropStatus(CropStatus cropStatus) { status = null; return Result.success(null); }
        };

        CropStatus testStatus = new CropStatus("field1", CropStage.GERMINATION, 5, LocalDate.now(), "plant1");
        repo.insertCropStatus(testStatus);
        GetCropStatusUseCase useCase = new GetCropStatusUseCase(repo);
        Result<Optional<CropStatus>> result = useCase.execute("field1");
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertTrue(result.getData().isPresent());
        Assertions.assertEquals(testStatus.getFieldId(), result.getData().get().getFieldId());
    }

    @Test
    void returnsFailureWhenRepositoryThrowsException() {
        GetCropStatusUseCase useCase = new GetCropStatusUseCase(new FailingRepository());
        Result<Optional<CropStatus>> result = useCase.execute("field1");
        Assertions.assertTrue(result.isFailure());
        Assertions.assertNotNull(result.getError());
    }
}
