package com.cash.transfer.service;

import com.cash.transfer.DTO.Transfer;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TransferService {

    String transfer(Transfer transfer) throws JsonProcessingException;
}
