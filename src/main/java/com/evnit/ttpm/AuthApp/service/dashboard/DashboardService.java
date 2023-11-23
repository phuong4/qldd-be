package com.evnit.ttpm.AuthApp.service.dashboard;

import java.util.List;
import com.evnit.ttpm.AuthApp.model.dashboard.KdNtXlscDto;
import com.evnit.ttpm.AuthApp.model.dashboard.NmdTbaRglDto;

//@Service
public interface DashboardService {
    List<NmdTbaRglDto> getNmdDataToChart();
    List<NmdTbaRglDto> getTbaDataToChart();
    List<NmdTbaRglDto> getRglDataToChart();
    List<KdNtXlscDto> getKdDataToChart();
    List<KdNtXlscDto> getNtDataToChart();
    List<KdNtXlscDto> getXlscDataToChart();
}
