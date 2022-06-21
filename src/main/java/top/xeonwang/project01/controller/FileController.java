package top.xeonwang.project01.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chen Q.
 */
@RestController
@Slf4j
public class FileController {

    @Value("${filesystem.path}")
    String filepath;

    Map<String, String> filesTemp = new HashMap<String, String>();

    @PostMapping(path = "/file", headers = {"content-type=multipart/form-data"})
    public Object saveFile(MultipartHttpServletRequest request) {

        List<MultipartFile> files = request.getFiles("file");

        //文件类型
        String type = request.getParameter("type");
        //文件数量
        String number = request.getParameter("number");

        String md5f = request.getParameter("md5");

        log.info("type: " + type + " number: " + number);
        for (MultipartFile multipartFile : files) {
            try {

                byte[] bytes = multipartFile.getBytes();


                MessageDigest md5 = MessageDigest.getInstance("MD5");

                byte[] digest = md5.digest(bytes);

                log.info(toHexString(digest));
                log.info(md5f);


            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {

            }

            log.info("file name: " + multipartFile.getOriginalFilename() + " fileSize: " + multipartFile.getSize());
            filesTemp.put(multipartFile.getOriginalFilename(), filepath + multipartFile.getOriginalFilename());


            String s = filesTemp.get(multipartFile.getOriginalFilename());
            File file = new File(s);
            try {
                multipartFile.transferTo(file);
            } catch (FileNotFoundException e) {
                log.error(e.toString());
            } catch (IOException e) {
                log.error(e.toString());
            }
        }
        return "";
    }

    private static String toHexString(byte[] md5) {
        StringBuilder sb = new StringBuilder();
        log.info("md5.length: " + md5.length);
        for (byte b : md5) {
            sb.append(Integer.toHexString(b & 0xff));
        }
        return sb.toString();
    }


    @GetMapping(path = "/file/{name}")
    public Object getFile(@PathVariable(value = "name") String filename, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Type", "application/octet-stream");
        response.addHeader("Accept-Ranges", "bytes");
        response.addHeader("Connection", "keep-alive");
        response.addHeader("Content-Disposition", "attachment;fileName=" + filename);

        String s = filesTemp.get(filename);
        if (s == null) {
            try {
                response.sendError(404, "文件不存在");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "";
        }
        File file = new File(s);
        if (file == null) {
            try {
                response.sendError(404, "文件不存在");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "";
        }
        response.setContentLengthLong(file.length());

        log.info(filename);

        try {

            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fileInputStream);

            OutputStream os = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }

            os.flush();
            os.close();
            bis.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            log.error(e.toString());
        } catch (IOException e) {
            log.error(e.toString());
        }

        return "";
    }

    @GetMapping(path = "/file/{name}/view")
    public Object viewFile(@PathVariable(value = "name") String filename, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
//        response.addHeader("Content-Type", "application/octet-stream");
//        response.setHeader("Content-Type", "application/pdf");
//        response.addHeader("Content-Disposition", "inline;fileName=" + filename);

        String s = filesTemp.get(filename);
        if (s == null) {
            try {
                response.sendError(404, "文件不存在");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "";
        }
        File file = new File(s);
        if (file == null) {
            try {
                response.sendError(404, "文件不存在");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "";
        }
        response.setContentLengthLong(file.length());

        log.info(filename);

        try {

            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fileInputStream);

            OutputStream os = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }

            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            log.error(e.toString());
        } catch (IOException e) {
            log.error(e.toString());
        }

        return "";
    }
}
