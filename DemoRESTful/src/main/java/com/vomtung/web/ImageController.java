package com.vomtung.web;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.vomtung.util.Utils;

@Controller
@RequestMapping(value = "/image")
public class ImageController {

	/**
     * Upload single file using Spring Controller
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFileHandler(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
 
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                
                // Creating the directory to store file
                File dir = new File(Utils.getFolderStoreImage(request.getSession().getServletContext().getRealPath("/")));
                if (!dir.exists())
                    dir.mkdirs();
                // Create the file on server
                File serverFile = new File(Utils.getFolderStoreImage(request.getServletContext().getRealPath("/"))+System.currentTimeMillis()+"-"+UUID.randomUUID().toString()+".jpg");
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded file="+serverFile.getAbsolutePath();
            } catch (Exception e) {
                return "You failed to upload => " + e.getMessage();
            }
        } else {
            return "You failed to upload "+ " because the file was empty.";
        }
    }
    
    /**
     * Download single file using
     * @throws IOException 
     */
    @RequestMapping(value = "/getimage", method = RequestMethod.GET)
    public void getImage(@RequestParam(value="fileName", required=true) String  fileName,HttpServletResponse response,HttpServletRequest request) throws IOException {
		File f = new File(Utils.getFolderStoreImage(request.getServletContext().getRealPath("/")) + fileName);
		BufferedImage bi = ImageIO.read(f);
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.close();
    }
}
