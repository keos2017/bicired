package com.example.bicired;

public class DescripcionProducto {

    private String descripcion, producto;
    private int imgProducto;


    public DescripcionProducto() {
    }

    public DescripcionProducto(String descripcion, String producto, int imgProducto) {
        this.descripcion = descripcion;
        this.producto = producto;
        this.imgProducto = imgProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getImgProducto() {
        return imgProducto;
    }

    public void setImgProducto(int imgProducto) {
        this.imgProducto = imgProducto;
    }
}

