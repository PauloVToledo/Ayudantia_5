package com.example.practica_ayudantia.modelo.datos.dao;

import com.example.practica_ayudantia.modelo.Prestamo;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.Record;

import static org.jooq.impl.DSL.*;

public class prestamoDao {

    public static void agregarPrestamo(DSLContext query, Prestamo prestamo) {
        Table tablaPrestamo = table(name("Prestamo"));
        Field[] columnas = tablaPrestamo.fields("id_usuario", "id_libro", "fecha_inicio", "fecha_devolucion");
        query.insertInto(tablaPrestamo, columnas[0], columnas[1], columnas[2], columnas[3])
                .values(prestamo.getUsuario(), prestamo.getLibro(), prestamo.getFechaInicio(), prestamo.getFechaDevolucion())
                .execute();
    }

    public static boolean existePrestamo(DSLContext query, int idUsuario, int idLibro) {
        Table tablaPrestamo = table(name("Prestamo"));
        Result<Record> resultado = query.select().from(tablaPrestamo)
                .where(field("id_usuario").eq(idUsuario).and(field("id_libro").eq(idLibro)))
                .fetch();
        return resultado.isNotEmpty();
    }
}
