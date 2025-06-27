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
public class PlantDto {
    private final String plantId;
    private final String name;
    private final String species;
    private final List<String> tags;

    public PlantDto(String plantId, String name, String species, List<String> tags) {
        this.plantId = plantId;
        this.name = name;
        this.species = species;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public List<String> getTags() {
        return tags;
    }
    
    
}
