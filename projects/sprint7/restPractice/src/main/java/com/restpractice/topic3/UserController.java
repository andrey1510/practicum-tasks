package com.restpractice.topic3;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
class UserController {

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(List.of(new User(1L, "Иван Иванов")));
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(201).body(user);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok("Файл загружен: " + file.getOriginalFilename());
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> downloadFile() {
        byte[] fileData = new byte[1024];
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "file.txt");
        return ResponseEntity.ok().headers(headers).body(fileData);
    }
}

class User {
    private final Long id;
    private final String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
