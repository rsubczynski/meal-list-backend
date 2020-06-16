package com.meal.list.backend.service.filestorange;

import com.meal.list.backend.configuration.FileStorageProperties;
import com.meal.list.backend.entity.Ingredient;
import com.meal.list.backend.error.exception.FileStorageException;
import com.meal.list.backend.service.ExcelParserService;
import com.meal.list.backend.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileStorageService {

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        Path fileStorageDishLocation = getPathLocation(fileStorageProperties.getUploadDishDir());
        Path fileStorageIngredientLocation = getPathLocation(fileStorageProperties.getUploadIngredientDir());

        try {
            Files.createDirectories(fileStorageDishLocation);
            Files.createDirectories(fileStorageIngredientLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public InputStream getInputStream(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        validateExcelFile(fileName);
        return file.getInputStream();
    }

    private void validateExcelFile(String fileName) {
        if (fileName.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }
    }

    Path getPathLocation(String location) {
        return Paths.get(location).toAbsolutePath().normalize();
    }
}
