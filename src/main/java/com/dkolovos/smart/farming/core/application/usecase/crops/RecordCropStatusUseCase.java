/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.application.usecase.crops;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.CropStatus;
import com.dkolovos.smart.farming.core.domain.port.crop.CropStatusRepository;

/**
 *
 * @author dimitrioskolovos
 */
public class RecordCropStatusUseCase {

    private final CropStatusRepository repository;

    public RecordCropStatusUseCase(CropStatusRepository repository) {
        this.repository = repository;
    }

    public Result<Void> execute(CropStatus status) {
        try {
            repository.insertCropStatus(status);
            return Result.success(null);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
