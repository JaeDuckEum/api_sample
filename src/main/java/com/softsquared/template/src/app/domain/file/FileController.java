package com.softsquared.template.src.app.domain.file;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/files")
public class FileController {

    private final String PATH = "C:/Users/kjy93/OneDrive/바탕 화면/default/images/";

    /**
     * CKEditor 5
     * */
//    @PostMapping("")
//    public Map<String,Object> uploadImage(MultipartHttpServletRequest request) throws IOException {
//
//        Map<String,Object> result = new HashMap<>();
//        Map<String,String> url = new HashMap<>();
//
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
//        ZonedDateTime current = ZonedDateTime.now();
//        String FolderPath = current.format(format);
//        String path = PATH + FolderPath;
//        File file = new File(path);
//        if(file.exists() == false) {
//            file.mkdirs();
//        }
//        Iterator<String> iterator = request.getFileNames();
//
//        String newFileName="", originalFileExtension, contentType;
//
//        while(iterator.hasNext()) {
//            List<MultipartFile> list = request.getFiles(iterator.next());
//            for(MultipartFile multipartFile : list) {
//                if(multipartFile.isEmpty() == false) {
//                    contentType = multipartFile.getContentType();
//                    if(ObjectUtils.isEmpty(contentType)) {
//                        break;
//                    }
//                    else {
//                        if(contentType.contains("image/jpeg")) {
//                            originalFileExtension = ".jpg";
//                        }
//                        else if(contentType.contains("image/png")) {
//                            originalFileExtension = ".png";
//                        }
//                        else if(contentType.contains("image/gif")) {
//                            originalFileExtension = ".gif";
//                        }
//                        else {
//                            break;
//                        }
//                    }
//
//                    newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
//
//                    System.out.println(newFileName);
//
//                    file = new File(path + "/" + newFileName);
//                    multipartFile.transferTo(file);
//                }
//            }
//        }
//        url.put("default","http://localhost:9000/files?path="+FolderPath + "/" + newFileName);
//        result.put("urls",url);
//        return result;
//    }

    /**
     * CKEditor 4
     * */
    @PostMapping(value = "")
    public Map<String,Object> uploadImage(MultipartHttpServletRequest request) throws IOException {

        Map<String,Object> result = new HashMap<>();

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        ZonedDateTime current = ZonedDateTime.now();
        String FolderPath = current.format(format);
        String path = PATH + FolderPath;
        File file = new File(path);
        if(file.exists() == false) {
            file.mkdirs();
        }
        Iterator<String> iterator = request.getFileNames();

        String newFileName="";
        Map<String,Object> error= new HashMap<>();

        while(iterator.hasNext()) {
            List<MultipartFile> list = request.getFiles(iterator.next());
            for(MultipartFile multipartFile : list) {
                if(multipartFile.getSize()>10*1024*1024){
                    error.put("message","10MB 이상은 업로드 할 수 없습니다.");
                    result.put("uploaded",0);
                    result.put("error",error);
                    return result;
                }
                if(multipartFile.isEmpty() == false) {

                    newFileName = multipartFile.getOriginalFilename();

                    System.out.println(newFileName);

                    file = new File(path + "/" + newFileName);
                    multipartFile.transferTo(file);
//                    result.put("url","http://localhost:9000/files?path="+FolderPath + "/" + newFileName);
                    result.put("url","http://localhost:9000/files?path="+FolderPath + "/" + newFileName);
                    result.put("uploaded",1);
                }
            }
        }

        return result;
    }

//    @GetMapping(value=""
////            , produces=MediaType.IMAGE_JPEG_VALUE
//    )
//    public ResponseEntity<byte[]> getImage(@RequestParam("path") String path) throws IOException {
//        System.out.println(path);
//
//        InputStream imageStream = new FileInputStream(PATH + path);
//        byte[] image = IOUtils.toByteArray(imageStream);
//        imageStream.close();
//
//        return new ResponseEntity<>(image, HttpStatus.OK);
//    }

    @GetMapping(value="")
    public ResponseEntity<Resource> serveFile(@RequestParam("path") String path) {

        Path paths = Paths.get(PATH+path);
        String fileName="";
        Resource file = null;
        try {
            file = new UrlResource(paths.toUri());
            fileName = URLEncoder.encode(file.getFilename(), String.valueOf(StandardCharsets.UTF_8));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + URLEncoder.encode(file.getFilename(), String.valueOf(StandardCharsets.UTF_8)) + "\"").body(file);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + fileName + "\"").body(file);
    }



}
