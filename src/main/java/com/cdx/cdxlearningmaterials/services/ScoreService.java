package com.cdx.cdxlearningmaterials.services;

import com.cdx.cdxlearningmaterials.model.request.ScoreRequest;
import com.cdx.cdxlearningmaterials.model.response.GetScoreResponse;
import com.cdx.cdxlearningmaterials.model.response.UserResponse;
import com.cdx.cdxlearningmaterials.repository.ScoreRepository;
import com.cdx.cdxlearningmaterials.repository.UserRepository;
import com.cdx.cdxlearningmaterials.repository.dao.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> addScore(ScoreRequest request) {
        var user = userRepository.findByUserId(request.getUserId());
        if (user == null) {
            return new ResponseEntity<>("Can not process", HttpStatus.NOT_FOUND);
        }
        var scoreList = scoreRepository.findByUserIdAndAndGameEP(user.getUserId(), request.getGameEp());

        if (scoreList == null){
            scoreRepository.save(Score.builder()
                            .score(request.getScore())
                            .userId(request.getUserId())
                            .gameEP(request.getGameEp())
                            .updateDate(LocalDate.now())
                    .build());

        }else {
            scoreList.setScore(request.getScore());
            scoreRepository.save(scoreList);
        }
        return new ResponseEntity<>("Save Success", HttpStatus.OK);

    }

    public ResponseEntity<?> findAllScoreOfUserId(Long userId) {
        var score = scoreRepository.sumScoreByUserId(userId);
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    public ResponseEntity<?> findScoreOfAllUser() {
        var userList = userRepository.findAllByRole("user");
        var response = new GetScoreResponse();
        List<GetScoreResponse> responseList = new ArrayList<>();
        for (var user: userList) {
            var score = scoreRepository.sumScoreByUserId(user.getUserId());
            responseList.add(GetScoreResponse.builder()
                            .fullName(user.getFirstname().concat(" ").concat(user.getLastname()))
                            .score(score)
                            .userId(user.getUserId())
                    .build());
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);

    }

    public ResponseEntity<?> findAllUserByRoleIsUser() {
        var userList = userRepository.findAllByRole("user");
        var userResponse = new UserResponse();
        List<UserResponse> responseList = new ArrayList<>();
        for (var user:userList) {
            responseList.add(UserResponse.builder()
                            .userId(user.getUserId())
                            .fullname(user.getFirstname().concat(" ").concat(user.getLastname()))
                            .dob(user.getDob())
                            .username(user.getUsername())
                            .status(user.getRole())
                    .build());
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);

    }

    public ResponseEntity<?> deleteUserByIdAndScore(Long userId){

        if (userId == null){
            return new ResponseEntity<>("Error is null", HttpStatus.BAD_REQUEST);
        }
        var scoreList = scoreRepository.findAllByUserId(userId);

        if (scoreList.isEmpty()){
            return new ResponseEntity<>("Error is not found this userId : "+ userId, HttpStatus.NOT_FOUND);
        }

        for (var score:scoreList) {
            scoreRepository.deleteById(score.getScoreId());

        }

        userRepository.deleteById(userId);

        return new ResponseEntity<>("delete success", HttpStatus.OK);

    }


}
