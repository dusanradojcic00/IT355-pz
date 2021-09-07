package com.met.it355pz.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private static String UPLOADED_FOLDER = "/uploads";

    @PostMapping
    public ResponseEntity<?> postImage(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
        if (multipartFile.isEmpty()) {
            return ResponseEntity.ok("No file uploaded");
        }

        try {
            Path root = Paths.get("uploads");
            byte[] bytes = multipartFile.getBytes();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-A");
            String originalName = multipartFile.getOriginalFilename();
            String extension = originalName.substring(originalName.lastIndexOf('.') + 1);
            String fileName = originalName.substring(0, originalName.lastIndexOf('.'));
            Path path = Paths.get(UPLOADED_FOLDER + fileName + LocalDateTime.now().format(formatter) + "." + extension);
            Files.copy(multipartFile.getInputStream(), root.resolve(multipartFile.getOriginalFilename()));
            //Files.write(path, bytes);
            String test = request.getServletContext().getContextPath();
            return ResponseEntity.ok("File uploaded at: " + path.toString() + " " + test);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok("Error");
        }


    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        Resource resource = null;
        try {
            Path root = Paths.get("uploads");
            Path file = root.resolve(filename);
            resource = new UrlResource(file.toUri());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }


    }
}
