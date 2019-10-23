package com.petclinic.petclinic.controllers;

import java.io.IOException;
import java.util.List;

import com.petclinic.petclinic.models.Image;
import com.petclinic.petclinic.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageController {

	@Autowired
	ImageService imageService;


	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public List<Image> saveImages(@RequestParam("file") MultipartFile[] files) throws IOException {
		return imageService.createImages(files);
	}

}
