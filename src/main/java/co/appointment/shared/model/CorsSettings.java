package co.appointment.shared.model;

import lombok.Data;

import java.util.List;

@Data
public class CorsSettings {
    private List<String> allowedOrigins;
    private List<String> allowedHeaders;
    private List<String> allowedMethods;
    private long maxAge;
}
