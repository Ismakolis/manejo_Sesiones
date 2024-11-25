package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import servicios.LoginServices;
import servicios.LoginServicesImplement;
import servicios.LoginServicesSesionImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/logins","/login.html"})

public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    //sobre esxcribimos el metodo doGet
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // se hace una preguinta si la cookie es distinta a null
        //si es verdadero obtengo la cookie caso contrario creo un  nuebo objeto
        //de la cookie
        //Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        //busco en todo el arreglo de cookie si existe la cookie solicitada y la convierto en string
        /*Optional<String> cookieOptional = Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findFirst();*/
        // creo un nuevo objeto de la cookie

        //implementamos el objeto de la sesion
        LoginServices auth = new LoginServicesImplement();
        /*Creamo una variable optional donde se guarda el nombre del usuario
        * obteniendolo del metodo get username*/

        Optional<String> usernameOptional = auth.getUsername(req);
        //SI el username esta presente quiero que se muestre quiero que me muestre la vista de bienvenida.
        if (usernameOptional.isPresent()) {
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Bienvenido usuario</title>");
                out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class=\"container mt-5\">");  // Bootstrap container for centered content
                out.println("<h1 class=\"text-center\">Hola nuevamente " + usernameOptional.get() + " ya iniciaste sesión</h1>");
                out.println("<ul class=\"list-group\">");
                out.println("</ul>");
                out.println("<div class=\"text-center mt-3\">");
                out.println("<a href='" + req.getContextPath() + "/index.html' class=\"btn btn-primary\">Volver al inicio</a>");
                out.println("</div>");
                out.println("</div>");  // Closing the container
                out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>");
                out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>");
                out.println("</body>");
                out.println("</html>");
            }
            //caso contrario me devuelve al login
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            //resp.setContentType("text/html;charset=UTF-8 ");
           /* try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Bienvenido a la aplicacion</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Bienvenido a mi aplicacion</h1>");
                out.println("</body>");
                out.println("</html>");
            }*/


            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect(req.getContextPath() + "/login.html");
        } else {
            resp.getWriter().write("Error: Credenciales incorrectas.");

        }
    }
}
