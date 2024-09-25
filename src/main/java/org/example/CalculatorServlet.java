package org.example;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CalculatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        // Получаем параметры запроса
        String num1 = req.getParameter("a");
        String num2 = req.getParameter("b");
        String operation = req.getParameter("operation");

        double result = 0;
        boolean isValid = true;
        String errorMessage = null;

        if (num1 == null || num2 == null || operation == null || num1.isEmpty() || num2.isEmpty()) {
            errorMessage = "Необходимо указать оба числа и операцию.";
            isValid = false;
        } else {
            try {
                double number1 = Double.parseDouble(num1);
                double number2 = Double.parseDouble(num2);

                // Выбираем операцию
                switch (operation) {
                    case "add":
                        result = number1 + number2;
                        break;
                    case "subtract":
                        result = number1 - number2;
                        break;
                    case "multiply":
                        result = number1 * number2;
                        break;
                    case "divide":
                        if (number2 == 0) {
                            errorMessage = "Ошибка: Деление на ноль.";
                            isValid = false;
                        } else {
                            result = number1 / number2;
                        }
                        break;
                    default:
                        errorMessage = "Неподдерживаемая операция.";
                        isValid = false;
                }
            } catch (NumberFormatException e) {
                errorMessage = "Неверный формат числа.";
                isValid = false;
            }
        }

        // Формируем HTML-ответ
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("<html><body>");

        if (isValid) {
            responseBuilder.append("<h1>Результат: " + result + "</h1>");
        } else {
            responseBuilder.append("<h1>Ошибка: " + errorMessage + "</h1>");
        }

        responseBuilder.append("<br><a href=\"/index.html\">Вернуться к калькулятору</a>");
        responseBuilder.append("</body></html>");

        resp.getWriter().println(responseBuilder.toString());
    }
}
