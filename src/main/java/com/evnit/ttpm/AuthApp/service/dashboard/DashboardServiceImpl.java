package com.evnit.ttpm.AuthApp.service.dashboard;

import java.util.List;

import com.evnit.ttpm.AuthApp.model.dashboard.KdNtXlscDto;
import com.evnit.ttpm.AuthApp.model.dashboard.NmdTbaRglDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<NmdTbaRglDto> getNmdDataToChart() {
        String sql;
        List<NmdTbaRglDto> lst;
        sql = "EXECUTE sp_DashboardNmdTbaRgl 'NM', 'NM', 1";
        lst = jdbcTemplate.query(sql, new BeanPropertyRowMapper<NmdTbaRglDto>(NmdTbaRglDto.class));
        return lst;
    }

    @Override
    public List<NmdTbaRglDto> getTbaDataToChart() {
        List<NmdTbaRglDto> lst;
        String sql = "EXECUTE sp_DashboardNmdTbaRgl 'TBA', 'TBA', 2";
        lst = jdbcTemplate.query(sql, new BeanPropertyRowMapper<NmdTbaRglDto>(NmdTbaRglDto.class));
        return lst;
    }

    @Override
    public List<NmdTbaRglDto> getRglDataToChart() {
        List<NmdTbaRglDto> lst;
        String sql = "EXECUTE sp_DashboardNmdTbaRgl 'RGL', 'RGL', 3";
        lst = jdbcTemplate.query(sql, new BeanPropertyRowMapper<NmdTbaRglDto>(NmdTbaRglDto.class));
        return lst;
    }

    public List<KdNtXlscDto> getKdDataToChart() {
        List<KdNtXlscDto> lst;
        String sql = "EXECUTE sp_DashboardKD";
        lst = jdbcTemplate.query(sql, new BeanPropertyRowMapper<KdNtXlscDto>(KdNtXlscDto.class));
        return lst;
    }

    public List<KdNtXlscDto> getNtDataToChart() {
        List<KdNtXlscDto> lst;
        String sql = "EXECUTE sp_DashboardNT";
        lst = jdbcTemplate.query(sql, new BeanPropertyRowMapper<KdNtXlscDto>(KdNtXlscDto.class));
        return lst;
    }

    public List<KdNtXlscDto> getXlscDataToChart() {
        String sql;
        List<KdNtXlscDto> lst;
        sql = "EXECUTE sp_DashboardXLSC";
        lst = jdbcTemplate.query(sql, new BeanPropertyRowMapper<KdNtXlscDto>(KdNtXlscDto.class));
        return lst;
    }
}
