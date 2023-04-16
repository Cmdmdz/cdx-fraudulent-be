package com.cdx.cdxlearningmaterials.controllers;

import com.cdx.cdxlearningmaterials.model.request.ApproveRequest;
import com.cdx.cdxlearningmaterials.model.request.PosRequest;
import com.cdx.cdxlearningmaterials.model.request.UserDeleteRequest;
import com.cdx.cdxlearningmaterials.repository.PosRepository;
import com.cdx.cdxlearningmaterials.repository.dao.Pos;
import com.cdx.cdxlearningmaterials.services.PosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PosController {

    private final PosService posService;
    private final PosRepository posRepository;

    @GetMapping("/pos")
    public ResponseEntity<?> getuserAllByRoleIsUser (@RequestParam(name = "searchTerm", required = false) String searchTerm,
                                                     @RequestParam(name = "role") String role) {
        return posService.findAllPos(searchTerm,role);
    }

    @PostMapping("/approve")
    public ResponseEntity<?> approve (@Valid @RequestBody ApproveRequest request) {
        return posService.approveByListId(request);
    }

    @PostMapping("/create")
    public ResponseEntity<?> submitPost(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("account") String account,
            @RequestParam("facebook") String facebook,
            @RequestParam("line") String line,
            @RequestParam("phone") String phone,
            @RequestParam("image") MultipartFile image) {

        String imagePath = "/assets/images/" + image.getOriginalFilename();
        Path path = Paths.get(imagePath);
        try {
            Files.createDirectories(path.getParent());
            Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save image");
        }

        // Save post data to database
        Pos post = new Pos();
        post.setName(name);
        post.setDescription(description);
        post.setAccount(account);
        post.setFacebook(facebook);
        post.setLine(line);
        post.setPhone(phone);
        post.setImage(imagePath);
        post.setStatus(false);
        Pos savedPost = posRepository.save(post);

        if (savedPost != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Post saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save post");
        }
    }

}
