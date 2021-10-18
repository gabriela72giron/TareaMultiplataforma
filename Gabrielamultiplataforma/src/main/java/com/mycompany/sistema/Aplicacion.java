/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistema;

import com.mycompany.dao.SistemaDB;
import com.mycompany.utilidad.Layout;

import java.util.Scanner;
import java.util.ArrayList;


public class Aplicacion {
    
    private Scanner _EntradaTeclado;
    private ArrayList _Mistema;
    private int _MiSisIdCounter;
    private SistemaDB _SisModel;
    public Aplicacion(Scanner EntradaTeclado) {
        this._EntradaTeclado = EntradaTeclado;
        this._Mistema = new ArrayList<sistemaItem>();  
        this._MiSisIdCounter = 0;
        this._SisModel = new SistemaDB();
        this._SisModel.tableInitialize();
        this._Mistema = this._SisModel.getsistemaItems(true);
    }
    
    public void activarEvento(String opcionMenu){
        switch(opcionMenu.toUpperCase()){
            case "M":
                this.mostrarRegistros();
                break;
            case "I":
                this.ingresarNuevoRegistro();
                break;
            case "A":
                this.actualizarRegistro();
                break;
            case "E":   
                this.eliminarRegistro();
                break;
            case "S":
                break;
            default:
                System.out.println("Opción no reconocida!!!");
                break;
        }
    }
    
    private void ingresarNuevoRegistro(){
        Layout.printHeader("Nuevo Lenguaje");
        sistemaItem nuevosistemItem =  new sistemaItem();
        nuevosistemItem.setSistema(Layout.obtenerValorParaCampo("Nombre del lenguaje", "lenguaje X", this._EntradaTeclado));
        nuevosistemItem.setDiseñador(Layout.obtenerValorParaCampo("Nombre del diseñador", "diseñador X", this._EntradaTeclado));
        nuevosistemItem.setVersion(Integer.parseInt(Layout.obtenerValorParaCampo("version inicial", "version X", this._EntradaTeclado)));
        nuevosistemItem.setVersionreciente(Integer.parseInt(Layout.obtenerValorParaCampo("Version reciente", "Version X", this._EntradaTeclado)));
        
        this._SisModel.insertlenguajeItem(nuevosistemItem);
        this._Mistema = this._SisModel.getsistemaItems(true);
        
        Layout.printSeparator();
        System.out.println(this._Mistema.size());
    }
    
    private void mostrarRegistros(){
        Layout.printSeparator();
        ArrayList<String> columnas = new ArrayList<String>();
        columnas.add("Codigo");
        columnas.add("Sistema");
        columnas.add("Diseñador");
        columnas.add("Version");
        columnas.add("Ultima");
       
        
        ArrayList<Integer> anchos = new ArrayList<Integer>();
        anchos.add(9);
        anchos.add(14);
        anchos.add(18);
        anchos.add(17);
        anchos.add(16);
        
        
        try {
            
            Layout.imprimirEnColumna(columnas, anchos, "|");
            Layout.printSeparator();
            for(int i = 0; i< this._Mistema.size(); i++){
                columnas = ((sistemaItem) this._Mistema.get(i)).obtenerCampos();
                Layout.imprimirEnColumna(columnas, anchos, "|");
            }
            
        } catch(Exception ex) {
            System.err.println("Error al imprimir " + ex.getMessage());
        }
    }
    
    private void actualizarRegistro(){
        Layout.printHeader("Actualizar Registro");
        int selectedId = Integer.valueOf(Layout.obtenerValorParaCampo("Ingrese Codigo Registro", "0", this._EntradaTeclado));
        sistemaItem selectsistema = null;

        selectsistema = this._SisModel.getsistemaItemById(selectedId);
        if (selectsistema == null ) {
            System.out.println("Registro solicitado no existe!!!");
        } else {
            selectsistema.setSistema(Layout.obtenerValorParaCampo("Nombre del lenguaje", selectsistema.getSistema(), this._EntradaTeclado));
            selectsistema.setDiseñador(Layout.obtenerValorParaCampo("Nombre del diseñador", selectsistema.getDiseñador(), this._EntradaTeclado));
            selectsistema.setVersion(Integer.parseInt(Layout.obtenerValorParaCampo("version inicial", "x", this._EntradaTeclado)));
            selectsistema.setVersionreciente(Integer.parseInt(Layout.obtenerValorParaCampo("Version reciente", "x", this._EntradaTeclado)));
      
           
            this._SisModel.updatelenguajeItem(selectsistema);
            this._Mistema = this._SisModel.getsistemaItems(true);
            Layout.printSeparator();
            System.out.println("Registro Actualizado Satisfactoriamente!!!");
        }
        
    }
    
    private void eliminarRegistro(){
        Layout.printHeader("Eliminar Registro");
        int selectedId = Integer.valueOf(Layout.obtenerValorParaCampo("Ingrese Codigo Registro", "0", this._EntradaTeclado));

        sistemaItem selectedsistema = this._SisModel.getsistemaItemById(selectedId);
        if (selectedsistema != null){
            Layout.printSeparator();
            String confirmado = Layout.obtenerValorParaCampo("¿Desea Eliminar este Registro? S - N", "N", this._EntradaTeclado);
            if (confirmado.toUpperCase().equals("S")){
               
                this._SisModel.deletelenguajeItem(selectedsistema);
                 this._Mistema = this._SisModel.getsistemaItems(true);
                Layout.printSeparator();
                System.out.println("Registro Eliminado Satisfactoriamente!!!");
            }
        } else {
            System.out.println("Registro solicitado no existe!!!");
            
           
        }
    
    }
    
    
}
