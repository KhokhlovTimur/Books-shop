package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@WebServlet("/images/books/*")
public class ImagesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imagePath = (req.getPathInfo().substring(1));
        InputStream inputStream = new FileInputStream("..\\imagesForSite\\booksImages" + req.getPathInfo() + ".png");
        byte[] imageBytes = inputStream.readAllBytes();
        resp.setContentType(getServletContext().getMimeType(imagePath));
        resp.setContentLength(imageBytes.length);
        resp.getOutputStream().write(imageBytes);
    }
}
