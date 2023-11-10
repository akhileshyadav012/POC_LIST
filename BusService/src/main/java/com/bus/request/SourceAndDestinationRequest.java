package com.bus.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SourceAndDestinationRequest {
    private String source;
    private String destination;
    private String date;
}
