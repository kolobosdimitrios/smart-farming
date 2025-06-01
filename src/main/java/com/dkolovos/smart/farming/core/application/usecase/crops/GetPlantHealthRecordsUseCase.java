/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.application.usecase.crops;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.PlantHealth;
import com.dkolovos.smart.farming.core.domain.port.crop.PlantHealthRepository;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dimitrioskolovos
 */
public class GetPlantHealthRecordsUseCase {
     private final PlantHealthRepository repository;

    public GetPlantHealthRecordsUseCase(PlantHealthRepository repository) {
        this.repository = repository;
    }

    public Result<Optional<List<PlantHealth>>> execute(String plantId) {
        try {
            return repository.getPlantHealthRecords(plantId);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
