package com.evnit.ttpm.AuthApp.service.system;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.common.ViewHisMountUnDevice;
import com.evnit.ttpm.AuthApp.entity.system.M_WORDER_HIS;
import com.evnit.ttpm.AuthApp.model.common.SearchHisMountUnDto;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
import com.evnit.ttpm.AuthApp.repository.common.ViewHisMountUnDeviceRepo;
import com.evnit.ttpm.AuthApp.util.SpecificationUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WonderHisServiceImpl implements WonderHisService {

    @Autowired
    WonderHisRepoService _wonderHisService;

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ViewHisMountUnDeviceRepo viewHisMountUnDeviceRepo;

    @Override
    public ResponseData GetList(String worderId, String type) {
        ResponseData response = new ResponseData();

        String sql;
        List lst;

        sql = "SELECT [ID] as id ,[WORDERID] as 'worderId' ,[W_CONTENT] as 'content' ,[W_CONTENT_EDIT] as 'contentEdit', [VIEW_CONTENT] as 'viewContent',CASE WHEN [W_MANIPULATION] = 'INS' THEN 'Thêm' WHEN [W_MANIPULATION] = 'UPDATE' THEN N'Sửa' WHEN [W_MANIPULATION] = 'DELETE' THEN 'Xóa' ELSE '' END AS 'action' ,[DATE_MANIPULATION] as 'date' ,[USER_MDF_ID] as 'user' ,[TYPEWORDER] as 'type' FROM [dbo].[M_WORDER_HIS] WHERE [TYPEWORDER] = '" + type + "' and WORDERID = '" + worderId + "' ORDER BY date desc";
        lst = jdbcTemplate.queryForList(sql);
        response.setState(ResponseData.STATE.OK.toString());
        response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        response.setData(lst);
        return response;
    }

    @Override
    public ResponseData GetListEvent(String Id, Integer eventType) {
        ResponseData response = new ResponseData();

        String sql;
        List lst;

        sql = "EXEC [dbo].[GET_EVENT_TB] @Id = N'" + Id + "', @EventType = " + eventType + "";
        lst = jdbcTemplate.queryForList(sql);
        response.setState(ResponseData.STATE.OK.toString());
        response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        response.setData(lst);
        return response;
    }

    @Override
    public ResponseData GetListMountUn(SearchHisMountUnDto dto) {
        ResponseData res = new ResponseData();
        res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        res.setState(ResponseData.STATE.OK.toString());
        try {
            SearchQuery searchQuery = new SearchQuery();
            List<SearchFilter> searchFilters = new ArrayList<>();
            if (dto.getCategoryType() != null && !dto.getCategoryType().isEmpty()) {
                searchFilters.add(new SearchFilter("categoryType", "=", dto.getCategoryType()));
            }
            if (dto.getIdDevice() != null && !dto.getIdDevice().isEmpty()) {
                searchFilters.add(new SearchFilter("idDevice", "=", dto.getIdDevice()));
            }
            if (dto.getIdDiemDo() != null && !dto.getIdDiemDo().isEmpty()) {
                searchFilters.add(new SearchFilter("idDiemDo", "=", dto.getIdDiemDo()));
            }
            var limit = Integer.MAX_VALUE;
            Pageable pageable = PageRequest.of(dto.getPage(), limit, dto.getSortExpression());
            searchQuery.setSearchFilters(searchFilters);
            Specification<ViewHisMountUnDevice> spec = SpecificationUtil.bySearchQuery(searchQuery, ViewHisMountUnDevice.class);
            var pageResult = viewHisMountUnDeviceRepo.findAll(spec, pageable);
            var content = pageResult.getContent();
            var listData = new ArrayList<ViewHisMountUnDevice>(content);
            listData.sort(Comparator
                    .comparing(ViewHisMountUnDevice::getNgayGan, Comparator.nullsLast(Comparator.naturalOrder()))
                    .thenComparing(ViewHisMountUnDevice::getNgayGo, Comparator.nullsLast(Comparator.naturalOrder())));

            List<ViewHisMountUnDevice> listRs = new ArrayList<>();

            var firstItem = listData.get(0);
            listRs.add(firstItem);
            var length = listData.size();
            for (int i = 1; i < length; i++) {
                var item = listData.get(i);
                var itemPre = listData.get(i - 1);
                if (!item.getTypeAccept().equals("NTMTLD") && !item.getTypeAccept().equals("HDD")) {
                    if (item.getAssetDesc() != null && !itemPre.getAssetDesc().equals(item.getAssetDesc()) && itemPre.getNgayGan() != null) {
                        listRs.add(item);
                    } else if (itemPre.getNgayGan() == null && item.getNgayGo() != null) {
                        listRs.add(item);
                    }
                }
            }
            listRs.sort(Comparator
                    .comparing(ViewHisMountUnDevice::getNgayGan, Comparator.nullsLast(Comparator.naturalOrder())).reversed()
                    .thenComparing(ViewHisMountUnDevice::getNgayGo, Comparator.nullsLast(Comparator.naturalOrder())).reversed());

            Page<ViewHisMountUnDevice> result = new PageImpl<ViewHisMountUnDevice>(listRs, pageable, pageResult.getTotalElements());
            res.setData(result);
        } catch (Exception ex) {
            res.setMessage(ResponseData.MESSAGE.ERROR.toString());
            res.setState(ResponseData.STATE.FAIL.toString());
        }
        return res;
    }


    @Override
    public Boolean CreateHis(String worderid, Object obj, String userId, String Type, String manipulation) {

        try {

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(obj);
            String json1 = ow.writeValueAsString(obj);
//            String result = compareAndReturnJson(json, json1);


            var createDate = new Date();
            M_WORDER_HIS his = new M_WORDER_HIS();
            his.setWORDERID(worderid);
            his.setW_CONTENT_EDIT(json);
            his.setW_MANIPULATION(manipulation);
            his.setDATE_MANIPULATION(createDate);
            his.setVIEW_CONTENT(json);
            his.setUSER_MDF_ID(userId);
            his.setTYPEWORDER(Type);
            var assetEntity = _wonderHisService.createMWorderHis(his);

            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    @Override
    public Boolean UpdateHis(String worderid, Object obj, Object obj1, List<String> ignoredFields,Map<String, String> variableNameMapping, String userId, String Type, String manipulation) {

        try {

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(obj).toLowerCase(Locale.ROOT);
            String json1 = ow.writeValueAsString(obj1).toLowerCase(Locale.ROOT);

            String result = compareAndReturnJson(json1, json,ignoredFields,variableNameMapping);

            var createDate = new Date();
            M_WORDER_HIS his = new M_WORDER_HIS();
            his.setWORDERID(worderid);
            his.setW_CONTENT_EDIT(json);
            his.setW_MANIPULATION(manipulation);
            his.setDATE_MANIPULATION(createDate);
            his.setVIEW_CONTENT(result);
            his.setUSER_MDF_ID(userId);
            his.setTYPEWORDER(Type);
            var assetEntity = _wonderHisService.createMWorderHis(his);

            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
    @Override
    public Boolean UpdateHisTUTI(String worderid, Object obj, Object obj1,Object obj2,Object obj3, List<String> ignoredFields,Map<String, String> variableNameMapping, String userId, String Type, String manipulation) {

        try {

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(obj).toLowerCase(Locale.ROOT);
            String json1 = ow.writeValueAsString(obj1).toLowerCase(Locale.ROOT);
            String json2 = ow.writeValueAsString(obj2).toLowerCase(Locale.ROOT);
            String json3 = ow.writeValueAsString(obj3).toLowerCase(Locale.ROOT);

            String result = compareAndReturnJson(json1, json,ignoredFields,variableNameMapping);
            String result1 = compareAndReturnJson(json3, json2,ignoredFields,variableNameMapping);

            String resultFinal = String.join(" ",result,result1);

            var createDate = new Date();
            M_WORDER_HIS his = new M_WORDER_HIS();
            his.setWORDERID(worderid);
            his.setW_CONTENT_EDIT(json);
            his.setW_MANIPULATION(manipulation);
            his.setDATE_MANIPULATION(createDate);
            his.setVIEW_CONTENT(resultFinal);
            his.setUSER_MDF_ID(userId);
            his.setTYPEWORDER(Type);
            var assetEntity = _wonderHisService.createMWorderHis(his);

            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
    public static String compareAndReturnJson(String json1, String json2,List<String> ignoredFields,Map<String,String> variableNameMapping) {
        try {
            // Create ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Parse JSON strings into JsonNode objects
            JsonNode node1 = objectMapper.readTree(json1);
            JsonNode node2 = objectMapper.readTree(json2);

            // Compare JsonNodes and accumulate changes
            StringBuilder changesString = new StringBuilder();
            findChanges(node1, node2, changesString, "",ignoredFields,variableNameMapping);

            // Return the JSON representation of the changes in the second input
            return changesString.toString();
        } catch (Exception e) {
            return "Error comparing JSON objects: " + e.getMessage();
        }
    }

    private static void findChanges(JsonNode node1, JsonNode node2, StringBuilder changesString, String path,List<String> ignoreFields,Map<String,String> variableNameMapping) {
        // Check fields in node1
//        Iterator<Map.Entry<String, JsonNode>> fields1 = node1.fields();
//        while (fields1.hasNext()) {
//            Map.Entry<String, JsonNode> entry = fields1.next();
//            String fieldName = entry.getKey();
//            JsonNode value1 = entry.getValue();
//            if(!ignoreFields.contains(fieldName)) {
//                String currentPath = path + "." + fieldName;
//                if (!node2.has(fieldName)) {
//                    // Field was removed
//                    changesNode.put(currentPath, value1.asText());
//                } else {
//                    JsonNode value2 = node2.get(fieldName);
//                    if (!value1.equals(value2)) {
//                        if (value1.isObject() && value2.isObject()) {
//                            // Recursively compare objects
//                            ObjectNode subChangesNode = changesNode.putObject(fieldName);
//                            findChanges(value1, value2, subChangesNode, currentPath, ignoreFields);
//                        } else {
//                            // Field was changed
//                            changesNode.put(currentPath, value2);
//                        }
//                    }
//                }
//            }
//        }



        Iterator<Map.Entry<String, JsonNode>> fields1 = node1.fields();
        while (fields1.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields1.next();
            String fieldName = entry.getKey();
            JsonNode value1 = entry.getValue();
            if(!ignoreFields.contains(fieldName)) {
                // Use the custom variable name if available, otherwise use the original name
                String customName = variableNameMapping.getOrDefault(fieldName, fieldName);
                String currentPath = path + "" + customName;

                if (!node2.has(fieldName)) {
                    // Field was removed xóa
                    changesString.append(" ").append(currentPath).append(" (").append(value1.asText()).append(");\n");
                } else {
                    JsonNode value2 = node2.get(fieldName);
                    if (!value1.equals(value2)) {
                        if (value1.isObject() && value2.isObject()) {
                            // Recursively compare objects
                            findChanges(value1, value2, changesString, currentPath);
                        } else {
                            // Field was changed sửa
                            changesString.append(" ").append(currentPath)
                                    .append(" (").append(value1.asText()).append(" -> ").append(value2.asText()).append(");\n");
                        }
                    }
                }
            }


        }

        // Check fields in node2

        Iterator<Map.Entry<String, JsonNode>> fields2 = node2.fields();
        while (fields2.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields2.next();
            String fieldName = entry.getKey();
            if(!ignoreFields.contains(fieldName)){
                // Use the custom variable name if available, otherwise use the original name
                String customName = variableNameMapping.getOrDefault(fieldName, fieldName);
                String currentPath = path + "" + customName;

                if (!node1.has(fieldName)) {
                    // Field was added thêm
                    changesString.append(" ").append(currentPath).append(" (").append(entry.getValue().asText()).append(");\n");
                }
            }
        }
    }



        public static String compareAndReturnString(String json1, String json2) {
            try {
                // Create ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();

                // Parse JSON strings into JsonNode objects
                JsonNode node1 = objectMapper.readTree(json1);
                JsonNode node2 = objectMapper.readTree(json2);

                // Compare JsonNodes and accumulate changes as a string
                StringBuilder changesString = new StringBuilder();
                findChanges(node1, node2, changesString, "");

                // Return the string representation of the changes in the second input
                return changesString.toString();
            } catch (Exception e) {
                return "Error comparing JSON objects: " + e.getMessage();
            }
        }

        private static void findChanges(JsonNode node1, JsonNode node2, StringBuilder changesString, String path) {
            // Mapping of variable names to UTF-8 names


            // Check fields in node1

        }



}
