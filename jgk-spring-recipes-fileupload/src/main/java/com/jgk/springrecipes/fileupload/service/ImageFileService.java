package com.jgk.springrecipes.fileupload.service;

import java.io.File;

import com.jgk.springrecipes.fileupload.controllers.FileUploadBean;

public interface ImageFileService {
	static final String KEY_IMAGE_ARCHIVE_DIRECTORY_NAME="image.archive.directoryname";
	String getText();
	FileUploadBean archiveImageFileUpload(FileUploadBean fileUploadBean);
}
