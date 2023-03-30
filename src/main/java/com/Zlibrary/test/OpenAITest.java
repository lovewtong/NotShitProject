package com.Zlibrary.test;


import com.Zlibrary.controller.OpenAiUtils;
import com.theokanning.openai.completion.CompletionChoice;
import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.completions.CompletionResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.List;

/**
 * @className: OpenAITest
 * @description: TODO 类描述
 * @author: sy
 * @date: 2023-03-22
 **/


@Slf4j
public class OpenAITest {

    /**
     * openAi接口请求API
     */
    @Test
    public void test() {
        List<CompletionChoice> questionAnswer = OpenAiUtils.getQuestionAnswer("使用SpringBoot框架进行Http请求的详细代码");
        for (CompletionChoice completionChoice : questionAnswer) {
            System.out.println(completionChoice.getText());
        }
        List<CompletionChoice> openAiApi = OpenAiUtils.getOpenAiApi("使用SpringBoot框架进行Http请求");
        for (CompletionChoice completionChoice : openAiApi) {
            System.out.println(completionChoice.getText());
        }

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 1080));
        //日志输出可以不添加
        //HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new OpenAILogger());
        //httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OpenAiClient openAiClient = OpenAiClient.builder()
                .apiKey("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")
                .connectTimeout(50)
                .writeTimeout(50)
                .readTimeout(50)
                .proxy(proxy)
                //.interceptor(Collections.singletonList(httpLoggingInterceptor))
                .apiHost("https://api.openai.com/")
                .build();
        CompletionResponse completions = openAiClient.completions("你是openAi吗");
        // Arrays.stream(completions.getChoices()).forEach(System.out::println);
        Arrays.stream(completions.getChoices()).forEach(System.out::println);
    }

}
