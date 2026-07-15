package com.itsmidris.edulead_crm.common.util;

import com.itsmidris.edulead_crm.common.payload.ApiResponse;

public final class ResponseBuilder {

    private ResponseBuilder() {
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }
}