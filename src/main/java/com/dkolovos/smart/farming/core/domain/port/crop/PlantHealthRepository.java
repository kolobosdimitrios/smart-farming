/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.port.crop;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.PlantHealth;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dimitrioskolovos
 */
public interface PlantHealthRepository {
    
    Result<Void> insertPlantHealthRecord(PlantHealth plantHealth);
    
    Result<Optional<List<PlantHealth>>> getPlantHealthRecords(String plantId);
    
    Result<Optional<List<PlantHealth>>> getAllPlantHealthRecords();
    
    Result<Optional<List<PlantHealth>>> findByFieldOrCondition(String fieldId, String conditionType);
    
    Result<Void> deletePlantHealthStatus(PlantHealth plantHealth);
}
