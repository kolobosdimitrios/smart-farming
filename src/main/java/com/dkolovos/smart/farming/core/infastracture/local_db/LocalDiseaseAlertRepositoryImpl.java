/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.infastracture.local_db;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.DiseaseAlertDto;
import com.dkolovos.smart.farming.core.domain.repository.crop.DiseaseAlertRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dimitrioskolovos
 */
public class LocalDiseaseAlertRepositoryImpl implements DiseaseAlertRepository{
    private final List<DiseaseAlertDto> alerts = new ArrayList<>();

    @Override
    public Result<Void> insertDiseaseAlert(DiseaseAlertDto alert) {
        alerts.add(alert);
        return Result.success(null);
    }

    @Override
    public Result<Void> updateDiseaseAlert(DiseaseAlertDto updatedAlert) {
        deleteDiseaseAlert(updatedAlert);
        alerts.add(updatedAlert);
        return Result.success(null);
    }

    @Override
    public Result<Void> deleteDiseaseAlert(DiseaseAlertDto alert) {
        alerts.removeIf(a -> a.getPlantId().equals(alert.getPlantId()));
        return Result.success(null);
    }

    @Override
    public Result<Optional<List<DiseaseAlertDto>>> getDiseaseAlerts() {
        return Result.success(Optional.of(new ArrayList<>(alerts)));
    }
}
