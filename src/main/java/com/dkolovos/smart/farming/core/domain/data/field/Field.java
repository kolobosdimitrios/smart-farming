package com.dkolovos.smart.farming.core.domain.data.field;

import java.util.Objects;

public class Field {
    private final String id;
    private final String name;
    private final Area area;
    

    public Field(String id, String name, Area area) {
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

    public Area getArea() {
        return area;
    }
}
