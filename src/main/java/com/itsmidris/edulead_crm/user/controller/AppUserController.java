package com.itsmidris.edulead_crm.user.controller;

import com.itsmidris.edulead_crm.common.payload.ApiResponse;
import com.itsmidris.edulead_crm.common.util.ResponseBuilder;
import com.itsmidris.edulead_crm.user.dto.request.CreateUserRequest;
import com.itsmidris.edulead_crm.user.dto.response.UserResponse;
import com.itsmidris.edulead_crm.user.service.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Management", description = "Manage CRM users")
@RestController
@RequestMapping("/api/v1/users")
@Validated
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Operation(summary = "Create User", description = "Creates a new CRM user.")
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

    @Operation(summary = "Get User", description = "Fetches user details using the user ID.")
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

    @Operation(summary = "Get All Users", description = "Returns all active CRM users.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {

        List<UserResponse> users = appUserService.getAllUsers();

        return ResponseEntity.ok(ResponseBuilder.success("Users fetched successfully",users));
    }

    @Operation(summary = "Disable User", description = "Soft deletes a CRM user.")
    @PatchMapping("/{id}/disable")
    public ResponseEntity<ApiResponse<Void>> disableUser(@PathVariable Long id) {

        appUserService.disableUser(id);

        return ResponseEntity.ok(ResponseBuilder.success("User disabled Successfully",null));

    }
}
