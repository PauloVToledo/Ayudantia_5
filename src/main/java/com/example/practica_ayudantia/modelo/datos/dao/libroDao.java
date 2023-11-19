package com.example.practica_ayudantia.modelo.datos.dao;

import com.example.practica_ayudantia.modelo.Libro;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.Record;

import static org.jooq.impl.DSL.*;

public class libroDao {

    public static void agregarLibro(DSLContext query, Libro libro) {
        Table tablaLibro = table(name("Libro"));
        Field[] columnas = tablaLibro.fields("titulo", "autor", "isbn", "genero", "ano_publicacion");
        query.insertInto(tablaLibro, columnas[0], columnas[1], columnas[2], columnas[3], columnas[4])
                .values(libro.getTitulo(), libro.getAutor(), libro.getIsbn(), libro.getGenero(), libro.getAnoPublicacion())
                .execute();
    }

    public static boolean existeLibro(DSLContext query, String isbn) {
        Table tablaLibro = table(name("Libro"));
        Result<Record> resultado = query.select().from(tablaLibro).where(field("isbn").eq(isbn)).fetch();
        return resultado.isNotEmpty();
    }
}
