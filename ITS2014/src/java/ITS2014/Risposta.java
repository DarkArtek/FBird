/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ITS2014;

/**
 *
 * @author Tabris
 */
public class Risposta {

    public static Risposta OK = new Risposta("ok");

    public String messaggio;

    public Risposta(String messaggio) {
        this.messaggio = messaggio;
    }
}
