package org.example.commons;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.example.database.Pamydex;
import org.example.model.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.*;


public class Function {
    public static <T extends Identifiable> UUID uniqueId(List<T> list){
        UUID uuid;
        List<UUID> listId = list.stream()
                .map(item -> item.getId())
                .collect(Collectors.toList());

        do {
            uuid = UUID.randomUUID();
        } while (listId.contains(uuid));

        return uuid;/;
    }

    public static void saveInfo() {
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

    public static void catchInfo(){
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

    public static @Nullable String chooseCurrentStatus(){
        Set<String> options = new HashSet<>();
        options.add("1");
        options.add("2");
        options.add("3");
        options.add("0");

        String option;

        do {
            option = IOFunctions.ask("Qual status o animal se encontra? ");

            switch (option) {
                case "1" -> {
                    return "Em Observação";
                }
                case "2" -> {
                    return "Disponível para Adoção";
                }
                case "3" -> {
                    return "Em Tratamento";
                }
                case "0" -> { continue; }
                default -> {
                    IOFunctions.printl("Digite uma opção válida!");
                }
            }
        } while (!options.contains(option));
        return null;
    }

    public static @Nullable String createPhone(){
        String phone;

        do{
            phone = IOFunctions.ask("Digite o telefone do PamMaster: ");

            if (phone.isEmpty()) return null;

            if (!(phone.matches("[0-9]+")) || phone.length() != 11) IOFunctions.printl("Digite apenas números no formato xxxxxxxxxxx");

        } while (!(phone.matches("[0-9]+")) || phone.length() != 11);

        return phone;
    }
}
