package com.cauanengdev.pamydex.dtos;

import java.util.UUID;

public class SpecieDTO {
    public record Request(String name) {}
    public record Response(UUID id, String name) {}
}
