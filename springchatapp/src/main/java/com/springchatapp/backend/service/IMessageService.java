package com.springchatapp.backend.service;

import com.springchatapp.backend.model.Message;

import java.util.List;

public interface IMessageService {
    List<Message> getAll();

    Message save(Message message);
}
