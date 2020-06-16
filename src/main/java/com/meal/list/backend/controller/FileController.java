package com.meal.list.backend.controller;

import com.meal.list.backend.entity.Ingredient;
import com.meal.list.backend.service.ExcelParserService;
import com.meal.list.backend.service.IngredientService;
import com.meal.list.backend.service.filestorange.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@RestController
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ExcelParserService excelParserService;

    @Autowired
    private IngredientService ingredientService;

//    @PostMapping("/uploadDishExcelFile")
//    public ResponseEntity uploadDishExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
//        Set<Ingredient> dishSet = excelParserService.parseDish(fileStorageService.storeFile(file));
//        return ResponseEntity.ok(dishSet);
//    }

    @PostMapping("/uploadIngredientExcelFile")
    public ResponseEntity ingredient(@RequestParam("file") MultipartFile file) throws IOException {
        Set<Ingredient> ingredientSet = excelParserService.parseIngredient(fileStorageService.getInputStream(file));
        ingredientService.saveAllIngredient(ingredientSet);
        return ResponseEntity.ok(ingredientSet);
    }
}
