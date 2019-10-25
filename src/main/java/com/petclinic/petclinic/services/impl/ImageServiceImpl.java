package com.petclinic.petclinic.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.petclinic.petclinic.models.Image;
import com.petclinic.petclinic.models.Pet;
import com.petclinic.petclinic.repositories.ImageRepository;
import com.petclinic.petclinic.services.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

	@Value("${upload-files-folder}")
	private String uploadFolder;

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class.getName());

	@Autowired
	ImageRepository imageRepository;

	@Override
	public Image saveImage(MultipartFile file, Pet pet) throws IOException {
		if (file.isEmpty()) {
			throw new IllegalArgumentException("Image cannot be empty.");
		}
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(this.uploadFolder + UUID.randomUUID() + "-" + file.getOriginalFilename());
			Files.write(path, bytes);
			Image image = new Image(path.toString());
			pet.addImage(image);
			return imageRepository.save(image);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new IOException(e.getMessage());
		}
	}

	@Override
	public List<Image> createImages(MultipartFile[] files) throws IOException {
		if (files.length == 0) {
			LOGGER.error("There are no files.");
			throw new IllegalArgumentException("There are no files.");
		}
		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				LOGGER.error("Image cannot be empty.");
				throw new IllegalArgumentException("Image cannot be empty.");
			}
		}
		try {
			List<Image> images = new ArrayList<>();
			for (MultipartFile file : files) {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(this.uploadFolder + UUID.randomUUID() + "-" + file.getOriginalFilename());
				Files.write(path, bytes);
				Image image = new Image(path.toString());
				images.add(image);
			}
			return images;
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			throw new IOException(e.getMessage());
		}
	}

	@Override
	public void deleteImages(List<Image> images) {
		try {
			for (Image image : images) {
				Path path = Paths.get(image.getPath());
				File file = path.toFile();
				file.delete();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	public String getUploadFolder() {
		return uploadFolder;
	}

	public void setUploadFolder(String uploadFolder) {
		this.uploadFolder = uploadFolder;
	}
}
