package com.example.practica_ayudantia.modelo.datos;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.Connection;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

public class DBGenerator {

    // Método inicial para crear una base de datos y sus respectivas tablas en caso de que no exista
    public static void iniciarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection("root", "");
        DSLContext create = DSL.using(connection);
        crearBaseDato(create, nombreBD);
        create = actualizarConexion(connection, nombreBD);
        crearTablaLibro(create);
        crearTablaUsuario(create);
        crearTablaPrestamo(create);
        relacionarTabla(create, "Prestamo", "id_usuario", "Usuario");
        relacionarTabla(create, "Prestamo", "id_libro", "Libro");
        DBConnector.closeConnection();
    }

    // Método para conectarse a una base de datos ya creada
    public static DSLContext conectarBD(String nombre) throws ClassNotFoundException {
        Connection connection = DBConnector.connection(nombre, "root", "");
        DSLContext create = DSL.using(connection);
        return create;
    }

    // Crea una base de datos en caso de que no exista
    private static void crearBaseDato(DSLContext create, String nombreBD) {
        create.createDatabaseIfNotExists(nombreBD).execute();
    }

    // Actualiza la conexión inicial para conectar a la base de datos
    private static DSLContext actualizarConexion(Connection connection, String nombreBD) {
        DBConnector.closeConnection();
        connection = DBConnector.connection(nombreBD, "root", "");
        DSLContext create = DSL.using(connection);
        return create;
    }

    // Método estándar para crear una tabla de Libros
    private static void crearTablaLibro(DSLContext create) {
        create.createTableIfNotExists("Libro").column("id_libro", INTEGER.identity(true))
                .column("titulo", VARCHAR(255))
                .column("autor", VARCHAR(255))
                .column("isbn", VARCHAR(20))
                .column("genero", VARCHAR(50))
                .column("ano_publicacion", INTEGER)
                .constraint(primaryKey("id_libro")).execute();
    }

    // Método estándar para crear una tabla de Usuarios
    private static void crearTablaUsuario(DSLContext create) {
        create.createTableIfNotExists("Usuario").column("id_usuario", INTEGER.identity(true))
                .column("nombre", VARCHAR(100))
                .column("numero_identificacion", VARCHAR(50))
                .column("direccion", VARCHAR(255))
                .column("numero_telefono", VARCHAR(20))
                .column("correo_electronico", VARCHAR(100))
                .constraint(primaryKey("id_usuario")).execute();
    }

    // Método estándar para crear una tabla de Préstamos
    private static void crearTablaPrestamo(DSLContext create) {
        create.createTableIfNotExists("Prestamo").column("id_prestamo", INTEGER.identity(true))
                .column("id_usuario", INTEGER)
                .column("id_libro", INTEGER)
                .column("fecha_inicio", DATE)
                .column("fecha_devolucion", DATE)
                .constraint(primaryKey("id_prestamo"))
                .constraint(foreignKey("id_usuario").references("Usuario"))
                .constraint(foreignKey("id_libro").references("Libro")).execute();
    }

    // Relaciona dos tablas a través de una clave foránea
    private static void relacionarTabla(DSLContext create, String nombreTabla, String claveForanea, String nombreTablaRelacion) {
        create.alterTableIfExists(nombreTabla).alterConstraint(foreignKey(claveForanea).references(nombreTablaRelacion)).enforced();
    }
}
