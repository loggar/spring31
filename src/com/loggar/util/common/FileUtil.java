package com.loggar.util.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class FileUtil {
	private FileUtil() {
		throw new AssertionError(FileUtil.class.getName() + " cannot be instantiable.");
	}

	public static Date getFileModifyDate(String filePath) {
		File file = null;

		try {
			file = new File(filePath);
			if (file == null || !file.exists() || !file.isFile()) {
				return null;
			}
		} catch (Exception e) {
			return null;
		}

		return new Date(file.lastModified());
	}

	public static boolean isExist(String path) {
		File f = new File(path);
		if (f.exists() && !f.isDirectory()) {
			return true;
		}
		return false;
	}

	public static boolean delFile(String fileName) {
		File f = new File(fileName);
		if (f.exists())
			if (f.isFile())
				return f.delete();
		return false;
	}

	public static boolean copyFile(String fromFileName, String toFileName) throws IOException {
		FileInputStream fis = new FileInputStream(fromFileName);
		FileOutputStream fos = new FileOutputStream(toFileName);

		int data = 0;
		while ((data = fis.read()) != -1)
			fos.write(data);
		fis.close();
		fos.close();
		return existsFile(toFileName);
	}

	public static boolean existsFile(String fileName) {
		File f = new File(fileName);
		return f.exists();
	}

	public static boolean makeDirectory(String dirName) {
		File f = new File(dirName);
		return f.mkdir();
	}

	public static boolean removeDirectory(String dirName) throws IOException {
		return removeAllDirectory(new File(dirName));
	}

	public static boolean removeAllDirectory(File target) throws IOException {
		if (target.exists()) {
			File[] files = target.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory())
					removeAllDirectory(files[i]);
				else
					files[i].delete();
			}
			return target.delete();
		}

		return false;
	}
}
