/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.application.usecase.crops;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.DiseaseAlert;
import com.dkolovos.smart.farming.core.domain.port.crop.DiseaseAlertRepository;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dimitrioskolovos
 */
public class GetDiseaseAlertsUseCase {
    private final DiseaseAlertRepository repository;

    public GetDiseaseAlertsUseCase(DiseaseAlertRepository repository) {
        this.repository = repository;
    }

    public Result<Optional<List<DiseaseAlert>>> execute() {
        try {
            return repository.getDiseaseAlerts();
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
