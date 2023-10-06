package com.evnit.ttpm.AuthApp.service.lookup;


import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.mapper.LookupMapper;
import com.evnit.ttpm.AuthApp.model.request.common.SelectItem;
import com.evnit.ttpm.AuthApp.model.request.common.TreeSelect;
import com.evnit.ttpm.AuthApp.repository.accreditation.ViewAccreditationDeviceRepo;
import com.evnit.ttpm.AuthApp.repository.category.*;
import com.evnit.ttpm.AuthApp.repository.common.ALstTypeRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class LookupServiceImpl implements LookupService {
    @Autowired
    DonViSHRepository donViSHRepository;
    @Autowired
    TinhTPRepository tinhTPRepository;
    @Autowired
    TbaRglRepository tbaRepository;
    @Autowired
    DeliveryUnitRepository deliveryUnitRepository;
    @Autowired
    NhaMayDienRepository nhaMayDienRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ALstTypeRepository aLstTypeRepository;
    private final ModelMapper mapper;
    private final ViewAccreditationDeviceRepo viewAccreditationDeviceRepo;
    public LookupServiceImpl(LookupMapper mapperConfig, ViewAccreditationDeviceRepo viewAccreditationDeviceRepo) {
        this.mapper = mapperConfig.getModelMapper();
        this.viewAccreditationDeviceRepo = viewAccreditationDeviceRepo;
    }

    @Override
    public ResponseData getCompaniesTree() {
        ResponseData response = new ResponseData();
        try {
            var treeSelects = donViSHRepository.findAllByIsDeleteFalse().stream().map(company -> mapper.map(company, TreeSelect.class)).collect(Collectors.toList());
            List<TreeSelect> tree = new ArrayList<>();
            var listTree = buildTree1(treeSelects, tree, 0, 0, 0);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(listTree);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ex.getMessage().toString());
            response.setData(null);
        }
        return response;
    }


    @Override
    public ResponseData getCompaniesTreeLevel12() {
        ResponseData response = new ResponseData();
        try {
            var treeSelects = donViSHRepository.findAllByIsDeleteFalseType12().stream().map(company -> mapper.map(company, TreeSelect.class)).collect(Collectors.toList());
            var listTree = buildTree(treeSelects, null, null);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(listTree);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ex.getMessage().toString());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData getCompanies() {
        ResponseData response = new ResponseData();
        try {
            var companyList = donViSHRepository.findAllByIsDeleteFalse().stream().map(company -> mapper.map(company, SelectItem.class)).collect(Collectors.toList());
            response.setData(companyList);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ex.getMessage().toString());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData getCity() {
        ResponseData response = new ResponseData();
        try {
            String sql;
            List lst; //List<SListGroupAll> lst;
            sql = "Select a.ID as value , a.DOMAIN as domain ,a.NAME  as text , Case when a.DOMAIN = 0 then N'Miền bắc'when a.DOMAIN = 1 then N'Miền trung'  when a.DOMAIN =2 then N'Miền nam'end as domainStr from S_PROVINCES a  where a.ISDELETE = 0 order by a.NAME";
            lst=jdbcTemplate.queryForList(sql);
            var selectList = tinhTPRepository.findAllByIsDelete().stream().map(city -> mapper.map(city, SelectItem.class)).collect(Collectors.toList());
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(lst);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData getTBA() {
        ResponseData response = new ResponseData();
        try {
            var selectList = tbaRepository.findAll().stream().filter(x -> !x.getIsDelete() && x.getTbaRglStatus() != 5 ).map(city -> mapper.map(city, SelectItem.class)).collect(Collectors.toList());
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(selectList);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }
    @Override
    public ResponseData getUser() {
        ResponseData response = new ResponseData();
        try {
            String sql;
            List selectList; //List<SListGroupAll> lst;
            sql = " Select a.USERID as id, a.USERID  as value, a.USERNAME as text from Q_USER a Where a.ENABLE =1" ;
            selectList = jdbcTemplate.queryForList(sql);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(selectList);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }
    @Override
    public ResponseData getRole(String UserId) {
        ResponseData response = new ResponseData();
        try {



            String sql1;
            List selectList1; //List<SListGroupAll> lst;

            sql1 = "Select a.Id as id ,a.Name as Name, a.Type  as Type, a.ISQL as isql, a.isLD as isld ,a.Status as Status ,a.UserId as userid from ViewRole a Where a.UserId =?" ;
            selectList1 = jdbcTemplate.queryForList(sql1,UserId);

            String sql;
            List selectList; //List<SListGroupAll> lst;
            if(selectList1==null || selectList1.isEmpty()){
                sql = " Select a.Id as id,a.Name as Name, a.Type  as Type, a.ISQL as isql, a.isLD as isld ,a.Status as Status ,a.UserId as userid from ViewRoleStatus a  order by a.Type,a.Name" ;
                selectList = jdbcTemplate.queryForList(sql);

            }
            else{
                sql = " Select a.Id as id ,a.Name as Name, a.Type  as Type, a.ISQL as isql, a.isLD as isld ,a.Status as Status ,a.UserId as userid from ViewRole a Where a.UserId =? or a.UserId is null order by a.Type,a.Name" ;
                selectList = jdbcTemplate.queryForList(sql,UserId);

            }
//            sql = " Select a.Id as id,a.Name as Name, a.Type  as Type, a.ISQL as isql, a.isLD as isld ,a.Status as Status ,a.UserId as userid from ViewRoleStatus a  order by a.Type,a.Name" ;
//            selectList = jdbcTemplate.queryForList(sql);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(selectList);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }
    @Override
    public ResponseData getElectricFactory() {
        ResponseData response = new ResponseData();
        try {
            var selectList = nhaMayDienRepository.findAll().stream().filter(x -> !x.getIs_Delete() && x.getStatus() != 5)
                    .map(fact -> mapper.map(fact, SelectItem.class)).collect(Collectors.toList());
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(selectList);
        } catch (Exception ignored) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString() + ignored.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData getTC() {
        ResponseData response = new ResponseData();
        try {

//        	 String sql;
//             List lst; //List<SListGroupAll> lst;
//             //Truy vấn các trường cần thiết (không lấy tất cả trường để tối ưu dung lượng trả về
//             if (Util.checkNullOrEmpty(sParentId)) {
//                 sql = "  select GROUPLISTID, GROUPLISTDESC, GROUPLIST_PARENT," +
//                         "(select count(*) from S_LIST_GROUP_ALL S2 where S2.GROUPLIST_PARENT=S1.GROUPLISTID) CHILDCOUNT" +
//                         " from S_LIST_GROUP_ALL S1 where GROUPLIST_PARENT is null\n" +
//                         "  order by GROUPLISTORD, GROUPLISTDESC";
////                 lst= jdbcTemplate.query(sql, new Object[]{},
////                         new BeanPropertyRowMapper(SListGroupAll.class));
//                 lst=jdbcTemplate.queryForList(sql);
//             }
//             else {
//                 sql = "  select GROUPLISTID, GROUPLISTDESC, GROUPLIST_PARENT," +
//                         "(select count(*) from S_LIST_GROUP_ALL S2 where S2.GROUPLIST_PARENT=S1.GROUPLISTID) CHILDCOUNT" +
//                         " from S_LIST_GROUP_ALL S1 where GROUPLIST_PARENT=?\n" +
//                         "  order by GROUPLISTORD, GROUPLISTDESC";
////                 lst= jdbcTemplate.query(sql, new Object[]{sParentId},
////                         new BeanPropertyRowMapper(SListGroupAll.class));
//                 lst=jdbcTemplate.queryForList(sql,sParentId);
//             }
//             responseData.setData(lst);


//            var selectList = .findAll().stream().map(city -> mapper.map(city, SelectItem.class)).collect(Collectors.toList());
            String sql;
            List lst; //List<SListGroupAll> lst;
            sql = " Select a.LISTCODE as id, a.LISTDESC as value, a.LISTDESC as text from S_LIST_ALL a\r\n"
                    + "where a.GROUPLISTID = 'TCDD'";
            lst = jdbcTemplate.queryForList(sql);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(lst);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData getTT() {
        ResponseData response = new ResponseData();
        try {
            String sql;
            List lst; //List<SListGroupAll> lst;
            sql = "Select a.LISTCODE as id, a.LISTCODE as value, a.LISTDESC as text from S_LIST_ALL a where a.GROUPLISTID = 'LTT' order by a.LISTCODE ";
            lst = jdbcTemplate.queryForList(sql);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(lst);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }
    @Override
    public ResponseData getEvent(String TbaId,String DiemDoId) {
        ResponseData response = new ResponseData();
        try {
            String sql;
            List lst;
            sql = "SELECT EventId as id, EventId as value, Event as text FROM View_PTTSM where TbaId =? and DiemDoId= ? ";
            lst = jdbcTemplate.queryForList(sql,TbaId,DiemDoId);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(lst);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData getDateAnalysis(String TbaId,String DiemDoId,String EventId) {
        ResponseData response = new ResponseData();
        try {
            String sql;
            List lst;
            sql = "SELECT DateEvent as id, DateEvent as value, DateEvent as text FROM View_PTTSM where TbaId =? and DiemDoId= ? and EventId= ? ";
            lst = jdbcTemplate.queryForList(sql,TbaId,DiemDoId,EventId);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(lst);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }
    @Override
    public ResponseData getPttsm(String TbaId, String DiemDoId, String EventId, String Time) {
        ResponseData response = new ResponseData();
        try {
            String sql;
            List lst;
            sql = "SELECT Ia,Ib,Ic,Ua,Ub,Uc,Phia,Phib,Phic FROM View_PTTSM where TbaId =? and DiemDoId= ? and EventId= ? and DateEvent =? ";
            lst = jdbcTemplate.queryForList(sql,TbaId,DiemDoId,EventId,Time);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(lst);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }
    @Override
    public ResponseData getLHNMD() {
        ResponseData response = new ResponseData();
        try {
            String sql;
            List lst; //List<SListGroupAll> lst;
            sql = "Select a.LISTCODE as id, a.LISTCODE as value, a.LISTDESC as text from S_LIST_ALL a where a.GROUPLISTID = 'LHNMD' order by a.LISTCODE ";
            lst = jdbcTemplate.queryForList(sql);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(lst);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData getXNK() {
        ResponseData response = new ResponseData();
        try {
            String sql;
            List lst; //List<SListGroupAll> lst;
            sql = "Select a.LISTCODE as id, a.LISTCODE as value, a.LISTDESC as text from S_LIST_ALL a where a.GROUPLISTID = 'DTXNK' order by a.LISTCODE ";
            lst = jdbcTemplate.queryForList(sql);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(lst);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }

    public ResponseData getDTXNK() {
        ResponseData response = new ResponseData();
        try {
            String sql;
            List lst; //List<SListGroupAll> lst;
            sql = "  Select a.ID as id , a.TBA_RGL_NAME as text ,a.ID  as value  from S_CATEGORY_TBA_RGL a  where a.IS_DELETE = 0 and a.TBA_RGL_XNK = 1";
            lst = jdbcTemplate.queryForList(sql);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(lst);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData getListType(String typeParent) {
        ResponseData response = new ResponseData();
        try {
            var selectList = aLstTypeRepository.findByTypeIdParentAndIsVisibleIsFalse(typeParent).stream()
                    .map(type -> mapper.map(type, SelectItem.class)).collect(Collectors.toList());
            response.setData(selectList);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ignored) {
            response.setMessage(ResponseData.MESSAGE.ERROR.toString() + ignored.getMessage());
            response.setState(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }
    @Override
    public ResponseData getDeliveryUnitDD() {
        ResponseData response = new ResponseData();
        try {
            // var selectList = deliveryUnitRepository.findAll().stream().filter(x -> x.getIs_Delete() == false).map(city -> mapper.map(city, SelectItem.class)).collect(Collectors.toList());

            String sql;
            List selectList; //List<SListGroupAll> lst;
            sql = "Select a.ID as id , a.NAME as text ,Cast (a.ID as nvarchar(36)) as value  from S_Delivery_Unit a  where a.IS_DELETE = 0 order by [NAME] asc";
            selectList = jdbcTemplate.queryForList(sql);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(selectList);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }
    @Override
    public ResponseData getDeliveryUnit() {
        ResponseData response = new ResponseData();
        try {
          // var selectList = deliveryUnitRepository.findAll().stream().filter(x -> x.getIs_Delete() == false).map(city -> mapper.map(city, SelectItem.class)).collect(Collectors.toList());

            String sql;
            List selectList; //List<SListGroupAll> lst;
            sql = "Select a.ID as id , a.NAME as text ,a.ID  as value  from S_Delivery_Unit a  where a.IS_DELETE = 0 order by [NAME] asc";
            selectList = jdbcTemplate.queryForList(sql);



            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(selectList);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData getListAccreditationByAssetId(String assetId) {
        ResponseData response = new ResponseData();
        try {
            var selectList = viewAccreditationDeviceRepo.findByAssetIdDevice(assetId).stream().map(accred -> mapper.map(accred,SelectItem.class)).collect(Collectors.toList());
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(selectList);
        }catch (Exception ex){
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }

    private List<TreeSelect> buildTree(List<TreeSelect> allSelects, Integer rootSelect, Boolean showOnlyChild) {
        List<TreeSelect> tree = new ArrayList<TreeSelect>();
        List<TreeSelect> roots = new ArrayList<TreeSelect>();

        if (showOnlyChild == null || !showOnlyChild) {
            if (rootSelect == null) {
                roots = allSelects.stream().filter(x -> x.getParentId() == null || x.getParentId() == 0).collect(Collectors.toList());
            } else {
                TreeSelect rootNode = allSelects.stream().filter(x -> x.getId() == rootSelect).findFirst().orElse(null);
                if (rootNode != null) {
                    roots.add(rootNode);
                }
            }
        } else {
            if (rootSelect == null) {
                roots = allSelects.stream().filter(x -> x.getParentId() == null || x.getParentId() == 0).collect(Collectors.toList());
            } else {
                roots = allSelects.stream().filter(x -> x.getParentId() == rootSelect).collect(Collectors.toList());
            }
        }
        for (TreeSelect r : roots) {
            tree.add(r);
            TreeSelect nextNode = r;
            TreeSelect parentNode;
            int index;
            int level = 1;
            TreeSelect currentNode;
            while (nextNode != null) {
                currentNode = nextNode;
                nextNode = null;
                int currNodeId = currentNode.getId();
                final TreeSelect nodeCheck = currentNode;
                List<TreeSelect> childList = allSelects.stream().filter(x -> x.getParentId() == currNodeId).collect(Collectors.toList());
                currentNode.setChildren(childList);
                currentNode.set_children(childList);
                if (currentNode.getChildren() != null && !currentNode.getChildren().isEmpty()) {
                    nextNode = currentNode.getChildren().get(0);
                    level++;
                } else {
                    while (currentNode.getParent() != null) {
                        parentNode = currentNode.getParent();
                        index = parentNode.getChildren().indexOf(currentNode);
                        if (index < parentNode.getChildren().size() - 1) {
                            nextNode = parentNode.getChildren().get(index + 1);
                            break;
                        } else {
                            currentNode = parentNode;
                            level--;
                        }
                    }
                }
            }
        }
        return tree;
    }

    private List<TreeSelect> buildTree1(List<TreeSelect> allTrees, List<TreeSelect> tree, Integer compId, Integer parentId, int index) {
        List<TreeSelect> listChild = new ArrayList<>();
        if (parentId != 0 && index == 0) {
            TreeSelect item = allTrees.stream().filter(x -> Objects.equals(x.getId(), compId)).findFirst().orElse(new TreeSelect());
            listChild.add(item);
        } else {
            listChild = allTrees.stream().filter(x -> Objects.equals(x.getParentId(), parentId)).collect(Collectors.toList());
        }
        for (TreeSelect item : listChild) {
            index++;
            List<TreeSelect> childNode = new ArrayList<>();
            childNode = buildTree1(allTrees, childNode, 0, item.getId(), index);
            if (childNode.size() > 0) {
                List<TreeSelect> child = new ArrayList<>(childNode);
                item.setChildren(child);
                item.set_children(child);
            }
            tree.add(item);
        }
        return tree;
    }
}

