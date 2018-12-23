package szczepaniak.ppss.StackService.service;

import com.google.common.collect.Lists;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import szczepaniak.ppss.StackService.infrastructure.exeption.ResourceNotFoundException;
import szczepaniak.ppss.StackService.model.Image;
import szczepaniak.ppss.StackService.repository.ImageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    ImageRepository imageRepository;
    StorageService storageService;
    Logger logger = LoggerFactory.getLogger(ImageService.class);
    @Autowired
    public ImageService( StorageService storageService,ImageRepository imageRepository) {
        this.storageService=storageService;
        this.imageRepository = imageRepository;

    }
    public Image create(MultipartFile file,Image image) {
        String s=image.getImageURL();
        image.setImageURL(s);

        return imageRepository.save(image);
    }


    public Image update(MultipartFile file,Image image) {
        if (imageRepository.existsById(image.getId())) {
            image.setImageURL(storageService.store(file));
            return imageRepository.save(image);
        } else {
            throw new ResourceNotFoundException();
        }
    }


    public void delete(String id) {
        if (imageRepository.existsById(id)) {
            Image image=imageRepository.findById(id).get();
            storageService.delete(image.getImageURL());
            imageRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public Image getById(String id) {
        Optional<Image> image = imageRepository.findById(id);
        return image.orElseThrow(ResourceNotFoundException::new);
    }

    public List<Image> getAll() {
        Iterable<Image> images = imageRepository.findAll();
        return  Lists.newArrayList(images);
    }
}
