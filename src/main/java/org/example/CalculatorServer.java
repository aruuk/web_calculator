package org.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class CalculatorServer {

    public static void main(String[] args) throws Exception {
        // Создаем сервер на порту 8080
        Server server = new Server(8080);

        // Настраиваем контекст для сервлетов
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");

        // Указываем базовую директорию для статических файлов (например, HTML, CSS, JS)
        handler.setResourceBase("src/main/webapp");

        // Добавляем сервлет для калькулятора
        handler.addServlet(new ServletHolder(new CalculatorServlet()), "/calculate");

        // Добавляем обработчик статических файлов
        handler.addServlet(new ServletHolder("default", new org.eclipse.jetty.servlet.DefaultServlet()), "/");

        // Добавляем контекст к серверу
        server.setHandler(handler);

        // Запуск сервера
        server.start();
        System.out.println("Сервер запущен на порту 8080");
        server.join();
    }
}
