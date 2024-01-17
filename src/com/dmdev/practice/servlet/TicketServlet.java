package com.dmdev.practice.servlet;

import com.dmdev.practice.service.TicketService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {

    private static final TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        var flightId = Long.valueOf(req.getParameter("flightId"));
        try (var writer = resp.getWriter()) {
            writer.write("<h1>Купленные билеты: </h1>");
            writer.write("<ul>");
            ticketService.findAllByFlightId(flightId).forEach(
                    ticketDto -> writer.write("""
                            <li>
                                %s
                            </li>
                            """.formatted(ticketDto.getSeatNo()))
            );
            writer.write("</ul>");
        }
    }
}
