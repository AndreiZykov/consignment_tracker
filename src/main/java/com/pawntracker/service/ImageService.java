package com.pawntracker.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ImageService {

    void saveImage(Path path, byte[] bytes) throws IOException {
        Files.write(path, bytes);
    }

}
