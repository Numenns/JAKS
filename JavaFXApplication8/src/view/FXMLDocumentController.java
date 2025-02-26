/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package view;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author josep
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private WebView webview;
    
    @FXML
    private Label label;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine webEngine = webview.getEngine(); // Obtener el WebEngine
        String html = generarHTML(); // Generar la tabla en HTML
        webEngine.loadContent(html, "text/html"); // Cargar en WebView
    }

    // Método para generar el contenido HTML de la tabla
    private String generarHTML() {
        
       

        StringBuilder tablaHTML = new StringBuilder();
        tablaHTML.append("<html><head><style>")
                .append("table { width: 100%; border-collapse: collapse; }")
                .append("th, td { border: 1px solid black; padding: 8px; text-align: left; }")
                .append("th { background-color: #f2f2f2; }")
                .append("</style></head><body>")
                .append("<table>")
                .append("<tr><th>Prioridad</th><th>Emergencia</th><th>Tiempo</th><th>ID</th></tr>");

       

        tablaHTML.append("</table></body></html>");
        return tablaHTML.toString();
    }
}

// Clase para manejar los datos de la tabla
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

    @Override
    public String toString() {
        return "<tr>"
                + "<td>" + nPrioridad + "</td>"
                + "<td>" + tEmergencia + "</td>"
                + "<td>" + tiempo + "</td>"
                + "<td>" + id + "</td>"
                + "</tr>";
    }
}