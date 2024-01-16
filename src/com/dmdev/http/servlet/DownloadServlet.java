package com.dmdev.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Disposition", "attachment; filename=\"filename.jpeg\"");
        resp.setContentType("image/jpeg");

        try (var writer = resp.getOutputStream();
             var stream = DownloadServlet.class.getClassLoader().getResourceAsStream("image.jpg"))
        {
            writer.write(stream.readAllBytes());
        }
    }
}
