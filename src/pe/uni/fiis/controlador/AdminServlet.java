package pe.uni.fiis.controlador;

import pe.uni.fiis.modelo.bean.Pelicula;
import pe.uni.fiis.modelo.bean.Sala;
import pe.uni.fiis.modelo.factory.TransaccionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminServlet")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pelicula = (String)request.getParameter("inPelicula");
        String sala = (String)request.getParameter("inSala");
        String funcion = (String)request.getParameter("inFuncion");
        HttpSession session = request.getSession();
        String admin = String.valueOf(session.getAttribute("admin"));

        if(pelicula != null){
            session.setAttribute("admin",admin);
            request.getRequestDispatcher("/AdminPelicula.jsp").forward(request,response);
        }
        if(sala != null){
            session.setAttribute("admin",admin);
            request.getRequestDispatcher("/AdminSala.jsp").forward(request,response);
        }
        if(funcion != null){
            session.setAttribute("admin",admin);
            List<Sala> salas = TransaccionFactory.getInstance().listarSala();
            request.setAttribute("salas", salas);
            List<Pelicula> peliculas = TransaccionFactory.getInstance().listarPelicula();
            request.setAttribute("peliculas", peliculas);
            request.getRequestDispatcher("/AdminFuncion.jsp").forward(request,response);
        }
    }
}
