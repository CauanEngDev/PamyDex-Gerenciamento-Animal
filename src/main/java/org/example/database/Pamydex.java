package org.example.database;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.example.model.*;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Pamydex {
    private static List<PamNimal> pamNimals = new ArrayList<>();
    private static List<PamGym> pamGyms = new ArrayList<>();
    private static List<PamMaster> pamMasters = new ArrayList<>();

    public void saveInfo(List<Register> pamyDex){
        XStream  xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);

        xstream.alias("PamyDex", List.class);
        xstream.alias("Pamgyms", List.class);
        xstream.alias("PamGym", PamGym.class);
        xstream.alias("PamMasters", List.class);
        xstream.alias("PamMaster",  PamMaster.class);
        xstream.alias("PamNimals", List.class);
        xstream.alias("PamNimal",  PamNimal.class);
        xstream.alias("address", Address.class);

        File arqXml = new File("PamyDex.xml");
        try (FileWriter writer = new FileWriter(arqXml)) {
            xstream.toXML(pamyDex, writer);
        } catch (java.io.IOException e) {
//            e.getMessage()System.out.println("Erro ao salvar arquivo");
        }
    }
}
