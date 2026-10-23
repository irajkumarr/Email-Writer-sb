package com.rajkumartimalsina.emailwriter;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailWriterController {

    private final EmailWriterService emailWriterService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateEmail(@Valid @RequestBody EmailRequest request) {
        String response = emailWriterService.generateEmailReply(request);
        return ResponseEntity.ok(response);
    }
}
