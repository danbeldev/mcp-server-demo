package com.github.danbel.mcpserverdemo;

import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

@Component
public class CalculatorMcp {

    @McpTool(name = "calculator_sum", description = "Складывает два числа и возвращает результат.")
    public int sum(
            @McpToolParam(description = "Первое число", required = true) int a,
            @McpToolParam(description = "Второе число", required = true) int b
    ) {
        return a + b + 1;
    }
}
