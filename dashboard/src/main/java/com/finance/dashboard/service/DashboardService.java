package com.finance.dashboard.service;

import com.finance.dashboard.entity.RecordEntity;
import com.finance.dashboard.mapper.DashboardMapper;
import com.finance.dashboard.repository.RecordRepository;
import com.financeswagger.Swaggerex.model.DashboardDTO;
import com.financeswagger.Swaggerex.model.DashboardDTOCategorySummary;
import com.financeswagger.Swaggerex.model.RecordDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DashboardService {
    @Autowired
    private final RecordRepository recordRepository;

    @Autowired
    private final DashboardMapper dashboardMapper;

    public ResponseEntity<List<DashboardDTO>> getDashboardData() {

        List<RecordEntity> records = recordRepository.findAll();

        DashboardDTO dashboard = buildDashboard(records);

        return ResponseEntity.ok(List.of(dashboard));
    }

    private DashboardDTO buildDashboard(List<RecordEntity> records) {

        DashboardDTO dto = new DashboardDTO();

        // 1️⃣ Total Income
        float totalIncome = (float) records.stream()
                .filter(r -> "income".equalsIgnoreCase(r.getRecordType()))
                .mapToDouble(RecordEntity::getAmount)
                .sum();

        // 2️⃣ Total Expense
        float totalExpense = (float) records.stream()
                .filter(r -> "expense".equalsIgnoreCase(r.getRecordType()))
                .mapToDouble(RecordEntity::getAmount)
                .sum();

        // 3️⃣ Net Balance
        float netBalance = totalIncome - totalExpense;

        dto.setTotalIncome(totalIncome);
        dto.setTotalExpenses(totalExpense);
        dto.setNetBalance(netBalance);

        // 4️⃣ Recent Transactions (latest 5)
        List<RecordDTO> recentTransactions = records.stream()
                .sorted((r1, r2) -> r2.getCreatedAt().compareTo(r1.getCreatedAt()))
                .limit(5)
                .map(dashboardMapper::convertToDTO)
                .toList();

        dto.setRecentTransactions(recentTransactions);

        // 5️⃣ Category Summary
        List<DashboardDTOCategorySummary> categorySummary = records.stream()
                .collect(Collectors.groupingBy(
                        RecordEntity::getCategory,
                        Collectors.summingDouble(RecordEntity::getAmount)
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    DashboardDTOCategorySummary cs = new DashboardDTOCategorySummary();
                    cs.setCategory(entry.getKey());
                    cs.setTotal(entry.getValue().floatValue());
                    return cs;
                })
                .toList();

        dto.setCategorySummary(categorySummary);

        return dto;
    }
}
