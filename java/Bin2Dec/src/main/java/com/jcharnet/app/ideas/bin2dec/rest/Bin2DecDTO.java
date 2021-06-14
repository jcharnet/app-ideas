package com.jcharnet.app.ideas.bin2dec.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Bin2DecDTO (@JsonProperty("binaryNumber") String binaryNumber, @JsonProperty("decimalNumber") Double decimalNumber, @JsonProperty("userName") String userName) {
    
}
