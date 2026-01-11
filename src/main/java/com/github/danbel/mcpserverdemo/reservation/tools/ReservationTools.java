package com.github.danbel.mcpserverdemo.reservation.tools;

import org.springaicommunity.mcp.annotation.McpMeta;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ReservationTools {

    // key = userId
    private final Map<Integer, Reservation> activeReservations = new HashMap<>();

    @McpTool(
            name = "reservation_create",
            description = "Создать активную бронь. У пользователя может быть только одна активная бронь."
    )
    public Map<String, Object> create(
            @McpToolParam(description = "Дата/время брони строкой, например '2026-01-11 20:00'", required = true)
            String datetime,

            @McpToolParam(description = "Количество гостей", required = true)
            int persons,

            @McpToolParam(description = "Зона (опционально)", required = false)
            String zone,

            @McpToolParam(description = "Комментарий (опционально)", required = false)
            String comment,

            McpMeta meta
    ) {
        int userId = (int) meta.get("user_id");

        if (activeReservations.containsKey(userId)) {
            return Map.of(
                    "ok", false,
                    "error_code", "ACTIVE_RESERVATION_EXISTS",
                    "message", "У вас уже есть активная бронь."
            );
        }

        var r = new Reservation(userId, datetime, persons, zone, comment, "ACTIVE");
        activeReservations.put(userId, r);

        return Map.of(
                "ok", true,
                "reservation", r
        );
    }

    @McpTool(
            name = "reservation_get_current",
            description = "Получить текущую активную бронь пользователя."
    )
    public Map<String, Object> getCurrent(McpMeta meta) {
        int userId = (int) meta.get("user_id");

        var r = activeReservations.get(userId);
        if (r == null) {
            return Map.of(
                    "ok", false,
                    "message", "Активной брони нет."
            );
        }

        return Map.of(
                "ok", true,
                "reservation", r
        );
    }

    @McpTool(
            name = "reservation_get_all",
            description = "Получить все активные брони (тестовый метод)."
    )
    public Map<String, Object> getAll() {
        return Map.of(
                "ok", true,
                "count", activeReservations.size(),
                "reservations", activeReservations.values()
        );
    }

    // простейшая модель
    public record Reservation(
            int userId,
            String datetime,
            int persons,
            String zone,
            String comment,
            String status
    ) {}
}
