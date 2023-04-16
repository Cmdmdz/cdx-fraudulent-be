package com.cdx.cdxlearningmaterials.controllers;

import com.cdx.cdxlearningmaterials.model.request.LogInRequest;
import com.cdx.cdxlearningmaterials.model.request.UserDeleteRequest;
import com.cdx.cdxlearningmaterials.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getuserAllByRoleIsUser (@RequestParam(name = "searchTerm", required = false) String searchTerm) {
        return userService.findAllUser(searchTerm);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> login (@Valid @RequestBody UserDeleteRequest request) {
        return userService.deleteByListId(request);
    }
}
