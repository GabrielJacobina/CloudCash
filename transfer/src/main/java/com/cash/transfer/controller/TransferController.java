package com.cash.transfer.controller;

import com.cash.transfer.DTO.Transfer;
import com.cash.transfer.service.TransferService;
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
