package com.cash.payment.controller;

import com.cash.payment.DTO.Transfer;
import com.cash.payment.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public ResponseEntity transfer(@RequestBody Transfer transfer) {
        return ResponseEntity.ok(transferService.transfer(transfer));
    }
}
