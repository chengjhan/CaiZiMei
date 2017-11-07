package com.czmbeauty.common.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class ImageUtil {

	public static boolean isImage(MultipartFile file) {

		String extension = FilenameUtils.getExtension(file.getOriginalFilename());

		if ("jpg".equals(extension)) {
			return true;
		}
		if ("png".equals(extension)) {
			return true;
		}
		return false;
	}

}
