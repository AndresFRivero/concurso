/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concurso.main;

import com.concurso.datos.JugadorJDBC;
import com.concurso.datos.PreguntaJDBC;
import com.concurso.entidades.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author AndresFRivero
 */
public class Main {

    public static int menu() {
        int opcionMenu = Integer.parseInt(JOptionPane.showInputDialog(
                "===== CONCURSO PREGUNTAS Y RESPUESTAS - MENU == == = \n"
                + "1. Crear una pregunta \n" + "2. Iniciar Juego \n" + "3. Ganadores \n" + "4. Salir\n\nSeleccione una opción"));
        return opcionMenu;
    }

    public static void main(String[] args) {
        PreguntaJDBC preguntaJDBC = new PreguntaJDBC();
        JugadorJDBC jugadorJDBC = new JugadorJDBC();
        int opcion;
        do {
            opcion = menu();
            switch (opcion) {
                case 1:
                    boolean e = false;
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID de la pregunta"));
                    List<Pregunta> listaPregunta = preguntaJDBC.select();
                    for (Pregunta pregunta : listaPregunta) {
                        if (pregunta.getId() == id) {
                            e = true;
                            break;
                        }
                    }
                    if (!e) {
                        String enunciado = JOptionPane.showInputDialog("Ingrese enunciado de la pregunta");
                        int nivel = Integer.parseInt(JOptionPane.showInputDialog("Ingrese nivel de dificultad en que estará la pregunta (1 al 5)"));
                        String opcion1 = JOptionPane.showInputDialog("Ingrese 1/4 opción de la pregunta (a)");
                        String opcion2 = JOptionPane.showInputDialog("Ingrese 2/4 opción de la pregunta (b)");
                        String opcion3 = JOptionPane.showInputDialog("Ingrese 3/4 opción de la pregunta (c)");
                        String opcion4 = JOptionPane.showInputDialog("Ingrese 4/4 opción de la pregunta (d)");
                        String respuesta = JOptionPane.showInputDialog("Ingrese la respuesta de la pregunta (a, b, c o d)");
                        Pregunta pregunta = new Pregunta(id, enunciado, nivel, opcion1, opcion2, opcion3, opcion4, respuesta);
                        preguntaJDBC.insert(pregunta);
                        JOptionPane.showMessageDialog(null, "La pregunta ha sido guardada con éxito");
                    } else {
                        JOptionPane.showMessageDialog(null, "El ID ya se encuentra registrado");
                    }
                    break;
                case 2:
                    boolean en = false;
                    int nivel_1 = 0;
                    int nivel_2 = 0;
                    int nivel_3 = 0;
                    int nivel_4 = 0;
                    int nivel_5 = 0;
                    List<Pregunta> listaPregunta_jugador = preguntaJDBC.select();
                    for (Pregunta pregunta : listaPregunta_jugador) {
                        if (pregunta.getNivel() == 1) {
                            nivel_1++;
                        } else if (pregunta.getNivel() == 2) {
                            nivel_2++;
                        } else if (pregunta.getNivel() == 3) {
                            nivel_3++;
                        } else if (pregunta.getNivel() == 4) {
                            nivel_4++;
                        } else {
                            nivel_5++;
                        }
                    }
                    if (nivel_1 == 5 && nivel_2 == 5 && nivel_3 == 5 && nivel_4 == 5 && nivel_5 == 5) {
                        int id_jugador = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del Jugador"));
                        List<Jugador> listaJugador = jugadorJDBC.select();
                        for (Jugador jugador : listaJugador) {
                            if (jugador.getId() == id_jugador) {
                                en = true;
                                break;
                            }
                        }
                        if (!en) {
                            String respuesta;
                            int nivel_alcanzado = 1;
                            float premio = 0;
                            boolean retiro_v = false;
                            ArrayList<Pregunta> listaPreguntaNivel = new ArrayList<>();
                            String nombre = JOptionPane.showInputDialog("Ingrese nombre del jugador");
                            String apellido = JOptionPane.showInputDialog("Ingrese apellido del jugador");
                            while (nivel_alcanzado < 6) {
                                for (Pregunta pregunta : listaPregunta_jugador) {
                                    if (pregunta.getNivel() == nivel_alcanzado) {
                                        listaPreguntaNivel.add(pregunta);
                                    }
                                }
                                Random random = new Random();
                                int i = random.nextInt(5);
                                respuesta = JOptionPane.showInputDialog("Jugador: " + id_jugador
                                        + "\nNombre: " + nombre + "\nApellido: " + apellido
                                        + "\nNivel: " + nivel_alcanzado + "\nPuntos: " + premio
                                        + "\n\nResponder lo siguiente:"
                                        + "\n" + listaPreguntaNivel.get(i).getEnunciado()
                                        + "\na. " + listaPreguntaNivel.get(i).getOpcion1()
                                        + "\nb. " + listaPreguntaNivel.get(i).getOpcion2()
                                        + "\nc. " + listaPreguntaNivel.get(i).getOpcion3()
                                        + "\nd. " + listaPreguntaNivel.get(i).getOpcion4()
                                        + "\n\n!Recuerde puede retirarse en cualquier momento¡ Si es asi marque 's' ");

                                if (respuesta.equalsIgnoreCase(listaPreguntaNivel.get(i).getRespuesta())) {
                                    nivel_alcanzado++;
                                    premio += 20;
                                    listaPreguntaNivel.clear();
                                    JOptionPane.showMessageDialog(null, "!Felicidades¡ Pasa al nivel " + nivel_alcanzado + ", obtienes 20 puntos");
                                } else if (respuesta.equalsIgnoreCase("s")) {
                                    retiro_v = true;
                                    JOptionPane.showMessageDialog(null, "!Gracias por jugar¡\n"
                                            + "Nivel alcanzado: " + nivel_alcanzado
                                            + "\nPuntos obtenidos: " + premio);
                                    break;
                                } else {
                                    premio = 0;
                                    JOptionPane.showMessageDialog(null, "Perdiste, !Gracias por jugar¡\n"
                                            + "Nivel alcanzado: " + nivel_alcanzado
                                            + "\nPierde todos los puntos: " + premio);
                                    break;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "!Felicidades¡ Paso toda la prueba \nNivel:" + nivel_alcanzado
                                    + "\nPuntos: " + premio);
                            Jugador jugador = new Jugador(id_jugador, nombre, apellido, nivel_alcanzado, premio, retiro_v);
                            jugadorJDBC.insert(jugador);
                        } else {
                            JOptionPane.showMessageDialog(null, "El ID ya se encuentra registrado");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Deben existir minimo 5 preguntas por nivel: "
                                + "\nNivel 1: " + nivel_1 + "\nNivel 2: " + nivel_2 + "\nNivel 3: " + nivel_3
                                + "\nNivel 4: " + nivel_4 + "\nNivel 5: " + nivel_5);
                    }
                    break;
                case 3:
                    String jugadores = "";
                    List<Jugador> listaJugador = jugadorJDBC.select();
                    for (Jugador jugador : listaJugador) {
                        if (jugador.getNivelAlcanzado() == 5 || jugador.getPremio() > 50) {
                            jugadores = jugadores + String.valueOf("\nNombre: " + jugador.getNombre()
                                    + "\nApellido: " + jugador.getApellido()
                                    + "\nNivel Alcanzado: " + jugador.getNivelAlcanzado()
                                    + "\nPuntos Obtenidos: " + jugador.getPremio() + "\n" );
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Lista de Ganadores" + jugadores);
                    break;
            }
        } while (opcion > 0 && opcion < 4);
    }
}
