package com.bbs.controller.utils;


import com.bbs.tool.Tool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:上传图片
 * Created By KL
 * Date: 2019/8/8
 * Time: 19:02
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    @ResponseBody
    @RequestMapping("uploadimg.do")
    public Map<String, String> uploadImg(HttpServletRequest request) throws IOException {
        //0代表失败，1代表成功，2代表格式错误
        HashMap<String, String> map = new HashMap<>();
        MultipartHttpServletRequest httpServletRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> fileList = httpServletRequest.getFiles("file");
        for (MultipartFile multipartFile : fileList) {
            if (!multipartFile.isEmpty()) {

                //文件夹名称
                String dirname = Tool.getyyyyMMdd();
                String filePath = com.bbs.tool.PublicStatic.FILE_PATH + "/" + dirname + "/";
                File file = new File(filePath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                String filenamelast = getFilename(multipartFile.getOriginalFilename()).toUpperCase();
                //System.out.println(filePath);
                String path = request.getSession().getServletContext().getRealPath("/");
                System.out.println(path);
                if (!filenamelast.equals(".JPG") && !filenamelast.equals(".GIF") && !filenamelast.equals(".JPEG") && !filenamelast.equals(".PNG") && !filenamelast.equals(".SWF")) {
                    map.put("isok", "2");
                } else {
                    filenamelast = Tool.getyyyyMMddHHmmssSSS() + Tool.getRandom() + filenamelast;
                    map.put("isok", "1");
                    map.put("path", dirname + "/" + filenamelast);
                    // 转存文件
                    try {
                        multipartFile.transferTo(new File(filePath + filenamelast));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return map;
    }

    //获取文件类型
    private String getFilename(String filename) {
        int i = filename.lastIndexOf(".");
        if (i != -1) {
            filename = filename.substring(i, filename.length());

        }
        return filename;
    }
}
