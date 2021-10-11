package com.example.demo.tasklet;

import com.example.demo.util.FileUtil;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@Component(value="fileDownloadTasklet")
public class FileDownloadTasklet implements Tasklet {

    @Autowired
    private FileUtil fileUtil;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        fileUtil.downloadFile(new URL(""));
        return RepeatStatus.FINISHED;
    }
}
