package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Slf4j
public class FileUtil {

    public void downloadFile(URL url) throws IOException {
        FileOutputStream fos = null;
        try {
            ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
            fos = new FileOutputStream(getLocalFilePath());
            fos.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        } catch (Exception e) {
            log.error("Download File Error {}", e.getMessage());
        }finally {
            fos.close();
        }
    }

    public void deleteFile(){
        try {
            Files.delete(new File(getLocalFilePath()).toPath());
        }catch (Exception e){
            log.error("Delete File Error {}", e.getMessage());
        }
    }

    private String getLocalFilePath(){
        return getClass().getClassLoader().getResource("student-data.csv").getFile();
    }
}
