package br.com.roma.service;

import org.springframework.ai.image.ImageGeneration;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

/**
 * Classe para geração de imagens a partir de um prompt.
 */
@Service
public class ImageService {
    private final OpenAiImageModel imageModel;

    public ImageService(OpenAiImageModel imageModel) {
        this.imageModel = imageModel;
    }

    /**
     * Método para geração de imagens.
     * @param prompt contem o texto descrevendo a imagem a ser gerada.
     * @param quality qualidade da imagem (standard ou hd).
     * @param n quantidade de imagens que serão geradas.
     * @return
     */
    public ImageResponse getImage(String prompt, String quality, Integer n) {

        final ImageResponse response = imageModel.call(
                new ImagePrompt(prompt, this.buildImageOptions(quality, n)));
        return response;
    }

    private OpenAiImageOptions buildImageOptions(String quality, Integer n) {
        OpenAiImageOptions options = new OpenAiImageOptions();
        options.setSize("1024x1024");
        options.setModel("dall-e-3");
        options.setResponseFormat("url");
        options.setN(n);
        options.setQuality(quality);
        options.setHeight(null);
        options.setWidth(null);
        return options;
    }
}
