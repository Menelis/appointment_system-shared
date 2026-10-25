package co.appointment.shared.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(boolean success, T data, String message) {

    public static <T> ApiResponse<T> error(final String message) {
        return new ApiResponse<>(false, null, message);
    }

    public static <T> ApiResponse<T> success(final T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<T> success(final String message) {
        return new ApiResponse<>(true, null, message);
    }
}
