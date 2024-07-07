package com.mycompany.proyectoso;
import Controlador.ControladorEjecucion;
import Gui.GestionarListados;


/*
/**
 *
 * @author Jose Daniel
*/
public class ProyectoSO {

public static GestionarListados Glistados;

    public static void main(String[] args) {
        ControladorEjecucion controladorEjecucion = new ControladorEjecucion();
        Glistados = new GestionarListados(controladorEjecucion);
        Glistados.setVisible(true);
    }
}
