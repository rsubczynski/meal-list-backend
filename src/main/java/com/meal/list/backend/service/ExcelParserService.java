package com.meal.list.backend.service;

import com.meal.list.backend.entity.Ingredient;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExcelParserService {

    public Set<Ingredient> parseIngredient(InputStream stream) throws IOException {
        Set<Ingredient> ingredientList = new HashSet<Ingredient>();
        HSSFWorkbook wb = new HSSFWorkbook(stream);
        HSSFSheet sheet = wb.getSheetAt(0);

        for (Row row : sheet) {
            ingredientList.add(
                    Ingredient.builder()
                            .name(row.getCell(0).getStringCellValue())
                            .protein(row.getCell(1).getNumericCellValue())
                            .carbohydrate(row.getCell(2).getNumericCellValue())
                            .fat(row.getCell(3).getNumericCellValue())
                            .kcal(row.getCell(4).getNumericCellValue())
                            .build());
        }
        return ingredientList;
    }

}

