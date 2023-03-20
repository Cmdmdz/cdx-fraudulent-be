package com.cdx.cdxlearningmaterials.controllers;

import com.cdx.cdxlearningmaterials.model.request.ScoreRequest;
import com.cdx.cdxlearningmaterials.services.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController()
public class ScoreController {
    private final ScoreService scoreService;

    // info page
    @GetMapping("/score/user/{userId}")
    public ResponseEntity<?> countScoreByUserId (@Valid @PathVariable Long userId ) {
        return scoreService.findAllScoreOfUserId(userId);
    }

    // score page
    @GetMapping("/score/all")
    public ResponseEntity<?> getScoreOfAllUser () {
        return scoreService.findScoreOfAllUser();
    }

    // game page
    @PostMapping("/score/add")
    public ResponseEntity<?> addScore (@Valid @RequestBody ScoreRequest request) {
        return scoreService.addScore(request);
    }

    // page members
    @GetMapping("/user/all")
    public ResponseEntity<?> getuserAllByRoleIsUser () {
        return scoreService.findAllUserByRoleIsUser();
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteScoreById (@Valid @PathVariable Long userId) {
        return scoreService.deleteUserByIdAndScore(userId);
    }
}
