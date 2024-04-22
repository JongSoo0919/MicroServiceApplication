package com.example.applicationorderservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class KafkaOrderDto implements Serializable {
    private Schema schema;
    private Payload payload;

    @Builder
    public KafkaOrderDto(Schema schema, Payload payload) {
        this.schema = schema;
        this.payload = payload;
    }
}
