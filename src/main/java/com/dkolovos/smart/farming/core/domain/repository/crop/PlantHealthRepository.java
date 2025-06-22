/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.repository.crop;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.PlantHealthDto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dimitrioskolovos
 */
public interface PlantHealthRepository {
    
    Result<Void> insertPlantHealthRecord(PlantHealthDto plantHealth);
    
    Result<Optional<List<PlantHealthDto>>> getPlantHealthRecords(String plantId);
    
    Result<Optional<List<PlantHealthDto>>> getAllPlantHealthRecords();
    
    Result<Optional<List<PlantHealthDto>>> findByFieldOrCondition(String fieldId, String conditionType);
    
    Result<Void> deletePlantHealthStatus(PlantHealthDto plantHealth);
}
