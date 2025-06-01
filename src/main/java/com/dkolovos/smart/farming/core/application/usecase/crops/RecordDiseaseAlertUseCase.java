/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.application.usecase.crops;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.DiseaseAlert;
import com.dkolovos.smart.farming.core.domain.port.crop.DiseaseAlertRepository;

/**
 *
 * @author dimitrioskolovos
 */
public class RecordDiseaseAlertUseCase {
     private final DiseaseAlertRepository repository;

    public RecordDiseaseAlertUseCase(DiseaseAlertRepository repository) {
        this.repository = repository;
    }

    public Result<Void> execute(DiseaseAlert alert) {
        try {
            repository.insertDiseaseAlert(alert);
            return Result.success(null);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
