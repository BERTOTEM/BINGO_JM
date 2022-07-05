/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

import com.example.domain.Bingo;
import com.example.domain.Gamers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller//habilita los controladores
@Slf4j   //sirve parar ver mensajes en la consola
public class controladorREST {

    @Autowired
    static final Bingo bingo = new Bingo();
    @Autowired
    private final Gamers gamer = new Gamers();
    /**
     * muestra la cartilla del bingo, la crea y implementa los metodos para ganar
     * @param model
     * @return la pagina indice donde se muestra la cartilla del bingo
     */
    @GetMapping("/")//me coloca la direccion y que se va a hacer en ella
    public String comienzo(Model model) {
        log.info("Estoy ejecutando el controlador MVC");
        bingo.CreateCarton();
        model.addAttribute("bingo", bingo);
        model.addAttribute("lb", bingo.getLb());
        model.addAttribute("li", bingo.getLi());
        model.addAttribute("ln", bingo.getLn());
        model.addAttribute("lg", bingo.getLg());
        model.addAttribute("lo", bingo.getLo());
        model.addAttribute("BINGO", bingo.getBINGO());
        model.addAttribute("BINGO_L", bingo.getBINGO_L());
        model.addAttribute("BINGO_X", bingo.getBINGO_X());
        model.addAttribute("BINGO_88", bingo.getBINGO_88());
        model.addAttribute("BallDrawn", bingo.getBallDrawn());//la parte del String es el nombre de la variable que debe de colocar en la pagina
        return "indice";
    }
    /**
     * marca en la posicion determinada una x cuando este conincide un la balota
     * @param model
     * @return redirecciona a indice con la actualizacion del marcado
     */
    @GetMapping("/CheckCard_B")//me coloca la direccion y que se va a hacer en ella
    public String CheckCard_B(Model model) {
        log.info("Estoy ejecutando el controlador MVC");
        bingo.CheckCard_B();
        return "redirect:/";
    }
     /**
     * marca en la posicion determinada una x cuando este conincide un la balota
     * @param model
     * @return redirecciona a indice con la actualizacion del marcado
     */
    @GetMapping("/CheckCard_I")//me coloca la direccion y que se va a hacer en ella
    public String CheckCard_I(Model model) {
        log.info("Estoy ejecutando el controlador MVC");
        bingo.CheckCard_I();
        return "redirect:/";
    }
     /**
     * marca en la posicion determinada una x cuando este conincide un la balota
     * @param model
     * @return redirecciona a indice con la actualizacion del marcado
     */
    @GetMapping("/CheckCard_N")//me coloca la direccion y que se va a hacer en ella
    public String CheckCard_N(Model model) {
        log.info("Estoy ejecutando el controlador MVC");
        bingo.CheckCard_N();
        return "redirect:/";
    }
     /**
     * marca en la posicion determinada una x cuando este conincide un la balota
     * @param model
     * @return redirecciona a indice con la actualizacion del marcado
     */
    @GetMapping("/CheckCard_G")//me coloca la direccion y que se va a hacer en ella
    public String CheckCard_G(Model model) {
        log.info("Estoy ejecutando el controlador MVC");
        bingo.CheckCard_G();
        return "redirect:/";
    }
     /**
     * marca en la posicion determinada una x cuando este conincide un la balota
     * @param model
     * @return redirecciona a indice con la actualizacion del marcado
     */
    @GetMapping("/CheckCard_O")//me coloca la direccion y que se va a hacer en ella
    public String CheckCard_O(Model model) {
        log.info("Estoy ejecutando el controlador MVC");
        bingo.CheckCard_O();
        return "redirect:/";
    }
     /**
     * saca una balota al azar
     * @param model
     * @return redirecciona a indice con la actualizacion de la balota
     */
    @GetMapping("/Bolita")//me coloca la direccion y que se va a hacer en ella
    public String Bolita(Model model) {
        log.info("Estoy ejecutando el controlador MVC");
        bingo.gameBalls();
        return "redirect:/";
    }
     /**
     * chequea que la cartilla este marcada en su totalidad
     * @param model
     * @return redirecciona a indice con la actualizacion de ganador o no
     */
    @GetMapping("/Winner")//me coloca la direccion y que se va a hacer en ella
    public String Winner(Model model) {
        log.info("Estoy ejecutando el controlador MVC");
        bingo.Winner();
        return "redirect:/";
    }
     /**
     * chequea que la cartilla este marcada formadno una L
     * @param model
     * @return redirecciona a indice con la actualizacion de ganador o no
     */
    @GetMapping("/WinnerL")//me coloca la direccion y que se va a hacer en ella
    public String WinnerL(Model model) {
        log.info("Estoy ejecutando el controlador MVC");
        bingo.WinnerL();
        return "redirect:/";
    }
    /**
     * chequea que la cartilla este marcada formadno una X
     * @param model
     * @return redirecciona a indice con la actualizacion de ganador o no
     */
    @GetMapping("/WinnerX")//me coloca la direccion y que se va a hacer en ella
    public String WinnerX(Model model) {
        log.info("Estoy ejecutando el controlador MVC");
        bingo.WinnerX();
        return "redirect:/";
    }
    /**
     * chequea que la cartilla este marcada formadno una ::
     * @param model
     * @return redirecciona a indice con la actualizacion de ganador o no
     */
    @GetMapping("/Winner88")//me coloca la direccion y que se va a hacer en ella
    public String Winner88(Model model) {
        log.info("Estoy ejecutando el controlador MVC");
        bingo.Winner88();
        return "redirect:/";
    }
    /**
     * chequea los jugadores que esten conectados
     * @param model
     * @return redirecciona a indice con la actualizacion de los jugadores conectados
     */
    @GetMapping("/Lobby")//me coloca la direccion y que se va a hacer en ella
    public String inicio(Model model) {
        log.info("Estoy ejecutando el controlador MVC");
        gamer.Formato_json();
        model.addAttribute("gamer", gamer);
        model.addAttribute("jugadores", gamer.getJugadores());
        return "Lobby";
    }

}
