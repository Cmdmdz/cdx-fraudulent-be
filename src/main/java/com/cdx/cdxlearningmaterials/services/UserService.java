package com.cdx.cdxlearningmaterials.services;

import com.cdx.cdxlearningmaterials.model.request.UserDeleteRequest;
import com.cdx.cdxlearningmaterials.model.response.UserResponse;
import com.cdx.cdxlearningmaterials.repository.UserRepository;
import com.cdx.cdxlearningmaterials.repository.dao.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<?> findAllUser(String name) {

        List<User> userList = null;

        if (name != null) {
            userList = userRepository.findAllByName(name);
        } else {
            userList = userRepository.findAll();
        }

        List<UserResponse> responseList = new ArrayList<>();
        for (var user : userList) {
            responseList.add(UserResponse.builder()
                    .id(user.getUserId())
                    .name(user.getName())
                    .username(user.getUsername())
                    .status(user.getRole())
                    .build());
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteByListId(UserDeleteRequest Ids) {

        try {
            userRepository.deleteAllById(Ids.getIds());

        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>("delete success", HttpStatus.OK);
    }
}
