/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.infrastructure.db.local;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.crop.PlantHealth;
import com.dkolovos.smart.farming.core.domain.port.crop.PlantHealthRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 *
 * @author dimitrioskolovos
 */
public class LocalPlantHealthRepositoryImpl implements PlantHealthRepository {

    private final Map<String, List<PlantHealth>> store = new ConcurrentHashMap<>();

    @Override
    public Result<Void> insertPlantHealthRecord(PlantHealth plantHealth) {
        store.computeIfAbsent(plantHealth.getPlantId(), k -> new ArrayList<>()).add(plantHealth);
        return Result.success(null);
    }

    @Override
    public Result<Optional<List<PlantHealth>>> getPlantHealthRecords(String plantId) {
        return Result.success(Optional.ofNullable(store.getOrDefault(plantId, List.of())));
    }

    @Override
    public Result<Optional<List<PlantHealth>>> getAllPlantHealthRecords() {
        List<PlantHealth> all = store.values().stream().flatMap(List::stream).collect(Collectors.toList());
        return Result.success(Optional.of(all));
    }

    @Override
    public Result<Void> deletePlantHealthStatus(PlantHealth plantHealth) {
        List<PlantHealth> records = store.get(plantHealth.getPlantId());
        if (records != null) {
            records.removeIf(r -> r.equals(plantHealth));
        }
        return Result.success(null);
    }

    @Override
    public Result<Optional<List<PlantHealth>>> findByFieldOrCondition(String fieldId, String conditionType) {
        try {
            List<PlantHealth> result = store.getOrDefault(fieldId, List.of()).stream()
                    .filter(ph -> ph.getSymptoms().stream().anyMatch(symptom -> symptom.equalsIgnoreCase(conditionType)))
                    .collect(Collectors.toList());

            return Result.success(Optional.of(result));
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
