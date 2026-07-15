package com.itsmidris.edulead_crm.user.controller;

import com.itsmidris.edulead_crm.common.payload.ApiResponse;
import com.itsmidris.edulead_crm.common.util.ResponseBuilder;
import com.itsmidris.edulead_crm.user.dto.request.CreateUserRequest;
import com.itsmidris.edulead_crm.user.dto.response.UserResponse;
import com.itsmidris.edulead_crm.user.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(
            @Valid @RequestBody CreateUserRequest request) {

        UserResponse response = appUserService.createUser(request);

        ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User Created successfully")
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable Long id) {

        UserResponse response = appUserService.getUserById(id);
        ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User fetched successfully")
                .data(response)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {

        List<UserResponse> users = appUserService.getAllUsers();

        return ResponseEntity.ok(ResponseBuilder.success("Users fetched successfully",users));
    }
}
