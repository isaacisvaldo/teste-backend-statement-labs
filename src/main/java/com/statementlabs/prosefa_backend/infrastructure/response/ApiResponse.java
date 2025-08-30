
package com.statementlabs.prosefa_backend.infrastructure.response;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import java.io.Serializable;

@Getter
@ToString
public class ApiResponse<T> implements Serializable {

    private final String message;
    private final HttpStatus status;
    private final int statusCode;
    private final T data;

    // Private constructor to enforce use of static factory methods
    private ApiResponse(String message, HttpStatus status, T data) {
        this.message = message;
        this.status = status;
        this.statusCode = status.value();
        this.data = data;
    }

    /**
     * Creates a successful API response with a message and data.
     */
    public static <T> ApiResponse<T> success(String message, HttpStatus status, T data) {
        return new ApiResponse<>(message, status, data);
    }

    /**
     * Creates a successful API response with data and a default "OK" status.
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("Operation successful.", HttpStatus.OK, data);
    }

    /**
     * Creates an error API response with a message and status.
     */
    public static <T> ApiResponse<T> error(String message, HttpStatus status) {
        return new ApiResponse<>(message, status, null);
    }
    
    /**
     * Creates a successful API response without any data.
     */
    public static <T> ApiResponse<T> noContent(String message) {
        return new ApiResponse<>(message, HttpStatus.NO_CONTENT, null);
    }
}