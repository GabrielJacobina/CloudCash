package com.cash.transfer.service;

import com.cash.transfer.dto.Transfer;

import java.io.IOException;

public interface TransferService {

    String transfer(Transfer transfer) throws IOException;
}
