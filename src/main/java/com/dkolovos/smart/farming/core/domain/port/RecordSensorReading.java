/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.port;

import com.dkolovos.smart.farming.core.domain.data.SensorReading;

/**
 *
 * @author dimitrioskolovos
 */
public interface RecordSensorReading {
    void save(SensorReading sensorReading);
    boolean exists(SensorReading sensorReading);
}
