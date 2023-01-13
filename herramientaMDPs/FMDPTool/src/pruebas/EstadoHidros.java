/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import utileria.Listas;

/**
 *
 * @author Home
 */
public class EstadoHidros {

    int[] accion;
    int agenda[];

    // con lista de acciones
    public EstadoHidros(int[] a) {

        agenda = new int[a.length];
        accion = a;
        inicializarAgenda();
    }

    // con numero de acciones
    public EstadoHidros(int a) {
        accion = new int[a];
        for (int i = 0; i < a; i++) {
            accion[i] = i;
        }
        inicializarAgenda();
    }

    public void inicializarAgenda() {
        agenda = new int[accion.length];
        for (int i = 0; i < accion.length; i++) {
            agenda[i] = accion[i];
        }
    }

    public void eliminaElementoAgenda() {
        agenda = Listas.eliminaElem(0, agenda);
    }

    public void visualizaAgenda() {
        for (int i = 0; i < agenda.length; i++) {
            System.out.println(agenda[i]);
        }
    }

    // devuelve accion a ejecutar y la elimina de la lista
    public int accionAEjecutar() {

        if (agenda.length == 0) {
            inicializarAgenda();
        }
        int resp = agenda[0];
        eliminaElementoAgenda();
        return resp;
    }

    public boolean agendaVacia() {
        if (agenda.length == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String args[]) {

        int[] acciones = {0, 1, 2};

        EstadoHidros estado = new EstadoHidros(acciones);

        estado.visualizaAgenda();
        int acc = estado.accionAEjecutar();
        System.out.println("accion a ejecutar: " + acc);

        estado.eliminaElementoAgenda();
        estado.eliminaElementoAgenda();
        //   estado.eliminaElementoAgenda();
        estado.visualizaAgenda();
        System.out.println("agenda vacia ? " + estado.agendaVacia());
     //   estado.inicializarAgenda();
        //   estado.visualizaAgenda();
        // Math.random()
    }

}
