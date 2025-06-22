package com.dkolovos.smart.farming.core.infrastructure.db.local;

import com.dkolovos.smart.farming.core.infastracture.local_db.LocalCropStatusRepositoryImpl;
import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.CropStageDto;
import com.dkolovos.smart.farming.core.domain.data.crop.CropStatusDto;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocalCropStatusRepositoryImplTest {

    @Test
    void insertAndRetrieveCropStatus() {
        LocalCropStatusRepositoryImpl repo = new LocalCropStatusRepositoryImpl();
        CropStatusDto status = new CropStatusDto(
                "field1",
                CropStageDto.SEEDLING,
                10,
                LocalDate.now().plusDays(30),
                "plant1"
        );

        Result<Void> insertResult = repo.insertCropStatus(status);
        Assertions.assertTrue(insertResult.isSuccess());

        Result<Optional<CropStatusDto>> fetchResult = repo.getFieldsCropStatus("field1");
        Assertions.assertTrue(fetchResult.isSuccess());
        Assertions.assertTrue(fetchResult.getData().isPresent());
        CropStatusDto fetched = fetchResult.getData().get();
        Assertions.assertEquals(status.getFieldId(), fetched.getFieldId());
        Assertions.assertEquals(status.getStage(), fetched.getStage());
        Assertions.assertEquals(status.getDaysToNextStage(), fetched.getDaysToNextStage());
        Assertions.assertEquals(status.getPredictedHarvestDate(), fetched.getPredictedHarvestDate());
    }

    @Test
    void getFieldsCropStatusReturnsEmptyWhenNotFound() {
        LocalCropStatusRepositoryImpl repo = new LocalCropStatusRepositoryImpl();
        Result<Optional<CropStatusDto>> result = repo.getFieldsCropStatus("unknown");
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertTrue(result.getData().isEmpty());
    }
}
