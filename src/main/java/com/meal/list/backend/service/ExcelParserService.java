package com.meal.list.backend.service;

import com.meal.list.backend.entity.Ingredient;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExcelParserService {

    private final static int NAME_ROW_INDEX = 0;
    private final static int PROTEIN_ROW_INDEX = 1;
    private final static int CARBOHYDRATE_ROW_INDEX = 2;
    private final static int FAT_ROW_INDEX = 3;
    private final static int KCAL_ROW_INDEX = 4;


    public Set<Ingredient> parseIngredient(InputStream stream) throws IOException {
        Set<Ingredient> ingredientList = new HashSet<Ingredient>();
        HSSFWorkbook wb = new HSSFWorkbook(stream);
        HSSFSheet sheet = wb.getSheetAt(0);

        for (Row row : sheet) {
            ingredientList.add(
                    Ingredient.builder()
                            .name(row.getCell(NAME_ROW_INDEX).getStringCellValue())
                            .protein(row.getCell(PROTEIN_ROW_INDEX).getNumericCellValue())
                            .carbohydrate(row.getCell(CARBOHYDRATE_ROW_INDEX).getNumericCellValue())
                            .fat(row.getCell(FAT_ROW_INDEX).getNumericCellValue())
                            .kcal(row.getCell(KCAL_ROW_INDEX).getNumericCellValue())
                            .build());
        }
        return ingredientList;
    }

}

