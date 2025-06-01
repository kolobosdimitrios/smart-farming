/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.application.usecase.crops;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.PlantHealth;
import com.dkolovos.smart.farming.core.domain.port.crop.PlantHealthRepository;

/**
 *
 * @author dimitrioskolovos
 */
public class RecordPlantHealthUseCase {
    private final PlantHealthRepository repository;

    public RecordPlantHealthUseCase(PlantHealthRepository repository) {
        this.repository = repository;
    }

    public Result<Void> execute(PlantHealth plantHealth) {
        try {
            repository.insertPlantHealthRecord(plantHealth);
            return Result.success(null);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
