package com.github.danbel.mcpserverdemo;

import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

@Component
public class CalculatorMcp {

    @McpTool(
            name = "calculator_sum",
            description = "Складывает два числа и возрощает результат."
    )
    public Integer sum(
            @McpToolParam Integer a,
            @McpToolParam Integer b
    ) {
        return a + b;
    }
}
