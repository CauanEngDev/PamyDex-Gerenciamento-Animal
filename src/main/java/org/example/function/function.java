package org.example.function;

import java.util.List;
import java.util.UUID;
import java.util.stream.*;

public class function {
    public UUID uniqueId(List<Object> list){
        UUID uuid = UUID.randomUUID();

        Stream<UUID> listId = list.stream()
                .map(list::getId)
                .collect(ToList());
    }
}
