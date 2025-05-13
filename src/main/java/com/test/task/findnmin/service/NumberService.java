package com.test.task.findnmin.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NumberService {

    public int findNMin(File file, int n) throws Exception {
        List<Integer> numbers = readNumbersFromExcel(file);

        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("Файл пуст или не содержит чисел.");
        }

        if (n < 1 || n > numbers.size()) {
            throw new IllegalArgumentException("N должно быть от 1 до " + numbers.size());
        }

        return quickSelect(numbers, 0, numbers.size() - 1, n - 1);
    }

    private List<Integer> readNumbersFromExcel(File file) throws Exception {
        List<Integer> numbers = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    numbers.add((int) cell.getNumericCellValue());
                }
            }
        }

        return numbers;
    }

    // Алгоритм Quick Select - имеет в среднем линейную сложность выполнения O(n)
    private int quickSelect(List<Integer> nums, int left, int right, int k) {
        if (left == right) {
            return nums.get(left);
        }

        int pivotIndex = partition(nums, left, right);

        if (k == pivotIndex) {
            return nums.get(k);
        } else if (k < pivotIndex) {
            return quickSelect(nums, left, pivotIndex - 1, k);
        } else {
            return quickSelect(nums, pivotIndex + 1, right, k);
        }
    }

    private int partition(List<Integer> nums, int left, int right) {
        int pivot = nums.get(right);
        int i = left;

        for (int j = left; j < right; j++) {
            if (nums.get(j) < pivot) {
                Collections.swap(nums, i, j);
                i++;
            }
        }

        Collections.swap(nums, i, right);
        return i;
    }
}

