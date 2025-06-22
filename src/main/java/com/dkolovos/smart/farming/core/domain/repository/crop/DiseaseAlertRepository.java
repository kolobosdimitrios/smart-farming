/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.repository.crop;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.DiseaseAlertDto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dimitrioskolovos
 */
public interface DiseaseAlertRepository {
    
    Result<Void> insertDiseaseAlert(DiseaseAlertDto diseaseAlert);
    
    Result<Optional<List<DiseaseAlertDto>>> getDiseaseAlerts();
    
    Result<Void> updateDiseaseAlert(DiseaseAlertDto diseaseAlert);
    
    Result<Void> deleteDiseaseAlert(DiseaseAlertDto diseaseAlert);
}
