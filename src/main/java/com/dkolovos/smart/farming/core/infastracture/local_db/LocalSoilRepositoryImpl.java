/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.infastracture.local_db;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.field.SoilDto;
import com.dkolovos.smart.farming.core.domain.repository.field.SoilRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 *
 * @author dimitrioskolovos
 */
public class LocalSoilRepositoryImpl implements SoilRepository {

    private final ArrayList<SoilDto> soils = new ArrayList<>();

    @Override
    public Result<Void> insertSoil(SoilDto soil) {
        for (SoilDto storeSoil : soils) {
            if (storeSoil.getId().equals(soil.getId())) {
                return Result.failure(new IllegalStateException("Value already inserted!"));
            }
        }
        this.soils.add(soil);
        return Result.success(null);

    }

    @Override
    public Result<SoilDto> getSoil(String id) {
        return soils.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .map(Result::success)
                .orElse(Result.failure(new IllegalStateException("No soil record found for id=" + id)));
    }

    @Override
    public Result<List<SoilDto>> getAllSoils() {
        return Result.success(new ArrayList<>(soils));
    }

    @Override
    public Result<Void> deleteSoil(SoilDto soil) {
        Optional<SoilDto> match = this.soils.stream()
                .filter(s -> s.getId().equals(soil.getId()))
                .findAny();

        if (match.isPresent()) {
            this.soils.remove(match.get());
            return Result.success(null);
        } else {
            return Result.failure(new IllegalStateException("No match for given soil to delete!"));
        }
    }

    @Override
    public Result<Void> deleteSoilById(String id) {
        Optional<SoilDto> match = this.soils.stream()
                .filter(s -> s.getId().equals(id))
                .findAny();

        if (match.isPresent()) {
            this.soils.remove(match.get());
            return Result.success(null);
        } else {
            return Result.failure(new IllegalStateException("No match for given soil id to delete!"));
        }
    }

    @Override
    public Result<SoilDto> updateSoil(SoilDto newSoil) {
        for (int i = 0; i < soils.size(); i++) {
            if (soils.get(i).getId().equals(newSoil.getId())) {
                soils.set(i, newSoil);
                return Result.success(newSoil);
            }
        }
        return Result.failure(new IllegalArgumentException("Soil with ID " + newSoil.getId() + " not found."));
    }

}
