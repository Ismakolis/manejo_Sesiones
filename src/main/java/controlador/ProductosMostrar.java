package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Productos;
import servicios.LoginServices;
import servicios.LoginServicesSesionImplement;
import servicios.ProductoServicesImplement;
import servicios.ProductoServicio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

//Anotacion
@WebServlet({"/productohtml"})

public class ProductosMostrar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoServicio servicios = new ProductoServicesImplement();
        List<Productos> productos = servicios.listar();

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");


        try (PrintWriter out = resp.getWriter()) {
            //Se crea la plantilla html
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Listado de productos</title>");
            //
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("</head>");
            out.println("<body class=\"bg-light\">");
            out.println("<div class=\"container my-5\">");
            out.println("<h1 class=\"text-center mb-4\">Listado de Productos</h1>");
            out.println("<table class=\"table table-bordered table-hover\">");
            out.println("<thead class=\"table-dark\">");
            out.println("<tr>");
            out.println("<th>ID PRODUCTO</th>");
            out.println("<th>NOMBRE</th>");
            out.println("<th>CATEGORIA</th>");
            out.println("<th>PRECIO</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            //
            productos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getIdProductos() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getCategoria() + "</td>");
                out.println("<td>" + p.getPrecio() + "</td>");
                out.println("</tr>");
            });
            out.println("</tbody>");
            out.println("</table>");
            out.println("<div class=\"text-center mt-4\">");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }


    }

}

