package com.evnit.ttpm.AuthApp.repository.device;

import com.evnit.ttpm.AuthApp.entity.category.A_ASSET_LINK;
import com.evnit.ttpm.AuthApp.entity.category.AssetLinkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface A_ASSETLINKRepository extends JpaRepository<A_ASSET_LINK, AssetLinkId> {
    void deleteAllByAssetIdLink(String id);

    @Modifying
    @Query("delete from A_ASSET_LINK a where a.assetId in :#{#assetId}")
    void deleteAllByAssetId(@Param("assetId") List<String> assetId);

    void deleteAllByAssetId(@Param("assetId") String id);

    boolean existsByAssetId(String assetId);
}

