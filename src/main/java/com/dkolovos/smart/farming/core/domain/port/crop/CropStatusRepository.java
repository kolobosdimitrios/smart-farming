/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.port.crop;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.CropStatus;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dimitrioskolovos
 */
public interface CropStatusRepository {
    
    Result<Void> insertCropStatus(CropStatus cropStatus);
    
    Result<Optional<CropStatus>> getFieldsCropStatus(String fieldId);
    
    Result<List<CropStatus>> getAllCropsStatus();
    
    Result<Void> updateCropStatus(CropStatus cropStatus);
    
    Result<Void> deleteCropStatus(CropStatus cropStatus);
    
}
