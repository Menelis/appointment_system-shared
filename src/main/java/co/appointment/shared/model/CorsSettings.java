package co.appointment.shared.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CorsSettings {
    private List<String> allowedOrigins = new ArrayList<>();
    private List<String> allowedHeaders = new ArrayList<>();
    private List<String> allowedMethods = new ArrayList<>();
    private long maxAge = 3600;
}
