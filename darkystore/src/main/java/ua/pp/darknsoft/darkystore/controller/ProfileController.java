package ua.pp.darknsoft.darkystore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pp.darknsoft.darkystore.dto.ProfileResponseDto;
import ua.pp.darknsoft.darkystore.service.IProfileService;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final IProfileService iProfileService;

    @GetMapping
    public ResponseEntity<ProfileResponseDto> getProfile() {
        ProfileResponseDto responseDto = iProfileService.getProfile();
        return ResponseEntity.ok(responseDto);
    }
}
