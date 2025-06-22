/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.repository.field;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.field.SoilDto;
import java.util.List;

/**
 *
 * @author dimitrioskolovos
 */
public interface SoilRepository {
    Result<Void> insertSoil(SoilDto soil);
    Result<SoilDto> getSoil(String id);
    Result<List<SoilDto>> getAllSoils();
    Result<Void> deleteSoil(SoilDto soil);
    Result<Void> deleteSoilById(String id);
    Result<SoilDto> updateSoil(SoilDto newSoil);
}
