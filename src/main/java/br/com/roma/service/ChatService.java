package br.com.roma.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatModel chatModel;

    public ChatService(final ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String callAi(String prompt){
        return chatModel.call(prompt);
    }
}
