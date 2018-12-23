package szczepaniak.ppss.StackService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import szczepaniak.ppss.StackService.model.Image;
import szczepaniak.ppss.StackService.service.ImageService;

import java.util.List;

@Controller
@RequestMapping("/api/images")
public class ImageController {
    ImageService imageService;
    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    public Image create(@RequestParam("file") MultipartFile file ,@ModelAttribute Image image) {
        return imageService.create(file,image);
    }

    @PutMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public Image update(@RequestParam("file") MultipartFile file,@ModelAttribute Image image) {
        return imageService.update(file,image);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        imageService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Image getById(@PathVariable("id") String id) {
        return imageService.getById(id);
    }
    @GetMapping()
    public List<Image> getPeople() {
        return imageService.getAll();
    }
}
