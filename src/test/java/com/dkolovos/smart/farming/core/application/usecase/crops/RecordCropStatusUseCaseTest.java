package com.dkolovos.smart.farming.core.application.usecase.crops;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.CropStageDto;
import com.dkolovos.smart.farming.core.domain.data.crop.CropStatusDto;
import com.dkolovos.smart.farming.core.domain.repository.crop.CropStatusRepository;
import com.dkolovos.smart.farming.core.infastracture.local_db.LocalCropStatusRepositoryImpl;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordCropStatusUseCaseTest {

    @Test
    void insertsStatusUsingRepository() {
        LocalCropStatusRepositoryImpl repo = new LocalCropStatusRepositoryImpl();
        RecordCropStatusUseCase useCase = new RecordCropStatusUseCase(repo);
        CropStatusDto status = new CropStatusDto("f1", CropStageDto.SEEDLING, 5, LocalDate.now(), "p1");
        Result<Void> result = useCase.execute(status);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertTrue(repo.getFieldsCropStatus("f1").getData().isPresent());
    }

    private static class FailingRepo implements CropStatusRepository {
        @Override public Result<Void> insertCropStatus(CropStatusDto cropStatus) { throw new RuntimeException("fail"); }
        @Override public Result<Optional<CropStatusDto>> getFieldsCropStatus(String fieldId) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<java.util.List<CropStatusDto>> getAllCropsStatus() { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Void> updateCropStatus(CropStatusDto cropStatus) { return Result.failure(new RuntimeException("fail")); }
        @Override public Result<Void> deleteCropStatus(CropStatusDto cropStatus) { return Result.failure(new RuntimeException("fail")); }
    }

    @Test
    void returnsFailureWhenRepositoryThrows() {
        RecordCropStatusUseCase useCase = new RecordCropStatusUseCase(new FailingRepo());
        CropStatusDto status = new CropStatusDto("f1", CropStageDto.SEEDLING, 5, LocalDate.now(), "p1");
        Result<Void> result = useCase.execute(status);
        Assertions.assertTrue(result.isFailure());
    }
}
