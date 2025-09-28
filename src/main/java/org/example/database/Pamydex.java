package org.example.database;

import org.example.model.*;

import java.util.ArrayList;
import java.util.List;

public class Pamydex {
    public static List<PamNimal> PAMNIMALS = new ArrayList<>();
    public static List<PamGym> PAMGYMS = new ArrayList<>();
    public static List<PamMaster> PAMMASTERS = new ArrayList<>();
    public static PamMaster GHOSTPAMMASTER = new PamMaster(null);
}
