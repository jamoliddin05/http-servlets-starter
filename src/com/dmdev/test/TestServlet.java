package com.dmdev.test;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/testing-servlet")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Content-Disposition", "attachment; filename=\"filename.jpeg\"");
        resp.setContentType("image/jpeg");

        try (var stream = TestServlet.class.getClassLoader().getResourceAsStream("image.jpg");
             var writer = resp.getOutputStream()) {
            writer.write(stream.readAllBytes());
        }
    }
}
