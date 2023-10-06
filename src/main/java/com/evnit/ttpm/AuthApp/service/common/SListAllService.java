package com.evnit.ttpm.AuthApp.service.common;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.common.SListAll;
import com.evnit.ttpm.AuthApp.entity.common.SListGroupAll;
import com.evnit.ttpm.AuthApp.service.system.SystemCommonService;
import com.evnit.ttpm.AuthApp.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class SListAllService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    EntityManager entityManager;

    @Autowired
    SystemCommonService systemCommonService;

    // Hàm lấy các nhóm danh mục cha
    public ResponseData sListGroupGetLst(String sParentId) {
        ResponseData responseData = new ResponseData(ResponseData.STATE.OK.toString(), ResponseData.MESSAGE.SUCCESS.toString());
        String sql;
        List lst; //List<SListGroupAll> lst;
        //Truy vấn các trường cần thiết (không lấy tất cả trường để tối ưu dung lượng trả về
        if (Util.checkNullOrEmpty(sParentId)) {
            sql = "  select GROUPLISTID, GROUPLISTDESC, GROUPLIST_PARENT," +
                    "(select count(*) from S_LIST_GROUP_ALL S2 where S2.GROUPLIST_PARENT=S1.GROUPLISTID) CHILDCOUNT" +
                    " from S_LIST_GROUP_ALL S1 where GROUPLIST_PARENT is null\n" +
                    "  order by GROUPLISTORD, GROUPLISTDESC";
//            lst= jdbcTemplate.query(sql, new Object[]{},
//                    new BeanPropertyRowMapper(SListGroupAll.class));
            lst=jdbcTemplate.queryForList(sql);
        }
        else {
            sql = "  select GROUPLISTID, GROUPLISTDESC, GROUPLIST_PARENT," +
                    "(select count(*) from S_LIST_GROUP_ALL S2 where S2.GROUPLIST_PARENT=S1.GROUPLISTID) CHILDCOUNT" +
                    " from S_LIST_GROUP_ALL S1 where GROUPLIST_PARENT=?\n" +
                    "  order by GROUPLISTORD, GROUPLISTDESC";
//            lst= jdbcTemplate.query(sql, new Object[]{sParentId},
//                    new BeanPropertyRowMapper(SListGroupAll.class));
            lst=jdbcTemplate.queryForList(sql,sParentId);
        }
        responseData.setData(lst);
        return responseData;
    }

    // Hàm lấy các nhóm danh mục cha
    public ResponseData sListGroupGet1(String sGroupId) {
        ResponseData responseData = new ResponseData(ResponseData.STATE.OK.toString(), ResponseData.MESSAGE.SUCCESS.toString());
        String sql;
        sql = "  select * from S_LIST_GROUP_ALL where GROUPLISTID=?";
        List lst= jdbcTemplate.query(sql, new Object[]{sGroupId},
                new BeanPropertyRowMapper(SListGroupAll.class));
        if (lst==null || lst.isEmpty())
            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Không tìm thấy bản ghi");
        responseData.setData(lst.get(0));
        return responseData;
    }

    //Hàm thêm mới nhóm
    @Transactional
	public ResponseData sListGroupCreate(SListGroupAll lg) {
        ResponseData responseData = new ResponseData(ResponseData.STATE.OK.toString(), ResponseData.MESSAGE.SUCCESS.toString());
        //Check khóa
        String s;
        s="select 1 from S_LIST_GROUP_ALL where GROUPLISTID=?";
        List lst=jdbcTemplate.queryForList(s,lg.getGrouplistid());
        if (lst!=null && !lst.isEmpty())
            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Đã tồn tại bản ghi, không thể thêm mới");

        entityManager.persist(lg);
        responseData.setData(lg.getGrouplistid());
        return responseData;
    }

    //Hàm cập nhật nhóm
    @Transactional
    public ResponseData sListGroupUpdate(SListGroupAll lg) {
        ResponseData responseData = new ResponseData(ResponseData.STATE.OK.toString(), ResponseData.MESSAGE.SUCCESS.toString());
        //Check khóa
        String s;
        s="select 1 from S_LIST_GROUP_ALL where GROUPLISTID=?";
        List lst=jdbcTemplate.queryForList(s,lg.getGrouplistid());
        if (lst==null || lst.isEmpty())
            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Không tìm thấy bản ghi");

        entityManager.merge(lg);
        return responseData;
    }

    //Hàm xóa nhóm
    public ResponseData sListGroupDel(String sGroupId) {
        //Check khóa
        String s;
        s="delete from S_LIST_GROUP_ALL where GROUPLISTID=?";
        int i=jdbcTemplate.update(s,sGroupId);
        if (i<=0)
            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Không tìm thấy bản ghi");
        return new ResponseData(ResponseData.STATE.OK.toString(), "Xóa thành công");
    }

    // Hàm lấy các danh mục theo nhóm danh mục
    public ResponseData sListItemGetLst(String sGroupId) {
        ResponseData responseData = new ResponseData(ResponseData.STATE.OK.toString(), ResponseData.MESSAGE.SUCCESS.toString());
        String sql;
        //Truy vấn các trường cần thiết (không lấy tất cả trường để tối ưu dung lượng trả về
        sql = "  select LISTID, LISTDESC, GROUPLISTID, LISTORD ,LISTCODE from S_LIST_ALL where GROUPLISTID=?\n" +
                "  order by  LISTDESC";

        List<SListGroupAll> lst = jdbcTemplate.query(sql, new Object[]{sGroupId},
                new BeanPropertyRowMapper(SListAll.class));
        responseData.setData(lst);
        return responseData;
    }

    // Hàm lấy chi tiết danh mục
    public ResponseData sListItemGet1(String listid) {
        ResponseData responseData = new ResponseData(ResponseData.STATE.OK.toString(), ResponseData.MESSAGE.SUCCESS.toString());
        String sql;
        sql = "select * from S_LIST_ALL where LISTID=?";
        List lst= jdbcTemplate.query(sql, new Object[]{listid},
                new BeanPropertyRowMapper(SListAll.class));
        if (lst==null || lst.isEmpty())
            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Không tìm thấy bản ghi");
        responseData.setData(lst.get(0));
        return responseData;
    }

    //Hàm thêm mới danh mục
    @Transactional
    public ResponseData sListItemCreate(SListAll info) {
        ResponseData responseData = new ResponseData(ResponseData.STATE.OK.toString(), ResponseData.MESSAGE.SUCCESS.toString());
        //Check khóa
        String s;
        s="select 1 from S_LIST_ALL where LISTCODE=?  ";
        List lst=jdbcTemplate.queryForList(s,info.getListcode().trim());
        if (lst!=null && !lst.isEmpty())
            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Đã tồn tại bản ghi, không thể thêm mới");

        entityManager.persist(info);
        responseData.setData(info.getListid());
        return responseData;
    }

    //Hàm cập nhật danh mục
    @Transactional
    public ResponseData sListItemUpdate(SListAll info) {
        ResponseData responseData = new ResponseData(ResponseData.STATE.OK.toString(), ResponseData.MESSAGE.SUCCESS.toString());
        //Check khóa
        
        
        
        String s;
        s="select 1 from S_LIST_ALL where LISTCODE=?   ";
        List lst=jdbcTemplate.queryForList(s,info.getListcode().trim());
        if (lst!=null && !lst.isEmpty())
            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Cập nhật danh mục xảy ra lỗi, Đã tồn tại bản ghi, không thể cập nhật");
        entityManager.merge(info);
        return responseData;
    }

    //Hàm xóa danh mục
    public ResponseData sListItemDel(String listid) {
        //Check khóa
        String s1;
        s1="select 1 from VIEW_CONGTO where (CCX_Wh=? or CCX_Varh=? or U=? or HSN=? or KIEU_CTO=? or LOAI_CTO=? or TSB_TU=? or TSB_TI=?) and ISDELETE=?  ";
        List lst1=jdbcTemplate.queryForList(s1,listid,listid,listid,listid,listid,listid,listid,listid,0);
        if (lst1!=null && !lst1.isEmpty())
            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Đã có dữ liệu ràng buộc, không thể xóa");

        String s2;
        s2="select 1 from S_CATEGORY_TBA_RGL where VOLTAGE_LEVEL=? and IS_DELETE=?  ";
        List lst2=jdbcTemplate.queryForList(s2,listid,0);
        if (lst2!=null && !lst2.isEmpty())
            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Đã có dữ liệu ràng buộc, không thể xóa");

        String s3;
        s3="select 1 from View_DiemDo where (XNK=? or TINH_CHAT1=? or TINH_CHAT2=?) and ISDELETE=?  ";
        List lst3=jdbcTemplate.queryForList(s3,listid,listid,listid,0);
        if (lst3!=null && !lst3.isEmpty())
            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Đã có dữ liệu ràng buộc, không thể xóa");

        String s4;
        s4="select 1 from M_DEAL where TYPE_DEAL=? and ISDELETE=?  ";
        List lst4=jdbcTemplate.queryForList(s4,listid,0);
        if (lst4!=null && !lst4.isEmpty())
            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Đã có dữ liệu ràng buộc, không thể xóa");

        String s5;
        s5="select 1 from A_ASSET where P_MANUFACTURERID=? and NATIONALFACT=? and ISDELETE=?  ";
        List lst5=jdbcTemplate.queryForList(s5,listid,listid,0);
        if (lst5!=null && !lst5.isEmpty())
            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Đã có dữ liệu ràng buộc, không thể xóa");

        String s;
        s="delete from S_LIST_ALL where LISTID=?";
        int i=jdbcTemplate.update(s,listid);
        if (i<=0)
            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Không tìm thấy bản ghi");
        return new ResponseData(ResponseData.STATE.OK.toString(), "Xóa thành công");
    }

}