package com.adelean.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	public static File unzipFile(File file) throws IOException {
		File newFile = null;
		byte[] buffer = new byte[1024];
		ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
		ZipEntry zipEntry = zis.getNextEntry();
		while(zipEntry != null) {
			String filename = file.getParentFile().getAbsolutePath() + File.separator + zipEntry.getName();
			logger.debug("Create file " + filename);
			newFile = new File(filename);
			FileOutputStream fos = new FileOutputStream(newFile);
			int len;
			while ((len = zis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			zipEntry = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();
		return newFile;
	}
}
