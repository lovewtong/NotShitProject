package com.Zlibrary.entity.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: OpenAi
 * @description: TODO 类描述
 * @author: sy
 * @date: 2023-03-22
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAi {

    String id;

    String name;

    String desc;

    String model;

    // 提示模板
    String prompt;

    // 创新采样
    Double temperature;

    // 情绪采样
    Double topP;

    // 结果条数
    Double n = 1d;

    // 频率处罚系数
    Double frequencyPenalty;

    // 重复处罚系数
    Double presencePenalty;

    // 停用词
    String stop;


}
