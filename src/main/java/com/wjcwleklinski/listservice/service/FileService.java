package com.wjcwleklinski.listservice.service;

import com.wjcwleklinski.listservice.error.ErrorMessage;
import com.wjcwleklinski.listservice.error.exception.InternalServerException;
import com.wjcwleklinski.listservice.error.exception.NotFoundException;
import com.wjcwleklinski.listservice.model.entity.File;
import com.wjcwleklinski.listservice.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public void storeFile(MultipartFile multipartFile) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            File file = File.builder()
                    .name(fileName)
                    .type(multipartFile.getContentType())
                    .data(multipartFile.getBytes())
                    .build();
            fileRepository.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalServerException(ErrorMessage.FILE_ERROR.getMessage());
        }
    }

    public File getFile(Long fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new NotFoundException(fileId));
    }
}
