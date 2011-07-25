package com.jgk.springrecipes.fileupload.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller //(value="fileUploadController")
public class FileUploadController {

	@RequestMapping(value="/gravy")
	public @ResponseBody String gravy() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><body>Gravy time</body></html>");
		return sb.toString();
	}

	@RequestMapping(value="/upload.form")
	public ModelAndView doiingSomesazeing(
				HttpServletRequest request,
				HttpServletResponse response,
				ModelAndView mov,
				FileUploadBean bean) {
		System.out.println("HOWDY FRIEND");
		System.out.println(bean);
		if(bean.getFile()!=null) {
			System.out.println("DOING SOMETHING WITH THE FILE");
			MultipartFile f = bean.getFile();
			try {
				byte[] data= f.getBytes();
				System.out.println(data.length);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Content Type: " + f.getContentType());
			System.out.println("file name: " + f.getName());
			System.out.println("Size: "+f.getSize());
			System.out.println("Original file name: " + f.getOriginalFilename());
			mov.addObject("originalFileName",f.getOriginalFilename());
			mov.addObject("fileLength",f.getSize());
			
		} else {
			System.out.println("NO FILE PROVIDED");
		}
		mov.addObject("jed","Jed's friend");
		mov.setViewName("gotfile");
		return mov;
	}
}
