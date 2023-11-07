package com.singsongchanson.domain.asset.command.application.dto;

import com.singsongchanson.domain.asset.domain.entity.Asset;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class AssetResponseDTO {

    private Long assetNo;
    private String originFileName;
    private String savedFileUrl;
    private String gender;
    private double scale;

    public static AssetResponseDTO from(Asset asset) {

        return new AssetResponseDTO(
                asset.getAssetNo(),
                asset.getOriginFileName(),
                asset.getSavedFileUrl(),
                asset.getGender(),
                asset.getScale());
    }
}
