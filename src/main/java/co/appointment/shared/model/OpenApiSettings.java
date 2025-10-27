package co.appointment.shared.model;

import lombok.Data;

@Data
public class OpenApiSettings {
    private OpenApiInfo info;

    @Data
    public static class OpenApiInfo {
        private String title;
        private String description;
        private String version;
    }
}
