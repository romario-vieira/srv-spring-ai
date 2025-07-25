package br.com.roma.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
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

    public String callAiChatResponse(String prompt){
        ChatResponse response = chatModel.call(new Prompt(prompt, OpenAiChatOptions.builder().model("gpt-4o-mini").temperature(0.4).build()));
        return response.getResult().getOutput().getText();
    }
}
