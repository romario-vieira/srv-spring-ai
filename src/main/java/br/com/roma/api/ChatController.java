package br.com.roma.api;

import br.com.roma.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatService service;

    public ChatController(ChatService service) {
        this.service = service;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt){
        return service.callAi(prompt);
    }
}
