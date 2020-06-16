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

    public void parseDish(InputStream stream) throws IOException {

        HSSFWorkbook wb = new HSSFWorkbook(stream);
        HSSFSheet sheet = wb.getSheetAt(0);
        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
        for (Row row : sheet) {
            for (Cell cell : row) {
                switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t\t");
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue() + "\t\t");
                        break;
                }
            }
            System.out.println();
        }
    }

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

