/*
 ****************************************************
 *
 *			TTPM-EVNICT
 *
 ****************************************************
 */
package com.evnit.ttpm.AuthApp.repository.samples;

import com.evnit.ttpm.AuthApp.entity.samples.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	@Query(value = "select * from Customer order by name", nativeQuery = true)
	//@Query(value = "select CUSTOMERID, NAME from Customer order by name", nativeQuery = true) //Truy vấn thiếu trường sẽ bị lỗi
	List<Customer> getALL();

	@Query(value = "select * from Customer where customerid=?", nativeQuery = true)
	Customer getById(String customerid);

}
