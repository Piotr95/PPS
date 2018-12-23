package szczepaniak.ppss.StackService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import szczepaniak.ppss.StackService.model.Image;
import szczepaniak.ppss.StackService.service.StorageService;


import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/storage")
public class StorageController {
    private StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @ResponseBody
    @GetMapping(path = "/{file:.+}", produces = "file/*")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Resource> serveFile(@PathVariable String image) {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(600, TimeUnit.SECONDS).cachePublic())
                .body(storageService.load(image));
    }


    @PostMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public String handleFileUpload(@RequestParam("image") MultipartFile image) {
        return storageService.store(image);
    }

//    @PostMapping()
//    @ResponseStatus(value = HttpStatus.OK)
//    public String addImageToMasterpiece(@RequestParam("image") MultipartFile file, @RequestBody Image image) {
//        return storageService.store(file);
//    }
}
