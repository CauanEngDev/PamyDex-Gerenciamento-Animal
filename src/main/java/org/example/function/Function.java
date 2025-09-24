package org.example.function;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.example.model.Identifiable;
import org.example.database.Pamydex;
import org.example.model.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.*;

public class Function {
    public <T extends Identifiable> UUID uniqueId(List<T> list){
        UUID uuid;
        List<UUID> listId = list.stream()
                .map(item -> item.getId())
                .collect(Collectors.toList());

        do {
            uuid = UUID.randomUUID();;
        } while (listId.contains(uuid));

        return uuid;
    }

    public void saveInfo() {
        XStream xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);

        Pamydex data = new Pamydex();

        xstream.alias("PamyDex", Pamydex.class);
        xstream.alias("Pamgyms", List.class);
        xstream.alias("PamGym", PamGym.class);
        xstream.alias("PamMasters", List.class);
        xstream.alias("PamMaster", PamMaster.class);
        xstream.alias("PamNimals", List.class);
        xstream.alias("PamNimal", PamNimal.class);
        xstream.alias("Address", Address.class);

        File arqXml = new File("PamyDex.xml");
        try (FileWriter writer = new FileWriter(arqXml)) {
            xstream.toXML(data, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo");
        }
    }

    public void catchInfo(){
        XStream xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);

        Pamydex pamyDex;
        File arqXml = new File("PamyDex.xml");
        try (FileReader reader = new FileReader(arqXml)){
            pamyDex =  (Pamydex) xstream.fromXML(reader);
        } catch (IOException e) {
            System.out.println("Erro ao puxar arquivo");
        }
    }
}
