package com.example.practica_ayudantia.controlador;

import com.example.practica_ayudantia.modelo.Usuario;
import com.example.practica_ayudantia.modelo.datos.DBGenerator;
import com.example.practica_ayudantia.modelo.datos.dao.usuarioDao;
import org.jooq.DSLContext;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "registroUsuarioServlet", value = "/registroUsuario")
public class RegistroUsuarioServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            DBGenerator.iniciarBD("Usuarios");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher respuesta = request.getRequestDispatcher("/registroUsuario.jsp");
        respuesta.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("edad").length() != 0 && request.getParameter("nombre").length() != 0 && request.getParameter("rut").length() != 0
                && request.getParameter("direccion").length() != 0 && request.getParameter("numeroTelefono").length() != 0
                && request.getParameter("correoElectronico").length() != 0) {
            String nombre = request.getParameter("nombre");
            int edad = Integer.parseInt(request.getParameter("edad"));
            String rut = request.getParameter("rut");
            String direccion = request.getParameter("direccion");
            String numeroTelefono = request.getParameter("numeroTelefono");
            String correoElectronico = request.getParameter("correoElectronico");

            Usuario usuario = new Usuario(nombre, rut, direccion, numeroTelefono, correoElectronico);
            DSLContext query = null;
            try {
                query = DBGenerator.conectarBD("Usuarios");
                if (agregarUsuario(query, usuario)) {
                    request.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("/registroValido.jsp").forward(request, response);
                } else {
                    // Usuario ya existe, mostrar mensaje en el JSP
                    request.setAttribute("mensaje", "No se puede agregar el usuario. Ya está registrado.");
                    request.getRequestDispatcher("/registroInvalido.jsp").forward(request, response);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean agregarUsuario(DSLContext query, Usuario usuario) {
        try {
            // Verificar si el usuario ya está registrado
            if (usuarioDao.existeUsuario(query, usuario.getNumeroIdentificacion())) {
                return false; // Usuario ya existe, no se puede agregar de nuevo
            }

            usuarioDao.agregarUsuario(query, usuario);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
