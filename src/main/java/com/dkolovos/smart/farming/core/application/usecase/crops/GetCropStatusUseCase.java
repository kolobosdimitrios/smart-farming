/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.application.usecase.crops;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.CropStatus;
import com.dkolovos.smart.farming.core.domain.port.crop.CropStatusRepository;
import java.util.Optional;

/**
 *
 * @author dimitrioskolovos
 */
public class GetCropStatusUseCase {
    private final CropStatusRepository repository;

    public GetCropStatusUseCase(CropStatusRepository repository) {
        this.repository = repository;
    }

    public Result<Optional<CropStatus>> execute(String fieldId) {
        try {
            return repository.getFieldsCropStatus(fieldId);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
