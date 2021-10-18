/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;


import com.mycompany.sistema.sistemaItem;
import java.util.ArrayList;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SistemaDB {
    
    private ArrayList _sistemaItems;
    
    public SistemaDB(){
        this._sistemaItems = new ArrayList<sistemaItem>();
    }
    
    public ArrayList<sistemaItem> getsistemaItems(){
        return this.getsistemaItems(false);
    }
    
    public void tableInitialize(){
        String sqlCreate = "CREATE TABLE IF NOT EXISTS LENGUAJES"
                        + " (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                        + " SISTEMA TEXT NOT NULL,"
                        + " DISEÑADOR TEXT NOT NULL,"
                        + " VERSION INTEGER NOT NULL,"
                        + " RECIENTE INTEGER NOT NULL"
                        + " )";
       
        try {
            Statement comando = Conexion.getConexion().createStatement();
            int resultado = comando.executeUpdate(sqlCreate);
            comando.close();
        } catch( Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public ArrayList<sistemaItem> getsistemaItems(boolean forceLoad){
        try {
           if (forceLoad) {
                Statement comando =  Conexion.getConexion().createStatement();
                ResultSet misRegistro = comando.executeQuery("SELECT * from LENGUAJES;");
                this._sistemaItems.clear();
                while (misRegistro.next()) {
                    sistemaItem registro = new sistemaItem();
                    registro.setId(misRegistro.getInt("ID"));
                    registro.setSistema(misRegistro.getString("SISTEMA"));
                    registro.setDiseñador(misRegistro.getString("DISEÑADOR"));
                    registro.setVersion(misRegistro.getInt("VERSION"));
                    registro.setVersionreciente(misRegistro.getInt("RECIENTE"));
                    
                    this._sistemaItems.add(registro);
                }
                comando.close();
           }
           return this._sistemaItems;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
            return this._sistemaItems;
        }   
    }
    
    public sistemaItem getsistemaItemById(int id){
        try {
            String SQLGetByID = "SELECT * FROM LENGUAJES WHERE ID = ?;";
            PreparedStatement comando =  Conexion.getConexion().prepareStatement(SQLGetByID);
            comando.setInt(1, id);
            ResultSet misRegistro = comando.executeQuery();
            sistemaItem registro = new sistemaItem();
            while (misRegistro.next()) {
                registro.setId(misRegistro.getInt("ID"));
                registro.setSistema(misRegistro.getString("SISTEMA"));
                registro.setDiseñador(misRegistro.getString("DISEÑADOR"));
                registro.setVersion(misRegistro.getInt("VERSION"));
                registro.setVersionreciente(misRegistro.getInt("RECIENTE"));
                
                break;
            }
            comando.close();

            return registro;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
            return null;
        }   
    }
    
    public int updatelenguajeItem(sistemaItem ItemToUpdate) {
        try {
            String SQLUpdate = "UPDATE LENGUAJES set SISTEMA=?, DISEÑADOR=?, VERSION=?, RECIENTE=? where ID=?;";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLUpdate);
            
            comando.setString(1, ItemToUpdate.getSistema());
            comando.setString(2, ItemToUpdate.getDiseñador());
            comando.setInt(3, ItemToUpdate.getVersion());
            comando.setInt(4, ItemToUpdate.getVersionreciente());
            comando.setInt(5, ItemToUpdate.getId());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
     public int insertlenguajeItem(sistemaItem ItemToInsert) {
        try {
            String SQLInsert = "INSERT INTO LENGUAJES (SISTEMA, DISEÑADOR, VERSION, RECIENTE) values (?, ?, ?, ?);";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLInsert);
            
            comando.setString(1, ItemToInsert.getSistema());
            comando.setString(2, ItemToInsert.getDiseñador());
            comando.setInt(3, ItemToInsert.getVersion());
            comando.setInt(4, ItemToInsert.getVersionreciente());
            
                    
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
     
    public int deletelenguajeItem(sistemaItem ItemToDelete) {
        try {
            String SQLDelete = "DELETE FROM LENGUAJES WHERE ID = ?;";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLDelete);
            
            comando.setInt(1, ItemToDelete.getId());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
}
