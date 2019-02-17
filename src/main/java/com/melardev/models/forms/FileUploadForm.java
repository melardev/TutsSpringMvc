package com.melardev.models.forms;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadForm {

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
