/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package edu.eci.escuelaing.arem.namesncs;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cristian.castellanos
 */
public class NamesNCS {

    public static void main(String[] args) {
        try {
            URL mySite = new URL("https://support.google.com/google-ads/answer/2382957?hl=es-419#:~:text=La%20referencia%20es%20la%20p%C3%A1gina,registran%20las%20URL%20de%20referencia.");
            System.out.println("Protocol: " + mySite.getProtocol());
            System.out.println("Authority. " + mySite.getAuthority());
            System.out.println("Host: " + mySite.getHost());
            System.out.println("Port: " + mySite.getPort());
            System.out.println("Path: " + mySite.getPath());
            System.err.println("Query: " + mySite.getQuery());
            System.out.println("File: " + mySite.getFile());
            System.out.println("Ref: " + mySite.getRef());
                    } catch (MalformedURLException ex) {
            Logger.getLogger(NamesNCS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}