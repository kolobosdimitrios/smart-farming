/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.infastracture.local_db;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.CropStatusDto;
import com.dkolovos.smart.farming.core.domain.repository.crop.CropStatusRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author dimitrioskolovos
 */
public class LocalCropStatusRepositoryImpl implements CropStatusRepository{

    private final Map<String, CropStatusDto> store = new ConcurrentHashMap<>();

    @Override
    public Result<Void> insertCropStatus(CropStatusDto cropStatus) {
        store.put(cropStatus.getFieldId(), cropStatus);
        return Result.success(null);
    }

    @Override
    public Result<Optional<CropStatusDto>> getFieldsCropStatus(String fieldId) {
        return Result.success(Optional.ofNullable(store.get(fieldId)));
    }

    @Override
    public Result<Void> updateCropStatus(CropStatusDto cropStatus) {
        store.put(cropStatus.getFieldId(), cropStatus);
        return Result.success(null);
    }

    @Override
    public Result<Void> deleteCropStatus(CropStatusDto cropStatus) {
        store.remove(cropStatus.getFieldId());
        return Result.success(null);
    }

    @Override
    public Result<List<CropStatusDto>> getAllCropsStatus() {
        return Result.success(new ArrayList<>(store.values()));
    }
    
}
