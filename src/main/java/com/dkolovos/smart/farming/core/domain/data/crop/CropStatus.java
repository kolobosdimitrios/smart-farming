/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.data.crop;

import java.time.LocalDate;

/**
 *
 * @author dimitrioskolovos
 */
public class CropStatus {
    private final String fieldId;
    private final CropStage stage;
    private final int daysToNextStage;
    private final LocalDate predictedHarvestDate;
    private final String plantId;

    public CropStatus(String fieldId, CropStage stage, int daysToNextStage, LocalDate predictedHarvestDate, String plantId) {
        this.fieldId = fieldId;
        this.stage = stage;
        this.daysToNextStage = daysToNextStage;
        this.predictedHarvestDate = predictedHarvestDate;
        this.plantId = plantId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public CropStage getStage() {
        return stage;
    }

    public int getDaysToNextStage() {
        return daysToNextStage;
    }

    public LocalDate getPredictedHarvestDate() {
        return predictedHarvestDate;
    }

    public String getPlantId() {
        return plantId;
    }
    
    
}
