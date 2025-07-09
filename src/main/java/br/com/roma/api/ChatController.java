package br.com.roma.api;

import br.com.roma.service.ChatService;
import br.com.roma.service.GymService;
import br.com.roma.service.ImageService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatController {

    private final ChatService service;
    private final GymService gymService;
    private final ImageService imageService;

    public ChatController(ChatService service, GymService gymService, ImageService imageService) {
        this.service = service;
        this.gymService = gymService;
        this.imageService = imageService;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt){
        return service.callAi(prompt);
    }

    @GetMapping("ask-ai-chat")
    public String getChatResponse(@RequestParam String prompt){
        return service.callAiChatResponse(prompt);
    }

    @GetMapping("ask-ai/gym-chat")
    public String getGymChat(@RequestParam String firstTrain, @RequestParam String secundTrain, @RequestParam String weekDay){
        return gymService.getTrain(firstTrain, secundTrain, weekDay);
    }

    @GetMapping("ask-ai/image")
    public List<String> getImage(@RequestParam String prompt,
                                 @RequestParam(defaultValue = "hd") String quality,
                                 @RequestParam(defaultValue = "1") Integer n){
        ImageResponse imageResponse = imageService.getImage(prompt, quality, n);
        List<String> imgUrls = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();
        return imgUrls;
    }
}
