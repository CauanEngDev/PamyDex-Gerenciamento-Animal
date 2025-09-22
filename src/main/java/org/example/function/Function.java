package org.example.function;

import org.example.model.Register;

import java.util.List;
import java.util.UUID;
import java.util.stream.*;

public class Function {
    public UUID uniqueId(List<Register> list){
        UUID uuid;

        List<UUID> listId = list.stream()
                .map(Register::getId)
                .collect(Collectors.toList());

        do {
            uuid = UUID.randomUUID();;
        } while (listId.contains(uuid));

        return uuid;
    }
}
