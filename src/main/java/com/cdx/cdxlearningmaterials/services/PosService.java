package com.cdx.cdxlearningmaterials.services;

import com.cdx.cdxlearningmaterials.model.request.ApproveRequest;
import com.cdx.cdxlearningmaterials.model.request.PosRequest;
import com.cdx.cdxlearningmaterials.model.request.UserDeleteRequest;
import com.cdx.cdxlearningmaterials.model.response.PosResponse;
import com.cdx.cdxlearningmaterials.model.response.UserResponse;
import com.cdx.cdxlearningmaterials.repository.PosRepository;
import com.cdx.cdxlearningmaterials.repository.dao.Pos;
import com.cdx.cdxlearningmaterials.repository.dao.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PosService {

    private final PosRepository posRepository;

    public ResponseEntity<?> findAllPos(String name, String role) {

        List<Pos> posList = null;

        if (name != null) {
            posList = posRepository.findAllByName(name);
        } else {
            posList = posRepository.findAll();
        }

        List<PosResponse> responseList = new ArrayList<>();
        for (var pos : posList) {
            if (Objects.equals(role, "admin")) {
                if (!pos.getStatus()){
                    responseList.add(PosResponse.builder()
                            .id(pos.getId())
                            .name(pos.getName())
                            .line(pos.getLine())
                            .facebook(pos.getFacebook())
                            .account(pos.getAccount())
                            .phone(pos.getPhone())
                            .description(pos.getDescription())
                            .image(pos.getImage())
                            .status(pos.getStatus())
                            .build());
                }

            } else {
                if (pos.getStatus()) {
                    responseList.add(PosResponse.builder()
                            .id(pos.getId())
                            .name(pos.getName())
                            .line(pos.getLine())
                            .facebook(pos.getFacebook())
                            .account(pos.getAccount())
                            .phone(pos.getPhone())
                            .description(pos.getDescription())
                            .image(pos.getImage())
                            .status(pos.getStatus())
                            .build());
                }
            }
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    public ResponseEntity<?> createPost(PosRequest request) {
        try {
            posRepository.save(Pos.builder()
                    .status(false)
                    .account(request.getAccount())
                    .description(request.getDescription())
                    .facebook(request.getFacebook())
                    .phone(request.getPhone())
                    .name(request.getName())
                    .line(request.getLine())
                    .image(request.getImage())
                    .build());
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("save success", HttpStatus.OK);

    }

    public ResponseEntity<?> approveByListId(ApproveRequest Ids) {
        try {
            List<Pos> posList = posRepository.findAllById(Ids.getIds());
            for (var pos : posList) {
                pos.setStatus(true);
                posRepository.save(pos);
            }

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("update success", HttpStatus.OK);
    }
}
