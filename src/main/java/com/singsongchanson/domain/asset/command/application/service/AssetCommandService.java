package com.singsongchanson.domain.asset.command.application.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.singsongchanson.domain.asset.command.application.dto.AssetResponseDTO;
import com.singsongchanson.domain.asset.domain.entity.Asset;
import com.singsongchanson.domain.asset.domain.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssetCommandService {

    private final AmazonS3Client amazonS3Client;
    private final AssetRepository assetRepository;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public AssetResponseDTO saveFile(MultipartFile multipartFile, String gender, double scale) throws IOException {

        String originalFilename = multipartFile.getOriginalFilename();

        int index = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(index + 1);

        String savedFileName = UUID.randomUUID() + "." + ext;
        String key = "assets/" + savedFileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        amazonS3Client.putObject(bucket, key, multipartFile.getInputStream(), metadata);
        String savedFileUrl =  amazonS3Client.getUrl(bucket, key).toString();

        Asset asset = Asset.builder()
                .originFileName(originalFilename)
                .savedFileUrl(savedFileUrl)
                .gender(gender)
                .scale(scale)
                .build();

        assetRepository.save(asset);

        return AssetResponseDTO.from(asset);
    }
}
