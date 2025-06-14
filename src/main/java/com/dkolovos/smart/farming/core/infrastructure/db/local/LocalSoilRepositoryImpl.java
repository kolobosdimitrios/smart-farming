/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.infrastructure.db.local;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.field.Soil;
import com.dkolovos.smart.farming.core.domain.port.field.SoilRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 *
 * @author dimitrioskolovos
 */
public class LocalSoilRepositoryImpl implements SoilRepository {

    private final ArrayList<Soil> soils = new ArrayList<>();

    @Override
    public Result<Void> insertSoil(Soil soil) {
        for (Soil storeSoil : soils) {
            if (storeSoil.getId().equals(soil.getId())) {
                return Result.failure(new IllegalStateException("Value already inserted!"));
            }
        }
        this.soils.add(soil);
        return Result.success(null);

    }

    @Override
    public Result<Soil> getSoil(String id) {
        return soils.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .map(Result::success)
                .orElse(Result.failure(new IllegalStateException("No soil record found for id=" + id)));
    }

    @Override
    public Result<List<Soil>> getAllSoils() {
        return Result.success(new ArrayList<>(soils));
    }

    @Override
    public Result<Void> deleteSoil(Soil soil) {
        Optional<Soil> match = this.soils.stream()
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
        Optional<Soil> match = this.soils.stream()
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
    public Result<Soil> updateSoil(Soil newSoil) {
        for (int i = 0; i < soils.size(); i++) {
            if (soils.get(i).getId().equals(newSoil.getId())) {
                soils.set(i, newSoil);
                return Result.success(newSoil);
            }
        }
        return Result.failure(new IllegalArgumentException("Soil with ID " + newSoil.getId() + " not found."));
    }

}
