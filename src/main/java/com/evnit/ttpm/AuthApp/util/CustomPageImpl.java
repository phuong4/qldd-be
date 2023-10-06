package com.evnit.ttpm.AuthApp.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.*;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
public class CustomPageImpl<T> {
    private static final long serialVersionUID = 3248189030448292002L;

    @JsonProperty("content")
    private List<T> content;

    @JsonProperty("number")
    private int number;

    @JsonProperty("size")
    private int size;

    @JsonProperty("totalElements")
    private Long totalElements;

    @JsonProperty("pageable")
    private JsonNode pageable;

    @JsonProperty("last")
    private boolean last;

    @JsonProperty("totalPages")
    private int totalPages;

    @JsonProperty("sort")
    private JsonNode sort;

    @JsonProperty("first")
    private boolean first;

    @JsonProperty("numberOfElements")
    private int numberOfElements;
}
