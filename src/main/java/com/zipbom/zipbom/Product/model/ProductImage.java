package com.zipbom.zipbom.Product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
public class ProductImage {

    @Value("${file.path}")
    private String rootPath;

    @Column(name = "product_image_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String imageUrl;
    private final String fileName;
    private final Long fileSize;
    private final String fileEncoding;
    private final String fileType;
    private LocalDateTime createdAt;

    private ProductImage(String fileEncoding, String fileType, String fileName, Long fileSize) {
        this.fileType = fileType;
        this.fileName = fileName;
        this.fileEncoding = fileEncoding;
        this.fileSize = fileSize;
    }

    public static ProductImage of(MultipartFile file) throws IOException {
        String imageName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String imageUrl = "D:/zipbom_file/" + imageName;
        File f = new File(imageUrl);
        FileInputStream fis = new FileInputStream(f);
        byte[] byteArray = new byte[(int) f.length()];
        fis.read(byteArray);
        String fileEncoding = Base64.encodeBase64String(byteArray);
        return new ProductImage(fileEncoding, file.getContentType(), file.getOriginalFilename(), file.getSize());
    }

    public static void FileWrite(MultipartFile file) throws IOException {
        String imageName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String imageUrl = "D:/zipbom_file/" + imageName;
        Path path = Paths.get(imageUrl);
        try {
            Files.write(path, file.getBytes());
        } catch (IOException ioException) {
            throw new IOException("product file write error");
        }
    }
}

