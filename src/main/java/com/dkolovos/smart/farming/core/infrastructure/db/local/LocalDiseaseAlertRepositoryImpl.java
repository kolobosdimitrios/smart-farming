/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.infrastructure.db.local;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.DiseaseAlert;
import com.dkolovos.smart.farming.core.domain.port.crop.DiseaseAlertRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dimitrioskolovos
 */
public class LocalDiseaseAlertRepositoryImpl implements DiseaseAlertRepository{
    private final List<DiseaseAlert> alerts = new ArrayList<>();

    @Override
    public Result<Void> insertDiseaseAlert(DiseaseAlert alert) {
        alerts.add(alert);
        return Result.success(null);
    }

    @Override
    public Result<Void> updateDiseaseAlert(DiseaseAlert updatedAlert) {
        deleteDiseaseAlert(updatedAlert);
        alerts.add(updatedAlert);
        return Result.success(null);
    }

    @Override
    public Result<Void> deleteDiseaseAlert(DiseaseAlert alert) {
        alerts.removeIf(a -> a.getPlantId().equals(alert.getPlantId()));
        return Result.success(null);
    }

    @Override
    public Result<Optional<List<DiseaseAlert>>> getDiseaseAlerts() {
        return Result.success(Optional.of(new ArrayList<>(alerts)));
    }
}
