package it.reloia.tecnoutils.httpserver;

import it.reloia.tecnoutils.httpserver.data.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticMarkerSets {
    private MarkersDataSet publicStructures;
    private MarkersDataSet privateStructures;
    
    public MarkersDataSet getPublicStructures() {
        if (publicStructures == null) {
            Map<String, Marker> markers = new HashMap<>();
            markers.put("central-square", new Marker(
                    "Piazza Centrale",
                    new MarkerColor(12, 251, 191, 1),
                    new MarkerColor(12, 251, 191, 0.3f),
                    List.of(
                            new MarkerShapeVertex(-581, -53),
                            new MarkerShapeVertex(-460, -53),
                            new MarkerShapeVertex(-460, 68),
                            new MarkerShapeVertex(-581, 68)
                    ),
                    new MarkerPosition(0, 0, 0)
            ));
            markers.put("legione-carabinieri", new Marker(
                    "Legione Carabinieri \"Chiaffredo Bergia\"",
                    new MarkerColor(255, 0, 0, 1),
                    new MarkerColor(255, 0, 0, 0.2f),
                    List.of(
                            new MarkerShapeVertex(-665, 182),
                            new MarkerShapeVertex(-610, 182),
                            new MarkerShapeVertex(-610, 230),
                            new MarkerShapeVertex(-665, 230)
                    ),
                    new MarkerPosition(-665, 65, 181)
            ));
            markers.put("palazzo-sant-ayama", new Marker(
                    "Palazzo Sant'Ayama",
                    new MarkerColor(0, 255, 255, 1),
                    new MarkerColor(0, 255, 255, 0.2f),
                    List.of(
                            new MarkerShapeVertex(-757, 188),
                            new MarkerShapeVertex(-704, 188),
                            new MarkerShapeVertex(-704, 241),
                            new MarkerShapeVertex(-757, 241)
                    ),
                    new MarkerPosition(-757, 65, 188)
            ));
            markers.put("palazzo-di-giustizia", new Marker(
                    "Palazzo di Giustizia",
                    new MarkerColor(0, 255, 255, 1),
                    new MarkerColor(0, 255, 255, 0.2f),
                    List.of(
                            new MarkerShapeVertex(-790, 99),
                            new MarkerShapeVertex(-681, 99),
                            new MarkerShapeVertex(-681, 186),
                            new MarkerShapeVertex(-790, 186)
                    ),
                    new MarkerPosition(-790, 65, 99)
            ));
            markers.put("banca", new Marker(
                    "Banca",
                    new MarkerColor(255, 215, 0, 1),
                    new MarkerColor(255, 215, 0, 0.2f),
                    List.of(
                            new MarkerShapeVertex(-688, -49),
                            new MarkerShapeVertex(-608, -49),
                            new MarkerShapeVertex(-608, 65),
                            new MarkerShapeVertex(-688, 65)
                    ),
                    new MarkerPosition(-688, 65, -49)
            ));
            markers.put("scuola", new Marker(
                    "Scuola",
                    new MarkerColor(0, 100, 0, 1),
                    new MarkerColor(0, 100, 0, 0.2f),
                    List.of(
                            new MarkerShapeVertex(-576, 95),
                            new MarkerShapeVertex(-466, 95),
                            new MarkerShapeVertex(-466, 144),
                            new MarkerShapeVertex(-576, 144)
                    ),
                    new MarkerPosition(-576, 65, 95)
            ));
            markers.put("caserma-vigili-del-fuoco", new Marker(
                    "Caserma Vigili del Fuoco \"Alberto Giombini\"",
                    new MarkerColor(255, 69, 0, 1),
                    new MarkerColor(255, 69, 0, 0.2f),
                    List.of(
                            new MarkerShapeVertex(-437, 162),
                            new MarkerShapeVertex(-399, 162),
                            new MarkerShapeVertex(-399, 191),
                            new MarkerShapeVertex(-404, 191),
                            new MarkerShapeVertex(-404, 238),
                            new MarkerShapeVertex(-436, 238)
                    ),
                    new MarkerPosition(-437, 65, 162)
            ));
            markers.put("municipio", new Marker(
                    "Municipio",
                    new MarkerColor(255, 215, 0, 1),
                    new MarkerColor(255, 215, 0, 0.25f),
                    List.of(
                            new MarkerShapeVertex(-582, -123),
                            new MarkerShapeVertex(-458, -123),
                            new MarkerShapeVertex(-458, -74),
                            new MarkerShapeVertex(-582, -74)
                    ),
                    new MarkerPosition(-582, 0, -123)
            ));
            markers.put("ospedale", new Marker(
                    "Ospedale",
                    new MarkerColor(255, 0, 0, 1),
                    new MarkerColor(255, 0, 0, 0.25f),
                    List.of(
                            new MarkerShapeVertex(-582, -181),
                            new MarkerShapeVertex(-458, -181),
                            new MarkerShapeVertex(-458, -134),
                            new MarkerShapeVertex(-582, -134)
                    ),
                    new MarkerPosition(-582, 0, -181)
            ));
            markers.put("questura", new Marker(
                    "Questura",
                    new MarkerColor(0, 0, 255, 1),
                    new MarkerColor(0, 0, 255, 0.25f),
                    List.of(
                            new MarkerShapeVertex(-660, -181),
                            new MarkerShapeVertex(-602, -181),
                            new MarkerShapeVertex(-602, -73),
                            new MarkerShapeVertex(-660, -73)
                    ),
                    new MarkerPosition(-660, 0, -181)
            ));
            markers.put("chiesa", new Marker(
                    "Chiesa",
                    new MarkerColor(255, 255, 255, 1),
                    new MarkerColor(255, 255, 255, 0.25f),
                    List.of(
                            new MarkerShapeVertex(-438, -181),
                            new MarkerShapeVertex(-379, -181),
                            new MarkerShapeVertex(-379, -74),
                            new MarkerShapeVertex(-438, -74)
                    ),
                    new MarkerPosition(-438, 0, -181)
            ));
            markers.put("stato-maggiore", new Marker(
                    "Stato Maggiore della Difesa",
                    new MarkerColor(0, 128, 0, 1),
                    new MarkerColor(0, 128, 0, 0.25f),
                    List.of(
                            new MarkerShapeVertex(-666, 144),
                            new MarkerShapeVertex(-609, 144),
                            new MarkerShapeVertex(-609, 182),
                            new MarkerShapeVertex(-666, 182)
                    ),
                    new MarkerPosition(-666,0, 144)
            ));
            markers.put("guardia-di-finanza", new Marker(
                    "Guardia di Finanza",
                    new MarkerColor(218, 165, 32, 1),
                    new MarkerColor(218, 165, 32, 0.25f),
                    List.of(
                            new MarkerShapeVertex(-582, 203),
                            new MarkerShapeVertex(-496, 203),
                            new MarkerShapeVertex(-496, 243),
                            new MarkerShapeVertex(-582, 243)
                    ),
                    new MarkerPosition(-582,0, 203)
            ));
            markers.put("farmacia", new Marker(
                    "Farmacia",
                    new MarkerColor(144, 238, 144, 1),
                    new MarkerColor(144, 238, 144, 0.25f),
                    List.of(
                            new MarkerShapeVertex(-575, -161),
                            new MarkerShapeVertex(-567, -161),
                            new MarkerShapeVertex(-567, -158),
                            new MarkerShapeVertex(-560, -157),
                            new MarkerShapeVertex(-560, -154),
                            new MarkerShapeVertex(-549, -154),
                            new MarkerShapeVertex(-549, -141),
                            new MarkerShapeVertex(-560, -141),
                            new MarkerShapeVertex(-560, -143),
                            new MarkerShapeVertex(-568, -143),
                            new MarkerShapeVertex(-568, -145),
                            new MarkerShapeVertex(-575, -145)
                    ),
                    new MarkerPosition(-575, 0, -161)
            ));
            
            publicStructures = new MarkersDataSet("Strutture Pubbliche", markers);
        }
        
        return publicStructures;
    }
    
    public MarkersDataSet getPrivateStructures() {
        if (privateStructures == null) {
            Map<String, Marker> markers = new HashMap<>();
            markers.put("ikea", new Marker(
                    "IKEA",
                    new MarkerColor(0, 87, 173, 1),
                    new MarkerColor(0, 87, 173, 0.2f),
                    List.of(
                            new MarkerShapeVertex(-432, 95),
                            new MarkerShapeVertex(-382, 95),
                            new MarkerShapeVertex(-382, 145),
                            new MarkerShapeVertex(-432, 145)
                    ),
                    new MarkerPosition(-432, 65, 95)
            ));
            markers.put("case-popolari", new Marker(
                    "Case Popolari",
                    new MarkerColor(0, 127, 255, 1),
                    new MarkerColor(0, 127, 255, 0.2f),
                    List.of(
                            new MarkerShapeVertex(-704, 193),
                            new MarkerShapeVertex(-671, 193),
                            new MarkerShapeVertex(-671, 240),
                            new MarkerShapeVertex(-704, 240)
                    ),
                    new MarkerPosition(-704, 65, 193)
            ));
            markers.put("galleria-d-baffoni", new Marker(
                    "Galleria \"D. Baffoni\"",
                    new MarkerColor(139, 69, 19, 1),
                    new MarkerColor(139, 69, 19, 0.25f),
                    List.of(
                            new MarkerShapeVertex(-433, -49),
                            new MarkerShapeVertex(-384, -49),
                            new MarkerShapeVertex(-384, 64),
                            new MarkerShapeVertex(-433, 64)
                    ),
                    new MarkerPosition(-433, 0, -49)
            ));
            markers.put("tasty-burger", new Marker(
                    "Tasty Burger",
                    new MarkerColor(198, 146, 20, 1),
                    new MarkerColor(198, 146, 20, 0.25f),
                    List.of(
                            new MarkerShapeVertex(-373, -117),
                            new MarkerShapeVertex(-331, -117),
                            new MarkerShapeVertex(-331, -74),
                            new MarkerShapeVertex(-373, -74)
                    ),
                    new MarkerPosition(-373, 0, -117)
            ));
            markers.put("bottega-alfieri", new Marker(
                    "Bottega Alfieri",
                    new MarkerColor(255, 0, 255, 1),
                    new MarkerColor(255, 0, 255, 0.25f),
                    List.of(
                            new MarkerShapeVertex(-493, 156),
                            new MarkerShapeVertex(-459, 156),
                            new MarkerShapeVertex(-459, 196),
                            new MarkerShapeVertex(-493, 196)
                    ),
                    new MarkerPosition(-493, 0, 156)
            ));
            markers.put("purissima", new Marker(
                    "Purissima",
                    new MarkerColor(173, 216, 230, 1),
                    new MarkerColor(173, 216, 230, 0.3f),
                    List.of(
                            new MarkerShapeVertex(-428, -44),
                            new MarkerShapeVertex(-389, -44),
                            new MarkerShapeVertex(-389, -5),
                            new MarkerShapeVertex(-428, -5)
                    ),
                    new MarkerPosition(-428, 65, -44)
            ));
            markers.put("giornalaio", new Marker(
                    "Giornalaio / Biblioteca",
                    new MarkerColor(173, 216, 230, 1),
                    new MarkerColor(173, 216, 230, 0.3f),
                    List.of(
                            new MarkerShapeVertex(-428, 20),
                            new MarkerShapeVertex(-389, 20),
                            new MarkerShapeVertex(-389, 59),
                            new MarkerShapeVertex(-428, 59)
                    ),
                    new MarkerPosition(-428, 65, 20)
            ));
            markers.put("euronyx", new Marker(
                    "Euronyx",
                    new MarkerColor(255, 102, 102, 1),
                    new MarkerColor(255, 102, 102, 0.25f),
                    List.of(
                            new MarkerShapeVertex(-522, 158),
                            new MarkerShapeVertex(-494, 158),
                            new MarkerShapeVertex(-494, 191),
                            new MarkerShapeVertex(-522, 191)
                    ),
                    new MarkerPosition(-522, 0, 158)
            ));
            markers.put("agenzia-consulenze", new Marker(
                    "Agenzia Consulenze",
                    new MarkerColor(0, 191, 255, 1),
                    new MarkerColor(0, 191, 255, 0.25f),
                    List.of(
                            new MarkerShapeVertex(-582, 155),
                            new MarkerShapeVertex(-552, 155),
                            new MarkerShapeVertex(-552, 197),
                            new MarkerShapeVertex(-582, 197)
                    ),
                    new MarkerPosition(-582, 0, 155)
            ));
            privateStructures = new MarkersDataSet("Strutture Private", markers);
        }

        return privateStructures;
    }
}
