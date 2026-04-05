package com.finance.dashboard.controller;

import com.finance.dashboard.service.DashboardService;
import com.financeswagger.Swaggerex.api.DashboardApi;
import com.financeswagger.Swaggerex.model.DashboardDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/finance")
@AllArgsConstructor
public class DashboardController implements DashboardApi {

    @Autowired
    private final DashboardService dashboardService;

    @Override
    public ResponseEntity<List<DashboardDTO>> getDashboardData() {
        return dashboardService.getDashboardData();
    }
}
