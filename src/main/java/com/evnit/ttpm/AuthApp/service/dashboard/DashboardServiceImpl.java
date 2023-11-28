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

//-- Truy vấn trong getNmdDataToChart, getTbaDataToChart, getRglDataToChart về cơ bản là giống nhau, dưới đây là truy vấn trong getNmdDataToChart
//-- Chọn ra tháng lớn nhất trong VIEW_NGHIEM_THU_FINAL và tháng lớn nhất trong VIEW_KIEM_DINH_FINAL để sau đó so sánh
//    WITH COMPAREMONTH AS (
//    SELECT MAX(THANGNT) AS THANG
//    FROM VIEW_NGHIEM_THU_FINAL
//    WHERE POWERSYSTEMTYPED = 'NM'
//
//    UNION ALL
//
//    SELECT MAX(THANGKD) AS THANG
//    FROM VIEW_KIEM_DINH_FINAL
//    WHERE KIEU = 'NM'
//)
//
//        -- Lựa chọn dãy 12 tháng
//, TwelveMonths AS (
//            SELECT DISTINCT
//        DATEADD(MONTH, - n, MaxDate) AS Thang
//    FROM COMPAREMONTH
//    CROSS APPLY (
//            SELECT MAX(THANG) AS MaxDate
//    FROM COMPAREMONTH
//    ) MaxDateInfo
//    CROSS APPLY (VALUES (0),(1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11)) AS Numbers(n)
//)
//
//        -- Tính tổng số lượng kiểm định theo từng tháng với loại nhà máy 'NM'
//            , KiemDinhData AS (
//            SELECT
//                    COALESCE(SUM(SOLUONG), 0) AS soLuongKD,
//    TwelveMonths.Thang AS thang
//    FROM TwelveMonths
//    LEFT JOIN VIEW_KIEM_DINH_FINAL ON TwelveMonths.Thang = VIEW_KIEM_DINH_FINAL.THANGKD AND KIEU = 'NM'
//    GROUP BY TwelveMonths.Thang
//)
//
//        -- Tính tổng số lượng nghiệm thu theo từng tháng với loại nhà máy 'NM'
//            , NghiemThuData AS (
//            SELECT
//                    COALESCE(SUM(AMOUNT), 0) AS soLuongNT,
//    TwelveMonths.Thang AS thang
//    FROM TwelveMonths
//    LEFT JOIN VIEW_NGHIEM_THU_FINAL ON TwelveMonths.Thang = VIEW_NGHIEM_THU_FINAL.THANGNT AND POWERSYSTEMTYPED = 'NM'
//    GROUP BY TwelveMonths.Thang
//)
//
//        -- Ghép dữ liệu từ hai bảng con trên
//    SELECT
//    KD.soLuongKD,
//    NT.soLuongNT,
//    FORMAT(KD.thang, 'MM-yyyy') AS thang
//    FROM KiemDinhData KD
//    JOIN NghiemThuData NT ON KD.thang = NT.thang
//    ORDER BY KD.THANG DESC

    @Override
    public List<NmdTbaRglDto> getNmdDataToChart() {
        String sql;
        List<NmdTbaRglDto> lst;
        sql = "WITH COMPAREMONTH AS (SELECT MAX(THANGNT) AS THANG FROM VIEW_NGHIEM_THU_FINAL WHERE POWERSYSTEMTYPED = 'NM' UNION ALL SELECT MAX(THANGKD) AS THANG FROM VIEW_KIEM_DINH_FINAL WHERE KIEU = 'NM' UNION ALL SELECT DATEFROMPARTS(YEAR(MAX(EndDate)), MONTH(MAX(EndDate)), '01') AS THANG FROM View_Problem WHERE Type = 1), TwelveMonths AS (SELECT DISTINCT DATEADD(MONTH, - n, MaxDate) AS Thang FROM COMPAREMONTH CROSS APPLY (SELECT MAX(THANG) AS MaxDate FROM COMPAREMONTH) MaxDateInfo CROSS APPLY (VALUES (0),(1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11)) AS Numbers(n)), KiemDinhData AS (SELECT COALESCE(SUM(SOLUONG), 0) AS soLuongKD, TwelveMonths.Thang AS thang FROM TwelveMonths LEFT JOIN VIEW_KIEM_DINH_FINAL ON TwelveMonths.Thang = VIEW_KIEM_DINH_FINAL.THANGKD AND KIEU = 'NM' GROUP BY TwelveMonths.Thang), NghiemThuData AS (SELECT COALESCE(SUM(AMOUNT), 0) AS soLuongNT, TwelveMonths.Thang AS thang FROM TwelveMonths LEFT JOIN VIEW_NGHIEM_THU_FINAL ON TwelveMonths.Thang = VIEW_NGHIEM_THU_FINAL.THANGNT AND POWERSYSTEMTYPED = 'NM' GROUP BY TwelveMonths.Thang), XuLySuCoData AS (SELECT COALESCE(SUM(Count), 0) AS soLuongXLSC, TwelveMonths.Thang AS thang FROM TwelveMonths LEFT JOIN View_Problem ON TwelveMonths.Thang = DATEFROMPARTS(YEAR(View_Problem.EndDate), MONTH(View_Problem.EndDate), '01') AND Type = 1 GROUP BY TwelveMonths.Thang) SELECT KD.soLuongKD, NT.soLuongNT, XLSC.soLuongXLSC, FORMAT(KD.thang, 'MM-yyyy') AS thang FROM KiemDinhData KD JOIN NghiemThuData NT ON KD.thang = NT.thang JOIN XuLySuCoData XLSC ON KD.thang = XLSC.thang ORDER BY KD.THANG DESC";
        lst = jdbcTemplate.query(sql, new BeanPropertyRowMapper<NmdTbaRglDto>(NmdTbaRglDto.class));
        return lst;
    }

    @Override
    public List<NmdTbaRglDto> getTbaDataToChart() {
        List<NmdTbaRglDto> lst;
        String sql = "WITH COMPAREMONTH AS (SELECT MAX(THANGNT) AS THANG FROM VIEW_NGHIEM_THU_FINAL WHERE POWERSYSTEMTYPED = 'TBA' UNION ALL SELECT MAX(THANGKD) AS THANG FROM VIEW_KIEM_DINH_FINAL WHERE KIEU = 'TBA' UNION ALL SELECT DATEFROMPARTS(YEAR(MAX(EndDate)), MONTH(MAX(EndDate)), '01') AS THANG FROM View_Problem WHERE Type = 2), TwelveMonths AS (SELECT DISTINCT DATEADD(MONTH, - n, MaxDate) AS Thang FROM COMPAREMONTH CROSS APPLY (SELECT MAX(THANG) AS MaxDate FROM COMPAREMONTH) MaxDateInfo CROSS APPLY (VALUES (0),(1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11)) AS Numbers(n)), KiemDinhData AS (SELECT COALESCE(SUM(SOLUONG), 0) AS soLuongKD, TwelveMonths.Thang AS thang FROM TwelveMonths LEFT JOIN VIEW_KIEM_DINH_FINAL ON TwelveMonths.Thang = VIEW_KIEM_DINH_FINAL.THANGKD AND KIEU = 'TBA' GROUP BY TwelveMonths.Thang), NghiemThuData AS (SELECT COALESCE(SUM(AMOUNT), 0) AS soLuongNT, TwelveMonths.Thang AS thang FROM TwelveMonths LEFT JOIN VIEW_NGHIEM_THU_FINAL ON TwelveMonths.Thang = VIEW_NGHIEM_THU_FINAL.THANGNT AND POWERSYSTEMTYPED = 'TBA' GROUP BY TwelveMonths.Thang), XuLySuCoData AS (SELECT COALESCE(SUM(Count), 0) AS soLuongXLSC, TwelveMonths.Thang AS thang FROM TwelveMonths LEFT JOIN View_Problem ON TwelveMonths.Thang = DATEFROMPARTS(YEAR(View_Problem.EndDate), MONTH(View_Problem.EndDate), '01') AND Type = 2 GROUP BY TwelveMonths.Thang) SELECT KD.soLuongKD, NT.soLuongNT, XLSC.soLuongXLSC, FORMAT(KD.thang, 'MM-yyyy') AS thang FROM KiemDinhData KD JOIN NghiemThuData NT ON KD.thang = NT.thang JOIN XuLySuCoData XLSC ON KD.thang = XLSC.thang ORDER BY KD.THANG DESC";
        lst = jdbcTemplate.query(sql, new BeanPropertyRowMapper<NmdTbaRglDto>(NmdTbaRglDto.class));
        return lst;
    }

    @Override
    public List<NmdTbaRglDto> getRglDataToChart() {
        List<NmdTbaRglDto> lst;
        String sql = "WITH COMPAREMONTH AS (SELECT MAX(THANGNT) AS THANG FROM VIEW_NGHIEM_THU_FINAL WHERE POWERSYSTEMTYPED = 'RGL' UNION ALL SELECT MAX(THANGKD) AS THANG FROM VIEW_KIEM_DINH_FINAL WHERE KIEU = 'RGL' UNION ALL SELECT DATEFROMPARTS(YEAR(MAX(EndDate)), MONTH(MAX(EndDate)), '01') AS THANG FROM View_Problem WHERE Type = 3), TwelveMonths AS (SELECT DISTINCT DATEADD(MONTH, - n, MaxDate) AS Thang FROM COMPAREMONTH CROSS APPLY (SELECT MAX(THANG) AS MaxDate FROM COMPAREMONTH) MaxDateInfo CROSS APPLY (VALUES (0),(1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11)) AS Numbers(n)), KiemDinhData AS (SELECT COALESCE(SUM(SOLUONG), 0) AS soLuongKD, TwelveMonths.Thang AS thang FROM TwelveMonths LEFT JOIN VIEW_KIEM_DINH_FINAL ON TwelveMonths.Thang = VIEW_KIEM_DINH_FINAL.THANGKD AND KIEU = 'RGL' GROUP BY TwelveMonths.Thang), NghiemThuData AS (SELECT COALESCE(SUM(AMOUNT), 0) AS soLuongNT, TwelveMonths.Thang AS thang FROM TwelveMonths LEFT JOIN VIEW_NGHIEM_THU_FINAL ON TwelveMonths.Thang = VIEW_NGHIEM_THU_FINAL.THANGNT AND POWERSYSTEMTYPED = 'RGL' GROUP BY TwelveMonths.Thang), XuLySuCoData AS (SELECT COALESCE(SUM(Count), 0) AS soLuongXLSC, TwelveMonths.Thang AS thang FROM TwelveMonths LEFT JOIN View_Problem ON TwelveMonths.Thang = DATEFROMPARTS(YEAR(View_Problem.EndDate), MONTH(View_Problem.EndDate), '01') AND Type = 3 GROUP BY TwelveMonths.Thang) SELECT KD.soLuongKD, NT.soLuongNT, XLSC.soLuongXLSC, FORMAT(KD.thang, 'MM-yyyy') AS thang FROM KiemDinhData KD JOIN NghiemThuData NT ON KD.thang = NT.thang JOIN XuLySuCoData XLSC ON KD.thang = XLSC.thang ORDER BY KD.THANG DESC";
        lst = jdbcTemplate.query(sql, new BeanPropertyRowMapper<NmdTbaRglDto>(NmdTbaRglDto.class));
        return lst;
    }

//-- Truy vấn trong getKdDataToChart, getNtDataToChart, getXlscDataToChart về cơ bản là giống nhau, dưới đây là truy vấn trong getKdDataToChart
//-- Tìm tháng lớn nhất trong VIEW_KIEM_DINH_FINAL
//WITH MAXMONTH AS (
//            SELECT MAX(THANGKD) AS THANG
//    FROM VIEW_KIEM_DINH_FINAL
//)
//
//        -- Lựa chọn dãy 12 tháng
//, TwelveMonths AS (
//            SELECT DISTINCT
//        DATEADD(MONTH, - n, THANG) AS thang
//    FROM MAXMONTH
//    CROSS APPLY (VALUES (0),(1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11)) AS Numbers(n)
//)
//
//        -- Tính tổng số lượng kiểm định theo từng tháng và từng loại nhà máy
//    SELECT
//    COALESCE(SUM(CASE WHEN KIEU = 'NM' THEN SOLUONG ELSE 0 END), 0) AS soLuongNM,
//    COALESCE(SUM(CASE WHEN KIEU = 'TBA' THEN SOLUONG ELSE 0 END), 0) AS soLuongTBA,
//    COALESCE(SUM(CASE WHEN KIEU = 'RGL' THEN SOLUONG ELSE 0 END), 0) AS soLuongRGL,
//    FORMAT(TwelveMonths.thang, 'MM-yyyy') AS thang
//    FROM TwelveMonths
//    LEFT JOIN VIEW_KIEM_DINH_FINAL ON TwelveMonths.thang = VIEW_KIEM_DINH_FINAL.THANGKD
//    GROUP BY TwelveMonths.thang
//    ORDER BY TwelveMonths.thang DESC;

    public List<KdNtXlscDto> getKdDataToChart() {
        List<KdNtXlscDto> lst;
        String sql = "WITH MAXMONTH AS (SELECT MAX(THANGKD) AS THANG FROM VIEW_KIEM_DINH_FINAL), TwelveMonths AS (SELECT DISTINCT DATEADD(MONTH, - n, THANG) AS thang FROM MAXMONTH CROSS APPLY (VALUES (0),(1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11)) AS Numbers(n)) SELECT COALESCE(SUM(CASE WHEN KIEU = 'NM' THEN SOLUONG ELSE 0 END), 0) AS soLuongNM, COALESCE(SUM(CASE WHEN KIEU = 'TBA' THEN SOLUONG ELSE 0 END), 0) AS soLuongTBA, COALESCE(SUM(CASE WHEN KIEU = 'RGL' THEN SOLUONG ELSE 0 END), 0) AS soLuongRGL, FORMAT(TwelveMonths.thang, 'MM-yyyy') AS thang FROM TwelveMonths LEFT JOIN VIEW_KIEM_DINH_FINAL ON TwelveMonths.thang = VIEW_KIEM_DINH_FINAL.THANGKD GROUP BY TwelveMonths.thang ORDER BY TwelveMonths.thang DESC";
        lst = jdbcTemplate.query(sql, new BeanPropertyRowMapper<KdNtXlscDto>(KdNtXlscDto.class));
        return lst;
    }

    public List<KdNtXlscDto> getNtDataToChart() {
        List<KdNtXlscDto> lst;
        String sql = "WITH MAXMONTH AS (SELECT MAX(THANGNT) AS THANG FROM VIEW_NGHIEM_THU_FINAL), TwelveMonths AS (SELECT DISTINCT DATEADD(MONTH, - n, THANG) AS Thang FROM MAXMONTH CROSS APPLY (VALUES (0),(1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11)) AS Numbers(n)) SELECT COALESCE(SUM(CASE WHEN POWERSYSTEMTYPED = 'NM' THEN AMOUNT ELSE 0 END), 0) AS SOLUONGNM, COALESCE(SUM(CASE WHEN POWERSYSTEMTYPED = 'TBA' THEN AMOUNT ELSE 0 END), 0) AS SOLUONGTBA, COALESCE(SUM(CASE WHEN POWERSYSTEMTYPED = 'RGL' THEN AMOUNT ELSE 0 END), 0) AS SOLUONGRGL, FORMAT(TwelveMonths.thang, 'MM-yyyy') AS thang FROM TwelveMonths LEFT JOIN VIEW_NGHIEM_THU_FINAL ON TwelveMonths.Thang = VIEW_NGHIEM_THU_FINAL.THANGNT GROUP BY TwelveMonths.Thang ORDER BY TwelveMonths.Thang DESC";
        lst = jdbcTemplate.query(sql, new BeanPropertyRowMapper<KdNtXlscDto>(KdNtXlscDto.class));
        return lst;
    }

    public List<KdNtXlscDto> getXlscDataToChart() {
        String sql;
        List<KdNtXlscDto> lst;
        sql = "WITH MAXMONTH AS (SELECT DATEFROMPARTS(YEAR(MAX(EndDate)), MONTH(MAX(EndDate)), '01') AS THANG FROM View_Problem), TwelveMonths AS (SELECT DISTINCT DATEADD(MONTH, - n, THANG) AS thang FROM MAXMONTH CROSS APPLY (VALUES (0),(1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11)) AS Numbers(n)) SELECT COALESCE(SUM(CASE WHEN Type = 1 THEN Count ELSE 0 END), 0) AS soLuongNM, COALESCE(SUM(CASE WHEN Type = 2 THEN Count ELSE 0 END), 0) AS soLuongTBA, COALESCE(SUM(CASE WHEN Type = 3 THEN Count ELSE 0 END), 0) AS soLuongRGL, FORMAT(TwelveMonths.thang, 'MM-yyyy') AS thang FROM TwelveMonths LEFT JOIN View_Problem ON TwelveMonths.thang = DATEFROMPARTS(YEAR(View_Problem.EndDate), MONTH(View_Problem.EndDate), '01') GROUP BY TwelveMonths.thang ORDER BY TwelveMonths.thang DESC";
        lst = jdbcTemplate.query(sql, new BeanPropertyRowMapper<KdNtXlscDto>(KdNtXlscDto.class));
        return lst;
    }
}
