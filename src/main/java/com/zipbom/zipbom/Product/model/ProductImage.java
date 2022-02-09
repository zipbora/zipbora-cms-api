package com.zipbom.zipbom.Product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
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

    private String fileName;
    private Long fileSize;
    @Lob
    private String fileEncoding;
    private String fileType;
    private String fileUrl;
    @Value("${file.path}")
    private String rootPath;
    @Id
    @Column(name = "product_image_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private LocalDateTime createdAt;

    protected ProductImage() {
    }

    private ProductImage(String fileEncoding, String fileType, String fileName, Long fileSize, String fileUrl) {
        this.fileType = fileType;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileUrl = fileUrl;
        this.fileEncoding = fileEncoding;
    }

    public static ProductImage of(MultipartFile file) throws IOException {
        String fileUrl = makeUrl(file.getOriginalFilename());
        String fileEncoding = encoding(file);
        return new ProductImage(fileEncoding, file.getContentType(), file.getOriginalFilename(), file.getSize(), fileUrl);
    }

    private static String encoding(MultipartFile file) throws IOException {
        byte[] byteArray = file.getBytes();
        String fileEncoding = Base64.encodeBase64String(byteArray);
        return fileEncoding;
    }

    private static String makeUrl(String fileName) {
        String randomFileName = UUID.randomUUID() + "_" + fileName;
        String fileUrl = "D:/zipbom_file/" + randomFileName;
        return fileUrl;
    }

    public void fileWrite(MultipartFile file) throws IOException {
        Path path = Paths.get(fileUrl);
        try {
            Files.write(path, file.getBytes());
        } catch (IOException ioException) {
            throw new IOException("product file write error");
        }
    }
}

