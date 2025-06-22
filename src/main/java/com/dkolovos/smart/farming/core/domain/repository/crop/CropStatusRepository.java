/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.repository.crop;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.CropStatusDto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dimitrioskolovos
 */
public interface CropStatusRepository {
    
    Result<Void> insertCropStatus(CropStatusDto cropStatus);
    
    Result<Optional<CropStatusDto>> getFieldsCropStatus(String fieldId);
    
    Result<List<CropStatusDto>> getAllCropsStatus();
    
    Result<Void> updateCropStatus(CropStatusDto cropStatus);
    
    Result<Void> deleteCropStatus(CropStatusDto cropStatus);
    
}
