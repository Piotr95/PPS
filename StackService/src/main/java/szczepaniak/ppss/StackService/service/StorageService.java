package szczepaniak.ppss.StackService.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import szczepaniak.ppss.StackService.infrastructure.config.StorageProperties;
import szczepaniak.ppss.StackService.infrastructure.exeption.ImageNotFoundException;
import szczepaniak.ppss.StackService.infrastructure.exeption.StorageException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageService {

    private final Path rootLocation;


    @Autowired
    public StorageService(StorageProperties storageProperties) {
        this.rootLocation = Paths.get(storageProperties.getLocation());
    }
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException();
        }
    }


    public Resource load(String image) {
        return getResource(image, rootLocation);
    }

    public static Resource getResource(String image, Path rootLocation) {
        try {
            Path file = rootLocation.resolve(image);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new ImageNotFoundException();
            }
        } catch (MalformedURLException e) {
            throw new ImageNotFoundException();
        }
    }


    public String store(MultipartFile file) {
        return getString(file, rootLocation);
    }

    public static String getString(MultipartFile file, Path rootLocation) {
        if (file.isEmpty()) {
            throw new StorageException();
        }
        try {
            File img = File.createTempFile("img", "." + FilenameUtils.getExtension(file.getOriginalFilename()), rootLocation.toFile());
            Files.copy(file.getInputStream(), img.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return img.getName();
        } catch (IOException e) {
            throw new StorageException();
        }
    }

    public void delete(String path) {
        new File(path).delete();
    }
}
