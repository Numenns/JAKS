
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package controller;

import cola.Cola;
import java.net.URL;
import java.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import model.CrearEmergencia;

public class FXMLDocumentController implements Initializable {

    @FXML
    private WebView webview; 
    @FXML
    private WebView webdos;  

    @FXML
    private TextArea textarea1;

    
    
    @FXML
    private Label label;

    private final int NUM_CONSULTORIOS = 3;
    private CrearEmergencia crearEmergencia;
    private Timeline timeline;

    private List<Consultorio> consultorios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        consultorios = Arrays.asList(
                new Consultorio(1),
                new Consultorio(2),
                new Consultorio(3)
        );
    }

    @FXML
    private void finalizar(ActionEvent event) {
        if (crearEmergencia != null) {
            crearEmergencia.detener();
            System.out.println("Generación de emergencias detenida.");
        }
    }

   
    
    @FXML
    private void Generar(ActionEvent event) {
        new Thread(new CrearEmergencia(new Cola<>())).start();
        System.out.println("Generando emergencias...");
    }@FXML
    private void Simulacion(ActionEvent event) {
        WebEngine webEngine1 = webview.getEngine();
        WebEngine webEngine2 = webdos.getEngine();

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            actualizarVista(webEngine1, "Estado de Consultorios");
            actualizarVista(webEngine2, "Listado de emergencias");
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        actualizarVista(webEngine1, "Estado de Consultorios");
        actualizarVista(webEngine2, "Listado de emergencias");

        if (crearEmergencia == null || !Thread.currentThread().isAlive()) {
            crearEmergencia = new CrearEmergencia(new Cola<>());
            new Thread(crearEmergencia).start();
            System.out.println("Generando emergencias...");
        }
    }

    private void actualizarVista(WebEngine webEngine, String titulo) {
        boolean esTablaEmergencias = titulo.equals("Listado de emergencias");
        String htmlContent = generarHTML(titulo, esTablaEmergencias);
        webEngine.loadContent(htmlContent, "text/html");
    }

    private String generarHTML(String titulo, boolean esTablaEmergencias) {
        StringBuilder html = new StringBuilder();
        html.append("<html><head><style>")
            .append("table { width: 100%; border-collapse: collapse; }")
            .append("th, td { border: 1px solid black; padding: 8px; text-align: center; }")
            .append("th { background-color: #f2f2f2; }");

        if (esTablaEmergencias) {
            html.append("th:nth-child(1), td:nth-child(1) { width: 25%; }") // Emergencia
                .append("th:nth-child(2), td:nth-child(2) { width: 20%; }") // Tiempo
                .append("th:nth-child(3), td:nth-child(3) { width: 15%; }") // NivelPrioridad (más pequeño)
                .append("th:nth-child(4), td:nth-child(4) { width: 40%; }"); // ID (más grande)
        } else {
            html.append("th, td { width: calc(100% / 6); }");  // Todas iguales en la primera tabla
        }

        html.append("</style></head><body>")
            .append("<h2>").append(titulo).append("</h2>")
            .append("<table>");

        if (esTablaEmergencias) {
            html.append("<tr><th>Emergencia</th><th>Tiempo</th><th>NivelPrioridad</th><th>ID</th></tr>");

            int filas = 0;
            for (Consultorio c : consultorios) {
                if (c.getEmergencia() != null && filas < 5) {
                    html.append("<tr>")
                        .append("<td>").append(c.getEmergencia().tEmergencia).append("</td>")
                        .append("<td>").append(c.getEmergencia().tiempo).append("</td>")
                        .append("<td>").append(c.getEmergencia().nPrioridad).append("</td>")
                        .append("<td>").append(c.getEmergencia().id).append("</td>")
                        .append("</tr>");
                    filas++;
                }
            }

            while (filas < 5) {
                html.append("<tr>")
                    .append("<td>-</td>")
                    .append("<td>-</td>")
                    .append("<td>-</td>")
                    .append("<td>-</td>")
                    .append("</tr>");
                filas++;
            }

        } else {
            html.append("<tr><th>Consultorio</th><th>Estado</th><th>Emergencia</th><th>Tiempo</th><th>NivelPrioridad</th><th>ID</th></tr>");

            for (Consultorio c : consultorios) {
                String bgColor = c.estaOcupado() ? "red" : "green";
                html.append("<tr style='background-color:").append(bgColor).append(";'>")
                    .append("<td>").append(c.getId()).append("</td>")
                    .append("<td>").append(c.estaOcupado() ? "Ocupado" : "Disponible").append("</td>")
                    .append("<td>").append(c.getEmergencia() != null ? c.getEmergencia().tEmergencia : "-").append("</td>")
                    .append("<td>").append(c.getEmergencia() != null ? c.getEmergencia().tiempo : "-").append("</td>")
                    .append("<td>").append(c.getEmergencia() != null ? c.getEmergencia().nPrioridad : "-").append("</td>")
                    .append("<td>").append(c.getEmergencia() != null ? c.getEmergencia().id : "-").append("</td>")
                    .append("</tr>");
            }
        }

        html.append("</table></body></html>");
        return html.toString();
    }}

/**
 * Clase Consultorio - representa un consultorio con una emergencia asignada o libre.
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
 * Clase Tabla - representa la información de una emergencia.
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