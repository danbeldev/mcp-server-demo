package com.github.danbel.mcpserverdemo;

import io.modelcontextprotocol.spec.McpSchema;
import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpPrompt;
import org.springaicommunity.mcp.context.McpSyncRequestContext;
import org.springframework.stereotype.Component;

@Component
public class PerepletPrompts {

    @McpPrompt(
            name = "pereplet-reply-style",
            description = "Стиль ответа администратора PEREPLËT (коротко, дружелюбно, по-русски)"
    )
    public McpSchema.GetPromptResult perepletReplyStyle(
            McpSyncRequestContext context,
            @McpArg(name = "user_message", description = "Сообщение гостя", required = true) String userMessage
    ) {
        return new McpSchema.GetPromptResult(
                "PEREPLËT reply style",
                java.util.List.of(
                        new McpSchema.PromptMessage(
                                McpSchema.Role.ASSISTANT,
                                new McpSchema.TextContent("""
                                        Ты администратор PEREPLËT (Санкт-Петербург).
                                        Отвечай по-русски, дружелюбно и кратко.
                                        Если не хватает данных — задай 1 уточняющий вопрос.
                                        Если вопрос про бронь — уточни: дата/время, кол-во гостей, зона, телефон.
                                        """)
                        ),
                        new McpSchema.PromptMessage(
                                McpSchema.Role.USER,
                                new McpSchema.TextContent(userMessage)
                        )
                )
        );
    }
}

