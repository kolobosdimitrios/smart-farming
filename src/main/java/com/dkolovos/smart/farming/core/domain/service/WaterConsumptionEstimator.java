/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.service;

import java.time.Instant;

/**
 *
 * @author dimitrioskolovos
 */
public interface WaterConsumptionEstimator {
    float estimate(String zoneId, Instant start, Instant end);
}
