package com.melardev.controllers;

import java.io.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.Valid;

import com.melardev.validators.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.melardev.models.forms.FileUploadForm;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/upload_download")
public class UploadDownloadFileController {

    @GetMapping
    public String getHome() {
        return "upload/home";
    }

    @RequestMapping(path = "/download")
    public void downloadFile(@RequestParam("filename") String fileName, HttpServletRequest request,
                             HttpServletResponse response) {

        String filePath = request.getServletContext().getRealPath("/imgs/logo.png");
        File downloadFile = new File(filePath);

        ServletContext context = request.getServletContext();
        String mimeType = context.getMimeType(filePath);

        if (mimeType == null)
            mimeType = "application/octet-stream";

        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFile.getName() + "\"");

        FileInputStream fis;
        try {
            fis = new FileInputStream(downloadFile);
            FileCopyUtils.copy(fis, response.getOutputStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Upload Files

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new FileValidator());
    }

    @ModelAttribute
    public void initBind(Model model) {
        FileUploadForm fileUploadForm = new FileUploadForm();
        model.addAttribute("fileUploadForm", fileUploadForm);
    }

    @GetMapping(path = "/upload")
    public String initForm(Model model) {
        FileUploadForm fileUploadForm = new FileUploadForm();
        //model.addAttribute("fileUploadForm", fileUploadForm);
        return "upload/upload";
    }

    @PostMapping(path = "/upload3")
    public String submitForm(Model model, @Valid @ModelAttribute("fileUploadForm") FileUploadForm fileUploadForm,
                             BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("fileUploadForm", fileUploadForm);
            return "upload/upload";
        } else {
            MultipartFile multiPartFile = fileUploadForm.getFile();
            File file = new File("D:/workspace/JavaEE/TutsSpringMvc/uploads/" + multiPartFile.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(file);
            byte[] bytes = multiPartFile.getBytes();
            fos.write(bytes);
            fos.close();

            model.addAttribute("message", String.format("File %s with size %d bytes has been uploaded succesfully"
                    , multiPartFile.getOriginalFilename(), multiPartFile.getSize()));

            return "upload/success";
        }
    }

    @PostMapping("/upload2")
    public String simpleUpload2(@RequestParam("upload_file") MultipartFile multipartFile, Model model) {

        model.addAttribute("message", String.format("File %s with size %d bytes has been uploaded succesfully"
                , multipartFile.getOriginalFilename(), multipartFile.getSize()));
        return "upload/success";
    }

    @PostMapping("/upload1")
    public String simpleUpload1(MultipartHttpServletRequest request, Model model) {
        MultipartFile multipartFile = request.getFile("upload_file");
        model.addAttribute("message", String.format("File %s with size %d bytes has been uploaded succesfully"
                , multipartFile.getOriginalFilename(), multipartFile.getSize()));
        File file = new File("D:/workspace/JavaEE/TutsSpringMvc/uploads/" + multipartFile.getOriginalFilename());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] bytes = multipartFile.getBytes();
            fos.write(bytes);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "upload/success";
    }

    @PostMapping("/upload4")
    public String handleUpload(@RequestParam("upload_file") Part part, Model model) {
        // Will only work for StandardServletMultipartResolver
        try {
            InputStream inputStream = part.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
