package br.com.roma.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GymService {
    private final ChatModel chatModel;

    public GymService(final ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    /**
     * Método para montar o treino para um determinado dia da semana.
     * @param firstTrain tipo do primeiro treino. Ex: perna.
     * @param secundTrain tipo do segundo treino. Ex: ombro.
     * @param dayWeek dia da semana em que o treino será realizado.
     * @return treino completo.
     */
    public String getTrain(final String firstTrain, final String secundTrain, final String dayWeek){
        var template = """
                Hoje é {dayWeek}.
                Eu quero criar um treino completo focando em duas partes do corpo,
                onde o primeiro treino deverá ser {firstTrain}, e o segundo deverá ser {secundTrain}.
                Por favor considere que o treino seja elaborado para que o aluno consiga realizar em até 1 hora.
                Retorne o resultado em uma lista separando os treinos e ordenando cada exercício.
                """;

        PromptTemplate prompt = new PromptTemplate(template);

        Map<String, Object> params = Map.of(
            "firstTrain", firstTrain,
            "secundTrain", secundTrain,
            "dayWeek", dayWeek
        );
        Prompt resultPrompt = prompt.create(params);

        return chatModel.call(resultPrompt).getResult().getOutput().getText();
    }
}
