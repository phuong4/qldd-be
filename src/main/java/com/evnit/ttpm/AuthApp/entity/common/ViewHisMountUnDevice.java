package com.evnit.ttpm.AuthApp.entity.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Table(name = "VIEW_HIS_MOUNT_UN_DEVICE")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewHisMountUnDevice implements Comparable<ViewHisMountUnDevice> {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "ASSETDESC")
    private String assetDesc;
    @Column(name = "NGAYGAN")
    private Date ngayGan;
    @Column(name = "SERIALNUM")
    private String serialNum;
    @Column(name = "TYPE_ACCEPT")
    private String typeAccept;
    @Column(name = "NGAYGO")
    private Date ngayGo;
    @Column(name = "ID_DEVICE")
    private String idDevice;
    @Column(name = "CATEGORYTYPE")
    private String categoryType;
    public void sortBy(String property, boolean ascending, List<ViewHisMountUnDevice> list) {
        Comparator<ViewHisMountUnDevice> comparator = (obj1, obj2) -> {
            int result = 0;

            switch (property) {
                case "ngayGan":
                    result = obj1.ngayGan.compareTo(obj2.ngayGan);
                    break;

                case "ngayGo":
                    result = obj1.ngayGo.compareTo(obj2.ngayGo);
                    break;

                // Add cases for other properties if needed

                default:
                    throw new IllegalArgumentException("Invalid property: " + property);
            }

            return ascending ? result : -result; // Reverse the result for descending order
        };

        Collections.sort(list, comparator);
    }
    @Override
    public int compareTo(@NotNull ViewHisMountUnDevice o) {
        int result = this.ngayGan.compareTo(o.ngayGan);
        if (result == 0) {
            result = this.ngayGo.compareTo(o.ngayGo);
        }
        return result;
    }
}
