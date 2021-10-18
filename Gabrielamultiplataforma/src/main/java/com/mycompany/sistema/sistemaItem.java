/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistema;

import java.util.ArrayList;

public class sistemaItem {

    /**
     * @return the _id
     */
    public int getId() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void setId(int _id) {
        this._id = _id;
    }

    /**
     * @return the _sistema
     */
    public String getSistema() {
        return _sistema;
    }

    /**
     * @param _sistema the _sistema to set
     */
    public void setSistema(String _sistema) {
        this._sistema = _sistema;
    }

    /**
     * @return the _diseñador
     */
    public String getDiseñador() {
        return _diseñador;
    }

    /**
     * @param _diseñador the _diseñador to set
     */
    public void setDiseñador(String _diseñador) {
        this._diseñador = _diseñador;
    }

    /**
     * @return the _version
     */
    public int getVersion() {
        return _version;
    }

    /**
     * @param _version the _version to set
     */
    public void setVersion(int _version) {
        this._version = _version;
    }

    /**
     * @return the _versionreciente
     */
    public int getVersionreciente() {
        return _versionreciente;
    }

    /**
     * @param _versionreciente the _versionreciente to set
     */
    public void setVersionreciente(int _versionreciente) {
        this._versionreciente = _versionreciente;
    }

 


    private int _id;
    private String _sistema;
    private String _diseñador;
    private int _version;
    private int _versionreciente;
    
    
    public sistemaItem(){
        this._id = 0;
        this._sistema = "";
        this._diseñador = "";
        this. _version = 0;
        this._versionreciente = 0;
       
    }
    
    public sistemaItem(int id, String sistema, String diseñador, int version, int versionreciente) {
        this._id = id;
        this._sistema = sistema;
        this._diseñador = diseñador;
        this._version = version;
        this._versionreciente = versionreciente;
        
    }
    
    public ArrayList<String> obtenerCampos(){
        ArrayList<String> campos = new ArrayList<String>();
        campos.add(String.valueOf(this.getId()));
        campos.add(this.getSistema());
        campos.add(this.getDiseñador());
        campos.add(String.valueOf(this.getVersion()));
        campos.add(String.valueOf(this.getVersionreciente()));
       
        
        return campos;
        
    }
   
    
    
    
}