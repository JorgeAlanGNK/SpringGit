package com.jorge_alan.spring_git_mvc.datos.sql_extension;

import java.sql.SQLType;
import java.util.Map;

import com.google.common.collect.Maps;

public class ParamValue {

    private String parametro;
    private Object valor;
    private SQLType tipo;

    public ParamValue(String parametro) {
        this.parametro = parametro;
    }

    public ParamValue(Object valor) {
        this.valor = valor;
    }

    public ParamValue(String parametro, Object valor) {
        this.parametro = parametro;
        this.valor = valor;
    }

    public ParamValue(String parametro, Object valor, SQLType tipo) {
        this.parametro = parametro;
        this.valor = valor;
        this.tipo = tipo;
    }

    public ParamValue(String parametro, SQLType tipo) {
        this.parametro = parametro;
        this.tipo = tipo;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public SQLType getTipo() {
        return tipo;
    }

    public void setTipo(SQLType tipo) {
        this.tipo = tipo;
    }
    
}
