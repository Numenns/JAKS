
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;

/**
 * Controlador de la aplicación JavaFX con WebView y actualización dinámica.
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private WebView webview;

    @FXML
    private Label label;
    private final int NUM_CONSULTORIOS = 3; 



    
    private List<Consultorio> consultorios;
    
    
    
    
     @FXML
     private void Simulacion(ActionEvent event) {
     WebEngine webEngine = webview.getEngine();

        // Timeline para actualizar la vista cada 2 segundos
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> actualizarVista(webEngine)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Primera carga de la tabla
        actualizarVista(webEngine);
     }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        consultorios = Arrays.asList(
            new Consultorio(1), new Consultorio(2), new Consultorio(3)
        );
    }

    /**
     * Actualiza la vista con el estado de los consultorios en la tabla.
     */
    private void actualizarVista(WebEngine webEngine) {
        // Construir el HTML con colores dinámicos
        String htmlContent = "<html><head><style>"
                + "table { width: 100%; border-collapse: collapse; }"
                + "th, td { border: 1px solid black; padding: 8px; text-align: center; }"
                + "th { background-color: #f2f2f2; }"
                + "</style></head><body>"
                + "<h2>Estado de Consultorios</h2>"
                + generarHTML()
                + "</body></html>";

        // Cargar el contenido en WebView
        webEngine.loadContent(htmlContent, "text/html");
    }

    /**
     * Genera la tabla HTML con el estado de los consultorios.
     */
    private String generarHTML() {
        StringBuilder tablaHTML = new StringBuilder();
        tablaHTML.append("<table>")
                .append("<tr><th>Consultorio</th><th>Estado</th><th>Emergencia</th><th>Tiempo</th><th>Nivel Prioridad</th><th>ID</th></tr>");

        for (Consultorio c : consultorios) {
            String bgColor = c.estaOcupado() ? "red" : "green";
            tablaHTML.append("<tr style='background-color: ").append(bgColor).append(";'>")
                    .append("<td>").append(c.getId()).append("</td>")
                    .append("<td>").append(c.estaOcupado() ? "Ocupado" : "Disponible").append("</td>")
                    .append("<td>").append(c.getEmergencia() != null ? c.getEmergencia().tEmergencia : "-").append("</td>")
                    .append("<td>").append(c.getEmergencia() != null ? c.getEmergencia().tiempo : "-").append("</td>")
                    .append("<td>").append(c.getEmergencia() != null ? c.getEmergencia().nPrioridad : "-").append("</td>")
                    .append("<td>").append(c.getEmergencia() != null ? c.getEmergencia().id : "-").append("</td>")
                    .append("</tr>");
        }

        tablaHTML.append("</table>");
        return tablaHTML.toString();
    }
}

/**
 * Clase que representa un consultorio.
 */
class Consultorio {
    private int id;
    private Tabla emergencia;

    public Consultorio(int id) {
        this.id = id;
        this.emergencia = null;
    }

    public int getId() {
        return id;
    }

    public boolean estaOcupado() {
        return emergencia != null;
    }

    public Tabla getEmergencia() {
        return emergencia;
    }

    public void asignarEmergencia(Tabla emergencia) {
        this.emergencia = emergencia;
    }

    public void liberarConsultorio() {
        this.emergencia = null;
    }
}

/**
 * Clase que representa una emergencia en la tabla.
 */
class Tabla {
    int nPrioridad;
    String tEmergencia;
    int tiempo;
    int id;

    public Tabla(int nPrioridad, String tEmergencia, int tiempo, int id) {
        this.nPrioridad = nPrioridad;
        this.tEmergencia = tEmergencia;
        this.tiempo = tiempo;
        this.id = id;
    }
}