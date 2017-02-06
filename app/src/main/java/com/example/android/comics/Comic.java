package com.example.android.comics;

/**
 * Created by INTEL on 06/02/2017.
 */

public class Comic {

    private String Titulo;
    private String Precio;
    private String paginas;
    private String Descripcion;
    private String Urlimagen;


    public Comic(String titulo, String urlimagen, String descripcion, String paginas, String precio) {
        Titulo = titulo;
        Urlimagen = urlimagen;
        Descripcion = descripcion;
        this.paginas = paginas;
        Precio = precio;
    }

    public Comic(){

    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }


    public String getUrlimagen() {
        return Urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        Urlimagen = urlimagen;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }
}
