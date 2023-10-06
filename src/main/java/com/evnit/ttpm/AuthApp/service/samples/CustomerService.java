package com.evnit.ttpm.AuthApp.service.samples;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.samples.Customer;
import com.evnit.ttpm.AuthApp.model.system.S_Key_ControlInfo;
import com.evnit.ttpm.AuthApp.repository.samples.CustomerRepository;
import com.evnit.ttpm.AuthApp.service.system.SystemCommonService;
import com.evnit.ttpm.AuthApp.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//Tham khảo:
//https://www.twilio.com/blog/java-custom-queries-jdbctemplate-springboot

@Service
public class CustomerService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    CustomerRepository cusRepos;

    @Autowired
    EntityManager entityManager;

    @Autowired
    SystemCommonService systemCommonService;

    public ResponseData getCustomerById(String cusId) {
        ResponseData responseData = new ResponseData(ResponseData.STATE.OK.toString(),ResponseData.MESSAGE.SUCCESS.toString());
        responseData.setData(cusRepos.getById(cusId));
        return responseData;
    }

    public ResponseData getLstAllCustomer() {
        ResponseData responseData = new ResponseData(ResponseData.STATE.OK.toString(),ResponseData.MESSAGE.SUCCESS.toString());
        responseData.setData(cusRepos.getALL());
        return responseData;
    }

    public ResponseData getLst2Customer(String name) {
        String sql="select * from Customer where name=?";
        //String sql="select CUSTOMERID, NAME from Customer where name=?"; //test chỉ lấy 2 trường: cách 1 bị lỗi, cách 2: Ok
        //Cách 1: => Trả ra entity tên trường như query
//        Object lst=jdbcTemplate.queryForList(sql, name);

        //Cách 2: bind vào entity => Trả ra entity như trong class
        List<Customer> lst=jdbcTemplate.query(sql, new Object[] { name },
                new BeanPropertyRowMapper(Customer.class));

        //Trả ra kq
        ResponseData responseData = new ResponseData(ResponseData.STATE.OK.toString(),ResponseData.MESSAGE.SUCCESS.toString());
        responseData.setData(lst);
        return responseData;
    }

    public ResponseData getLst3Customer(String name) {
        String sql="select CUSTOMERID, NAME from Customer where name=?1";//"select * from Customer where name=?1";
        Query qr=entityManager.createNativeQuery(sql, Customer.class);
        qr.setParameter(1,name);
        List<Customer> lst=qr.getResultList();
        ResponseData responseData = new ResponseData(ResponseData.STATE.OK.toString(),ResponseData.MESSAGE.SUCCESS.toString());
        responseData.setData(lst);
        return responseData;
    }

    //Ví dụng sử dụng truy vấn qua tham số đặt tên
    public ResponseData getLst4Customer(String name) {
        String sql="select CUSTOMERID, NAME from Customer where name=:name";//"select * from Customer where name=?1";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name",name);
        List lst=namedJdbcTemplate.queryForList(sql,params);
        //hoặc các này bind vào entity đều được
        //lst=namedJdbcTemplate.query(sql,params,new BeanPropertyRowMapper(Customer.class));
        //nếu câu lệnh update, delete thì dùng int i=namedJdbcTemplate.update(s, params);

        ResponseData responseData = new ResponseData(ResponseData.STATE.OK.toString(),ResponseData.MESSAGE.SUCCESS.toString());
        responseData.setData(lst);
        return responseData;
    }

    public ResponseData create(Customer cs, String userid) {
        // GenKeyID
        S_Key_ControlInfo keyControlInfo = new S_Key_ControlInfo(null, Customer.class.getSimpleName());
        keyControlInfo = systemCommonService.getGenKeyID(keyControlInfo);
        ResponseData responseData;
        if (keyControlInfo == null || keyControlInfo.getGenStatus() != 0) // Không thành công
            return new ResponseData(ResponseData.STATE.FAIL.toString(),"Sinh mã không thành công!");
           //return new ResponseData(ResponseData.STATE.FAIL.toString(),Util.showErrorFromGenKey(keyControlInfo)); //Lỗi ký tự
        String id = keyControlInfo.getOutputValue();
        Optional<Customer> optional = cusRepos.findById(id);
        if (optional.isPresent()) {
            return new ResponseData(ResponseData.STATE.FAIL.toString(),"Mã đã tồn tại");
        }
        if (Util.checkIDStandard(id) == false) {
            return new ResponseData(ResponseData.STATE.FAIL.toString(),"ID có ký tự đặc biệt");
        }

        cs.setCustomerid(id);
        cs.setCrdtime(new Date());
        cs.setUsercrid(userid);
        cs.setUserudid(null);
        cs.setUddtime(null);

        cusRepos.save(cs);

        responseData=new ResponseData(ResponseData.STATE.OK.toString(),"Thêm mới thành công!");
        responseData.setData(id); //Trả ra mã bản ghi
        return responseData;
    }

    public ResponseData update(Customer cs, String userid) {
        Optional<Customer> optional = cusRepos.findById(cs.getCustomerid());
        if (!optional.isPresent()) {
            return new ResponseData(ResponseData.STATE.FAIL.toString(),"Không tìm thấy bản ghi");
        }
        Customer info=optional.get();
        //Cập nhật các trường thông tin
        info.setAge(cs.getAge());
        info.setBirthday(cs.getBirthday());
        info.setName(cs.getName());
        info.setNum(cs.getNum());
        info.setUddtime(new Date());
        info.setUserudid(userid);
        cusRepos.save(info);
        return new ResponseData(ResponseData.STATE.OK.toString(),"Cập nhật thành công!");

    }

    @Transactional
    public ResponseData delete(String id) {
        String sql="DELETE FROM CUSTOMER WHERE CUSTOMERID=?1";
        Query qr=entityManager.createNativeQuery(sql);
        qr.setParameter(1,id);
        int i=qr.executeUpdate();
        if (i> 0)
            return new ResponseData(ResponseData.STATE.OK.toString(),"Xóa thành công!");
        else
            return new ResponseData(ResponseData.STATE.FAIL.toString(),"Xóa không thành công, không tồn tại bản ghi!");
    }

    public ResponseData callSP(String name) {
        Query qr=entityManager.createNativeQuery("{call runLstCustomer(?)}", Customer.class);
        qr.setParameter(1,name);
        List<Customer> lst=qr.getResultList();
        ResponseData responseData=new ResponseData(ResponseData.STATE.OK.toString(),"Call thành công!");
        responseData.setData(lst); //Trả ra mã bản ghi
        return responseData;

    }
}
