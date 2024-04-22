package com.example.applicationorderservice.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class Field {
    private String type;
    private boolean optional;
    private String field;

    public Field() {
    }

    public Field(String type, boolean optional, String field) {
        this.type = type;
        this.optional = optional;
        this.field = field;
    }
}
