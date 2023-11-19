package com.example.practica_ayudantia.modelo.datos.dao;

import com.example.practica_ayudantia.modelo.Usuario;
        import org.jooq.DSLContext;
        import org.jooq.Field;
        import org.jooq.Result;
        import org.jooq.Table;
        import org.jooq.Record;

        import static org.jooq.impl.DSL.*;

public class usuarioDao {

    public static void agregarUsuario(DSLContext query, Usuario usuario) {
        Table tablaUsuario = table(name("Usuario"));
        Field[] columnas = tablaUsuario.fields("nombre", "numero_identificacion", "direccion", "numero_telefono", "correo_electronico");
        query.insertInto(tablaUsuario, columnas[0], columnas[1], columnas[2], columnas[3], columnas[4])
                .values(usuario.getNombre(), usuario.getNumeroIdentificacion(), usuario.getDireccion(), usuario.getNumeroTelefono(), usuario.getCorreoElectronico())
                .execute();
    }

    public static boolean existeUsuario(DSLContext query, String numeroIdentificacion) {
        Table tablaUsuario = table(name("Usuario"));
        Result<Record> resultado = query.select().from(tablaUsuario).where(field("numero_identificacion").eq(numeroIdentificacion)).fetch();
        return resultado.isNotEmpty();
    }
}
