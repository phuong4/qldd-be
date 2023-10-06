package com.evnit.ttpm.AuthApp.repository.category;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.evnit.ttpm.AuthApp.entity.category.A_ASSET;
import com.evnit.ttpm.AuthApp.entity.category.SDeliveryUnit;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface A_ASSETRepository extends JpaRepository<A_ASSET, String> {
    @Query("Select a from A_ASSET a where a.ASSETID= :id and a.ISDELETE = false")
    @NotNull
    Optional<A_ASSET> findByIdNotDelete(@NotNull String id);

    @Query("Select  c from A_ASSET c where lower(c.SERIALNUM) like lower(:serialNumber) and c.CATEGORYID = :#{#categoryId} and c.ISDELETE = false ")
    Optional<A_ASSET> findBySerialNum(String serialNumber, @Param("categoryId") String categoryId);

    @Query("Select  c from A_ASSET c where c.ASSETID = :#{#assetId} and c.CATEGORYID = :#{#categoryId} and c.ISDELETE = false ")
    Optional<A_ASSET> findByAssetId(String assetId, @Param("categoryId") String categoryId);

    Optional<A_ASSET> findByASSETIDAndCATEGORYIDAndISDELETEIsFalse(String assetId, String categoryId);

    @Query("Select c from A_ASSET  c where  c.ISDELETE = false and c.transId = :transId and c.ISSAVE = false")
    List<A_ASSET> findByIsSave(@Param("transId") BigInteger transId);

    @Query("Select c from A_ASSET  c where  c.ISDELETE = false and c.ASSETID_PARENT = :#{#id}")
    List<A_ASSET> findByParentId(@Param("id") String id);

    @Query("Select c from A_ASSET  c where  c.ISDELETE = false  and c.ASSETID_PARENT = :#{#id}")
    List<A_ASSET> findByParent(@Param("id") String id);

    @Query("Select case when count(c) > 0 then true else false end from A_ASSET c  where c.ISDELETE = false and c.ASSETID_PARENT = :#{#id}")
    boolean existByAssetIdParent(String id);
}
