package com.cx.board.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileUploadConfig {
	@Value("${file.upload-dir}")
	private String uploadDir;

	public String getUploadDir() {
		return this.uploadDir;
	}
}
