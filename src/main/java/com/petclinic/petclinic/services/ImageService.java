package com.petclinic.petclinic.services;

import java.io.IOException;
import java.util.List;

import com.petclinic.petclinic.models.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	Image saveImage(MultipartFile file) throws IOException;

	List<Image> createImages(MultipartFile[] files) throws IOException;

	void deleteImages(List<Image> images);

}
