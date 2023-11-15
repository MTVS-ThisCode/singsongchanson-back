package com.singsongchanson.domain.asset.command.domain.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "TBL_ASSET")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetNo;
    private String originFileName;
    private String savedFileUrl;
    private String gender;
    private double scale;

    @Builder
    public Asset(Long assetNo, String originFileName, String savedFileUrl, String gender, double scale) {
        this.assetNo = assetNo;
        this.originFileName = originFileName;
        this.savedFileUrl = savedFileUrl;
        this.gender = gender;
        this.scale = scale;
    }
}
