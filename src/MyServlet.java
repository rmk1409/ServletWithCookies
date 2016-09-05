import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Roma on 05.09.2016.
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        //get name and put it in cookie
        String userName = request.getParameter("userName");
        Cookie cookie = new Cookie("userName", userName);
        cookie.setMaxAge(120);
        response.addCookie(cookie);

        PrintWriter writer = response.getWriter();
        writer.printf("<h3>Hello %s</h3>", userName);
        writer.println("Try to reopen browser and come here,  we'll remember you <b>for 2 min</b>");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        //check cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            PrintWriter writer = response.getWriter();
            writer.printf("<h3>Hello %s</h3>", cookies[0].getValue());
            writer.println("As you can see, we can recognize you");
        } else {
            getServletContext().getRequestDispatcher("/Form.html").forward(request, response);
        }
    }
}
