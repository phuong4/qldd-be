package com.evnit.ttpm.AuthApp.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseServiceData<T> {
    private T Data;
    @JsonProperty("STATE")
    private String state;
    @JsonProperty("MESSAGE")
    private String message;
}
