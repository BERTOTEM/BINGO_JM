/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.domain;

import java.util.ArrayList;
import java.util.Random;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 *
 * @author julian mazo
 */
@Data
@Service
public class Bingo {
    /**
     * lista para guardar las peloas en juego
     */
    ArrayList<String> ballinPlay = new ArrayList();
    /**
     *lista que contien la columna de la B
     */
    ArrayList<String> Lb = new ArrayList();
    /**
     * lista que contien la columna de la I
     */
    ArrayList<String> Li = new ArrayList();
    /**
     * lista que contien la columna de la N
     */
    ArrayList<String> Ln = new ArrayList();
    /**
     * lista que contien la columna de la G
     */
    ArrayList<String> Lg = new ArrayList();
    /**
     * lista que contien la columna de la O
     */
    ArrayList<String> Lo = new ArrayList();
    /**
     * bola sacada en ese moemnto q
     */
    String ballDrawn = "Oprime --->";
    /**
     *bingo de esquinas
     */
    String BINGO_88 = "BINGO_::";
    /**
     * numero de la bola qeu se juego en ese momento 
     */
    String ballNumber = "0";
    /**
     * bingo en X
     */
    String BINGO_X = "BINGO_X";
    /**
     * bingo en L
     */
    String BINGO_L = "BINGO_L";
    /**
     * letra de la bola que se juega en ese moemnto
     */
    String ballLetter = "B";
    /**
     * bingo
     */
    String BINGO = "BINGO";
    /**
     * lista para el marcado de las columnas
     */
    ArrayList List;
    /**
     * 
     * @param columna columba la cual se va a llenar
     * @param min el minimo 
     * @param max el maximo del ramdom
     */
    public void CreateColumn(ArrayList<String> columna, int min, int max) {
        Random rd = new Random();
        while (columna.size() < 6) {
            String num = String.valueOf(rd.nextInt(max - min + 1) + min);
            if (!columna.contains(num)) {
                columna.add(num);
            }
        }
    }
    /**
     * Crea el carton 
     * @return mensaje de ok para cheqeudo de cracion
     */
    public String CreateCarton() {
        this.CreateColumn(this.Lb, 1, 15);
        this.CreateColumn(this.Li, 16, 30);
        this.CreateColumn(this.Ln, 31, 45);
        this.CreateColumn(this.Lg, 46, 60);
        this.CreateColumn(this.Lo, 61, 75);
        return "ok";
    }
    /**
     * metodo pra sacar balotas al azar y tener un mhistorial de las ya sacadas
     */
    public void gameBalls() {
        String[] gameBalls = new String[5];
        gameBalls[0] = "B";
        gameBalls[1] = "I";
        gameBalls[2] = "N";
        gameBalls[3] = "G";
        gameBalls[4] = "O";
        Random rd = new Random();
        String bolilla = "";
        String numero = "";
        do {
            if (ballinPlay.size() < 75) {
                bolilla = gameBalls[rd.nextInt(5)];
                switch (bolilla) {
                    case "B" -> {
                        numero = String.valueOf(rd.nextInt(15) + 1);
                    }
                    case "I" -> {
                        numero = String.valueOf(rd.nextInt(15) + 16);
                    }
                    case "N" -> {
                        numero = String.valueOf(rd.nextInt(15) + 31);
                    }
                    case "G" -> {
                        numero = String.valueOf(rd.nextInt(15) + 46);
                    }
                    case "O" -> {
                        numero = String.valueOf(rd.nextInt(15) + 61);
                    }
                }
            }

        } while ((ballinPlay.contains(bolilla + numero) && (ballinPlay.size() < 74)));
        ballNumber = numero;
        ballLetter = bolilla;
        if (ballinPlay.size() < 75) {
            ballinPlay.add(bolilla + numero);
            ballDrawn = (bolilla + numero);
            System.out.println(ballinPlay.size());
        } else {
            ballDrawn = ("YA NO HAY BALOTAS ULTIMA BALOTA: " + ballinPlay.get(74));
        }

    }
    /**
     * metodo para marcar casillas en b
     * @return la columna con el marcado de x en la correspondiente posicion 
     */
    public ArrayList CheckCard_B() {
        List = Lb;
        int pos = List.indexOf(ballNumber);
        System.out.println(pos);
        if (pos >= 0) {
            List.set(pos, "X");
        }
        return List;
    }
    /**
     * metodo para marcar casillas en i
     * @return la columna con el marcado de x en la correspondiente posicion 
     */
    public ArrayList CheckCard_I() {
        List = Li;
        int pos = List.indexOf(ballNumber);
        System.out.println(pos);
        if (pos >= 0) {
            List.set(pos, "X");
        }
        return List;
    }
    /**
     * metodo para marcar casillas en n
     * @return la columna con el marcado de x en la correspondiente posicion 
     */
    public ArrayList CheckCard_N() {
        List = Ln;
        int pos = List.indexOf(ballNumber);
        System.out.println(pos);
        if (pos >= 0) {
            List.set(pos, "X");
        }
        return List;
    }
    /**
     * metodo para marcar casillas en g
     * @return la columna con el marcado de x en la correspondiente posicion 
     */
    public ArrayList CheckCard_G() {
        List = Lg;
        int pos = List.indexOf(ballNumber);
        System.out.println(pos);
        if (pos >= 0) {
            List.set(pos, "X");
        }
        return List;
    }
    /**
     * metodo para marcar casillas en o
     * @return la columna con el marcado de x en la correspondiente posicion 
     */
    public ArrayList CheckCard_O() {
        List = Lo;
        int pos = List.indexOf(ballNumber);
        System.out.println(pos);
        if (pos >= 0) {
            List.set(pos, "X");
        }
        return List;
    }
    /**
     * metodo para el chequeo del bingo, revisa que toda la cartilla tenga una x
     * @return el mensaje de ganar
     */
    public String Winner() {
        ArrayList<String> X = new ArrayList<>();
        for (int i = 0; i < 6; ++i) {
            X.add("X");
        }
        if ((Lb.equals(X) == true) && (Li.equals(X) == true) && (Ln.equals(X) == true) && (Lg.equals(X) == true) && (Lo.equals(X) == true)) {
            BINGO = "FELICIDADES GANASTE!!!";
            return "FELICIDADES GANASTE EL BINGO";
        } else {
            return "JUEGO EN CURSO";
        }
    }
     /**
     * metodo para el chequeo del bingo, revisa que toda la cartilla tenga una l compuesta de X
     * @return l mensaje de ganar en l
     */
    public String WinnerL() {
        ArrayList<String> X = new ArrayList<>();
        for (int i = 0; i < 6; ++i) {
            X.add("X");
        }
        if ((Lb.equals(X) == true) && (Li.get(4) == X.get(4)) && (Ln.get(4) == X.get(4)) && (Lg.get(4) == X.get(4)) && (Lo.get(4) == X.get(4))) {
            BINGO_L = "FELICIDADES GANASTE LA L";
            return "FELICIDADES GANASTE el bingo de la L";
        } else {
            return "Bingo_Ld";
        }

    }
      /**
     * metodo para el chequeo del bingo, revisa que toda la cartilla tenga una x compuesta de X
     * @return l mensaje de ganar en x
     */
    public String WinnerX() {
        ArrayList<String> X = new ArrayList<>();
        for (int i = 0; i < 6; ++i) {
            X.add("X");
        }
        if ((Lb.get(4) == X.get(4)) && (Lb.get(0) == X.get(0)) && (Li.get(1) == X.get(1)) && (Li.get(3) == X.get(3)) && (Ln.get(2) == X.get(2)) && (Lg.get(1) == X.get(1)) && (Lg.get(3) == X.get(3)) && (Lo.get(4) == X.get(4)) && (Lo.get(0) == X.get(0))) {
            BINGO_X = "FELICIDADES GANASTE LA X !!!";
            return "FELICIDADES GANASTE el bingo de la L";
        } else {
            return "Bingo_L";
        }

    }
     /**
     * metodo para el chequeo del bingo, revisa que toda la cartilla tenga una l compuesta de ::
     * @return l mensaje de ganar en ::
     */
    public String Winner88() {
        ArrayList<String> X = new ArrayList<>();
        for (int i = 0; i < 6; ++i) {
            X.add("X");
        }
        if ((Lb.get(0) == X.get(0)) && (Lb.get(4) == X.get(4)) && (Lo.get(0) == X.get(0)) && (Lo.get(4) == X.get(4))) {
            BINGO_88 = "FELICIDADES GANASTE 4 ESQUINAS";
            return "FELICIDADES GANASTE el bingo de la L";
        } else {
            return "Bingo_L";
        }

    }
}
