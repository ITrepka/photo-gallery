package com.itrepka.photogallery;

import com.itrepka.photogallery.view.controllers.AdminGalleryViewController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
//@ComponentScan({"com.itrepka.photogallery"})
public class PhotoGalleryApplication {

	public static void main(String[] args) {
		new File(AdminGalleryViewController.uploadDirectory).mkdir();
		SpringApplication.run(PhotoGalleryApplication.class, args);
	}

}
