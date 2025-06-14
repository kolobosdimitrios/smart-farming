/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.data.field;

import java.util.ArrayList;

/**
 *
 * @author dimitrioskolovos
 */
public class Soil {
    
    private final String id;
    private final String name;
    private final String status;
    private final float score;
    private final ArrayList<SoilValue> soilValues;

    public Soil(String id, String name, String status, float score) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.score = score;
        this.soilValues = new ArrayList<>();
    }
    
    public Soil(String id){
        this(id, "", "", 0f);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public float getScore() {
        return score;
    }

    public ArrayList<SoilValue> getSoilValues() {
        return soilValues;
    }
    
    public void addSoilValue(SoilValue soilValue){
        this.soilValues.add(soilValue);
    }
    
    public SoilValue getSoilValue(int index){
        return this.soilValues.get(index);
    }
    
    
}
