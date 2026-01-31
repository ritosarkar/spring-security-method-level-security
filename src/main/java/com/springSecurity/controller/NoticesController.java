package com.springSecurity.controller;


import com.springSecurity.model.Notice;
import com.springSecurity.repository.NoticeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
public class NoticesController {

    private final NoticeRepository noticeRepository;

    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotices(){
       List<Notice> notices=noticeRepository.findAllActiveNotices();
       if(notices!=null){
           return ResponseEntity.ok()
                   .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                   .body(notices);
       }
        return null;
    }
}
