package com.gdsc.hackathon.controller;

import com.gdsc.hackathon.dto.QRDto;
import com.gdsc.hackathon.service.QRService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QRController {

    @PostMapping("/QR")
    public ResponseEntity<byte[]> generateQr (@RequestBody QRDto qrDto){
        try {
            byte[] qrCodeImage = QRService.generateQRCode(qrDto);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(qrCodeImage.length);

            return new ResponseEntity<>(qrCodeImage, headers, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}