/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.data.crop;

/**
 *
 * @author dimitrioskolovos
 */
public class DiseaseAlert {
    private final String plantId;
    private final String diseaseName;
    private final int severityScore;
    private final String suggestedTreatment;

    public DiseaseAlert(String plantId, String diseaseName, int severityScore, String suggestedTreatment) {
        this.plantId = plantId;
        this.diseaseName = diseaseName;
        this.severityScore = severityScore;
        this.suggestedTreatment = suggestedTreatment;
    }

    public String getPlantId() {
        return plantId;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public int getSeverityScore() {
        return severityScore;
    }

    public String getSuggestedTreatment() {
        return suggestedTreatment;
    }
    
    
}
