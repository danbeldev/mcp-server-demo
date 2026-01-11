package com.github.danbel.mcpserverdemo;

import org.springaicommunity.mcp.annotation.McpResource;
import org.springframework.stereotype.Component;

@Component
public class PerepletResources {

    @McpResource(
            uri = "pereplet://policy/house-rules",
            name = "PEREPLËT House Rules",
            description = "Правила посещения PEREPLËT (кратко)"
    )
    public String houseRules() {
        return """
                PEREPLËT — креативное пространство для отдыха и развлечений.
                Коротко о правилах:
                1) Уважайте гостей и команду.
                2) Бережно относитесь к оборудованию и мебели.
                3) Бронь держим ограниченное время — уточняйте у администратора.
                4) Можно со своей едой и напитками (если у вас так принято — оставь/убери эту строку).
                """;
    }

    @McpResource(
            uri = "pereplet://info/zones",
            name = "PEREPLËT Zones",
            description = "Описание зон (пример)"
    )
    public String zones() {
        return """
                Зоны PEREPLËT:
                - Большой зал: медиа-экран, диваны.
                - VIP-зона: более приватно.
                - Настольные игры: отдельная зона/столы.
                """;
    }
}
