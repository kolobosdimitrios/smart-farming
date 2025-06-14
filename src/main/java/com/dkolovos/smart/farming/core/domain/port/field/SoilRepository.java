/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.port.field;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.field.Soil;
import java.util.List;

/**
 *
 * @author dimitrioskolovos
 */
public interface SoilRepository {
    Result<Void> insertSoil(Soil soil);
    Result<Soil> getSoil(String id);
    Result<List<Soil>> getAllSoils();
    Result<Void> deleteSoil(Soil soil);
    Result<Void> deleteSoilById(String id);
    Result<Soil> updateSoil(Soil newSoil);
}
