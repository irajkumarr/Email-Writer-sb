package com.rajkumartimalsina.emailwriter;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailWriterController {

    private final EmailWriterService emailWriterService;
}
