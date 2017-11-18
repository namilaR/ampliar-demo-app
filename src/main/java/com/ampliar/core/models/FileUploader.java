package com.ampliar.core.models;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.web.multipart.MultipartFile;

public class FileUploader {
	private static String UPLOADED_FOLDER = "/uploads/";

	public boolean uploadFile(MultipartFile file) {
		System.out.println("Inside file uploader!");
		if (file.isEmpty()) {
			System.err.println("Please select a file to upload");
			return false;
		}

		byte[] bytes;

		try {
			bytes = file.getBytes();
			String rootPath = System.getProperty("catalina.home");
			System.out.println(rootPath);
			File dir = new File(rootPath + File.separator + "uploads");

			if (!dir.exists())
				dir.mkdirs();

			File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());

			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;

	}
}
