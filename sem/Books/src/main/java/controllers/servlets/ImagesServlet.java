package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet("/images/books/*")
public class ImagesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imagePath = (req.getPathInfo().substring(1));
        File img = new File("..\\imagesForSite\\booksImages\\" + req.getPathInfo());
//        InputStream stream = getServletContext().getResourceAsStream("..\\imagesForSite\\booksImages\\" + req.getPathInfo());
//        byte[] content = stream.readAllBytes();
        resp.setContentType(getServletContext().getMimeType(imagePath));
        resp.setContentLength((int) img.length());
//        resp.getOutputStream().write(content);
//        resp.setHeader("Content-Type", getServletContext().getMimeType(imagePath));
//        resp.setHeader("Content-Length", String.valueOf(img.length()));
        resp.setHeader("Content-Disposition", "inline; filename=\"" + imagePath + "\"");
        Files.copy(img.toPath(), resp.getOutputStream());
//        req.con
//
    }
}
