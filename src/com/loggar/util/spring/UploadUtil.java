package com.loggar.util.spring;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {
	private static final Logger logger = LoggerFactory.getLogger(UploadUtil.class);

	public static boolean uploadFile(MultipartFile formFile, String realPath, String newFileName) throws IOException {
		try {
			InputStream stream = formFile.getInputStream();
			OutputStream bos = new FileOutputStream(realPath + newFileName);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];

			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1)
				bos.write(buffer, 0, bytesRead);
			bos.close();
			stream.close();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			return false;
		} catch (IOException e) {
			logger.error(e.getMessage());
			return false;
		}

		return true;
	}

	public static String uploadFile(MultipartFile formFile, String realPath) throws IOException {
		String newFileName = null;
		try {
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
			String today = formatter.format(new java.util.Date());

			String[] arrFileList = formFile.getOriginalFilename().split("[.]");
			int extIndex = arrFileList.length - 1;
			String ext = arrFileList[extIndex];
			newFileName = today + '_' + getRandomString(4) + "." + ext;

			uploadFile(formFile, realPath, newFileName);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			return null;
		} catch (IOException e) {
			logger.error(e.getMessage());
			return null;
		}
		return newFileName;
	}

	public static String getRandomString(int len) {
		Random random = new Random();
		char alphaNum[] = { 'a', 'b', 'c', 'd', 'e', 'f', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		String str = "";
		for (int j = 0; j < len; j++)
			str += alphaNum[random.nextInt(16)];
		return str;
	}
}
