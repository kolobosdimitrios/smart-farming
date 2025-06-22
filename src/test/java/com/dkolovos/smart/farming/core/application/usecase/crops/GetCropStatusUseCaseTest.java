package com.dkolovos.smart.farming.core.application.usecase.crops;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.CropStageDto;
import com.dkolovos.smart.farming.core.domain.data.crop.CropStatusDto;
import com.dkolovos.smart.farming.core.domain.repository.crop.CropStatusRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GetCropStatusUseCaseTest {

    private static class FailingRepository implements CropStatusRepository {
        @Override public Result<Void> insertCropStatus(CropStatusDto cropStatus) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Optional<CropStatusDto>> getFieldsCropStatus(String fieldId) { throw new RuntimeException("fail"); }
        @Override public Result<java.util.List<CropStatusDto>> getAllCropsStatus() { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Void> updateCropStatus(CropStatusDto cropStatus) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Void> deleteCropStatus(CropStatusDto cropStatus) { return Result.failure(new RuntimeException("fail")); }
    }

    @Test
    void returnsCropStatusWhenRepositorySucceeds() {
        CropStatusRepository repo = new CropStatusRepository() {
            private CropStatusDto status;
            @Override public Result<Void> insertCropStatus(CropStatusDto cropStatus) { status = cropStatus; return Result.success(null); }
            @Override public Result<Optional<CropStatusDto>> getFieldsCropStatus(String fieldId) { return Result.success(Optional.ofNullable(status)); }
            @Override public Result<java.util.List<CropStatusDto>> getAllCropsStatus() { return Result.success(java.util.List.of(status)); }
            @Override public Result<Void> updateCropStatus(CropStatusDto cropStatus) { status = cropStatus; return Result.success(null); }
            @Override public Result<Void> deleteCropStatus(CropStatusDto cropStatus) { status = null; return Result.success(null); }
        };

        CropStatusDto testStatus = new CropStatusDto("field1", CropStageDto.GERMINATION, 5, LocalDate.now(), "plant1");
        repo.insertCropStatus(testStatus);
        GetCropStatusUseCase useCase = new GetCropStatusUseCase(repo);
        Result<Optional<CropStatusDto>> result = useCase.execute("field1");
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertTrue(result.getData().isPresent());
        Assertions.assertEquals(testStatus.getFieldId(), result.getData().get().getFieldId());
    }

    @Test
    void returnsFailureWhenRepositoryThrowsException() {
        GetCropStatusUseCase useCase = new GetCropStatusUseCase(new FailingRepository());
        Result<Optional<CropStatusDto>> result = useCase.execute("field1");
        Assertions.assertTrue(result.isFailure());
        Assertions.assertNotNull(result.getError());
    }
}
