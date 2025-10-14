package org.example.commons;

import static org.example.database.Pamydex.*;
import org.example.database.PamydexTransferer;
import org.jetbrains.annotations.Nullable;

import org.example.model.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.*;

/**
 * Classe de funções usadas em algumas partes do código
 */
public class Function {
    /**
     * Função que cria um novo id com base nos já existentes
     * @param list lista de alguma instancia para ser analisada
     * @return retorna um novo id
     * @param <T> tipo da instacia
     */
    public static <T extends Identifiable> UUID uniqueId(List<T> list){
        UUID uuid;
        List<UUID> listId = list.stream()
                .map(Identifiable::getId)
                .toList();

        do {
            uuid = UUID.randomUUID();
        } while (listId.contains(uuid));

        return uuid;
    }

    /**
     * Função para serializar as informações salvas nas listas dentro de um xml
     */
    public static void saveInfo() {
        XStream xstream = new XStream(new PureJavaReflectionProvider());
        xstream.addPermission(AnyTypePermission.ANY);

        PamydexTransferer data = new PamydexTransferer();
        data.setPamGyms(PAMGYMS);
        data.setPamMasters(PAMMASTERS);
        data.setPamNimals(PAMNIMALS);


        xstream.alias("PamyDex", PamydexTransferer.class);

        xstream.aliasField("PamGyms", PamydexTransferer.class, "pamGyms");
        xstream.aliasField("PamMasters", PamydexTransferer.class, "pamMasters");
        xstream.aliasField("PamNimals", PamydexTransferer.class, "pamNimals");

        xstream.alias("PamGym", PamGym.class);
        xstream.alias("PamMaster", PamMaster.class);
        xstream.alias("PamNimal", PamNimal.class);
        xstream.alias("Address", Address.class);

        File arqXml = new File("data/PamyDex.xml");
        try (FileWriter writer = new FileWriter(arqXml)) {
            xstream.toXML(data, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo");
        }
    }

    /**
     * Desserializa as informações quando o programa é aberto
     */
    public static void catchInfo(){
        XStream xstream = new XStream(new PureJavaReflectionProvider());
        xstream.addPermission(AnyTypePermission.ANY);

        xstream.alias("PamyDex", PamydexTransferer.class);

        xstream.aliasField("PamGyms", PamydexTransferer.class, "pamGyms");
        xstream.aliasField("PamMasters", PamydexTransferer.class, "pamMasters");
        xstream.aliasField("PamNimals", PamydexTransferer.class, "pamNimals");

        xstream.alias("PamGym", PamGym.class);
        xstream.alias("PamMaster", PamMaster.class);
        xstream.alias("PamNimal", PamNimal.class);
        xstream.alias("Address", Address.class);

        File arqXml = new File("data/PamyDex.xml");
        try (FileReader reader = new FileReader(arqXml)){
            PamydexTransferer loadData = (PamydexTransferer) xstream.fromXML(reader);

            PAMMASTERS.clear();
            PAMGYMS.clear();
            PAMNIMALS.clear();

            if (loadData.getPamMasters() != null) {
                PAMMASTERS.addAll(loadData.getPamMasters());
            }
            if (loadData.getPamGyms() != null) {
                PAMGYMS.addAll(loadData.getPamGyms());
            }
            if (loadData.getPamNimals() != null) {
                PAMNIMALS.addAll(loadData.getPamNimals());
            }
        } catch (IOException e) {
            saveInfo();
        }
    }

    /**
     * Função para escolher o status do PamNimal
     * @return
     */
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
                case "0" -> { }
                default -> IOFunctions.printl("Digite uma opção válida!");
            }
        } while (!options.contains(option));
        return null;
    }

    /**
     * Função para validar e criar um novo telefone
     * @return
     */
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
