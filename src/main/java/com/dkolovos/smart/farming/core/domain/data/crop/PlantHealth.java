/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.data.crop;

import java.util.List;

/**
 *
 * @author dimitrioskolovos
 */
public class PlantHealth {
    private final String plantId;
    private final int healthScore; // 0–100
    private final List<String> symptoms;
    private final List<String> nutrientDeficiencies;

    public PlantHealth(String plantId, int healthScore, List<String> symptoms, List<String> nutrientDeficiencies) {
        this.plantId = plantId;
        this.healthScore = healthScore;
        this.symptoms = symptoms;
        this.nutrientDeficiencies = nutrientDeficiencies;
    }

    public String getPlantId() {
        return plantId;
    }

    public int getHealthScore() {
        return healthScore;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public List<String> getNutrientDeficiencies() {
        return nutrientDeficiencies;
    }
    
    
}
