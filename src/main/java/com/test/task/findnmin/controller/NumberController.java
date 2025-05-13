package com.test.task.findnmin.controller;

import com.test.task.findnmin.service.NumberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/api")
@Tag(name = "Number Controller", description = "API для работы с числами из файла")
public class NumberController {

    @Autowired
    private NumberService numberService;

    @PostMapping("/find-n-min")
    @Operation(
            summary = "Найти N-ное минимальное число из файла по заданному пути к локальному файлу",
            description = "Передается путь к .xlsx локлаьному файлу и число N" +
                    "Возвращает N-ое минимальное число из файла"
    )
    public ResponseEntity<?> findNMin(
            @Parameter(description = "Полный путь к .xlsx файлу (C:/data/numbers.xlsx)")
            @RequestParam String filePath,

            @Parameter(description = "N - порядковый номер минимального числа (начиная с 1)")
            @RequestParam int n
    ) {
        try {
            File file = new File(filePath);
            if (!file.exists() || !filePath.toLowerCase().endsWith(".xlsx")) {
                return ResponseEntity.badRequest().body("Файл не найден или не соответствует .xlsx");
            }

            int result = numberService.findNMin(file, n);
            return ResponseEntity.ok(result);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Ошибка при обработке файла: " + e.getMessage());
        }
    }
}

