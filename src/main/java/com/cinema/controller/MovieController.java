package com.cinema.controller;

import com.cinema.dto.movie.MovieDTO;
import com.cinema.service.MovieService;
import com.cinema.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/movie")
public class MovieController {
    private final CustomFileUtil fileUtil;
    private final MovieService movieService;

    @PostMapping("/")                  // 영화 등록
    public Map<String, String> register(
            @ModelAttribute MovieDTO movieDTO,
            @RequestParam(value = "file") MultipartFile file) {

        // 파일 확장자 검증
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && !isValidImageFile(originalFilename)) {
            return Map.of("error", "Invalid file type. Only JPG and PNG are allowed.");
        }

        // 파일을 저장하고 저장된 이름을 가져옴
        String savedFileName = fileUtil.saveFile(file);
        movieDTO.setPosterUrl(savedFileName); // 포스터 URL에 저장된 파일 이름 설정

        // 영화 정보를 데이터베이스에 저장
        Long movieNum = movieService.register(movieDTO); // 등록 후 영화 번호 반환

        // 성공 메시지 반환
        return Map.of("message", "Movie registered successfully", "movieNum", String.valueOf(movieNum), "fileName", savedFileName);
    }

    @PutMapping("/{movieNum}") // 영화 수정
    public ResponseEntity<Void> modifyMovie(
            @PathVariable Long movieNum,
            @ModelAttribute MovieDTO movieDTO,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        // 파일 확장자 검증 (업데이트할 파일이 있을 경우)
        if (file != null && !file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename != null && !isValidImageFile(originalFilename)) {
                return ResponseEntity.badRequest().body(null); // 잘못된 파일 형식
            }
            // 파일을 저장하고 저장된 이름을 가져옴
            String savedFileName = fileUtil.saveFile(file);
            movieDTO.setPosterUrl(savedFileName); // 포스터 URL에 저장된 파일 이름 설정
        }

        // 영화 수정
        movieDTO.setMovieNum(movieNum); // DTO에 ID 세팅
        movieService.modify(movieDTO);

        return ResponseEntity.ok().build(); // 성공 응답
    }

    @DeleteMapping("/{movieNum}") // 영화 삭제
    public ResponseEntity<Void> removeMovie(@PathVariable Long movieNum) {
        movieService.remove(movieNum);
        return ResponseEntity.noContent().build(); // 삭제 성공 응답
    }

    private boolean isValidImageFile(String filename) {   // 파일 형식을 검증하는 메소드
        String lowerCaseName = filename.toLowerCase();
        return lowerCaseName.endsWith(".jpg") || lowerCaseName.endsWith(".jpeg") || lowerCaseName.endsWith(".png");
    }
}
