/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.port.crop;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.DiseaseAlert;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dimitrioskolovos
 */
public interface DiseaseAlertRepository {
    
    Result<Void> insertDiseaseAlert(DiseaseAlert diseaseAlert);
    
    Result<Optional<List<DiseaseAlert>>> getDiseaseAlerts();
    
    Result<Void> updateDiseaseAlert(DiseaseAlert diseaseAlert);
    
    Result<Void> deleteDiseaseAlert(DiseaseAlert diseaseAlert);
}
