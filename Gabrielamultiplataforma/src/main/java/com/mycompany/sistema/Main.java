/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistema;

import com.mycompany.utilidad.Layout;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
       
        Layout.printHeader("LENGUAJES DE PROGRAMACION");
        String OpcionMenu = "";
        
        Scanner entradaTeclado = new Scanner(System.in);
        
        Aplicacion sistemaapp = new Aplicacion(entradaTeclado);
        
        while (!(OpcionMenu.toUpperCase().equals("S"))) {
            Layout.printMenu();
            OpcionMenu = entradaTeclado.nextLine();

            System.out.println("Texto ingresado es: " + OpcionMenu);
            sistemaapp.activarEvento(OpcionMenu);
            
        }
    }
    
}
