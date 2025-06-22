/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.repository.irrigation;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.irrigation.IrrigationSessionResultDto;

/**
 *
 * @author dimitrioskolovos
 */
public interface IrrigationSessionPort {
    void start(String zoneId);
    Result<IrrigationSessionResultDto> stop(String zoneId);
}
