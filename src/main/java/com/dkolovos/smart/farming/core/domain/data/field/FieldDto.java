package com.dkolovos.smart.farming.core.domain.data.field;

import java.util.Objects;

public class FieldDto {
    private final String id;
    private final String name;
    private final AreaAbstrct area;
    

    public FieldDto(String id, String name, AreaAbstrct area) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AreaAbstrct getArea() {
        return area;
    }
}
