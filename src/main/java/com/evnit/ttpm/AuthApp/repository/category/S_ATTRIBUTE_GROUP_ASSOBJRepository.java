package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.SAttrGroupAssObjId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.evnit.ttpm.AuthApp.entity.category.A_ASSET;
import com.evnit.ttpm.AuthApp.entity.category.SDeliveryUnit;
import com.evnit.ttpm.AuthApp.entity.category.S_ATTRIBUTE_GROUP_ASSOB;

import java.util.List;

@Repository

public interface S_ATTRIBUTE_GROUP_ASSOBJRepository extends JpaRepository<S_ATTRIBUTE_GROUP_ASSOB, SAttrGroupAssObjId> {
    @Query("select c from S_ATTRIBUTE_GROUP_ASSOB c where c.OBJID = :#{#objId}")
    List<S_ATTRIBUTE_GROUP_ASSOB> findByObjId(@Param("objId") String objId);

    @Modifying
    @Query("SELECT ag FROM S_ATTRIBUTE_GROUP_ASSOB ag WHERE ag.OBJID IN :#{#OBJID}")
    List<S_ATTRIBUTE_GROUP_ASSOB> findAllByOBJID(@Param("OBJID") List<String> OBJID);
}
