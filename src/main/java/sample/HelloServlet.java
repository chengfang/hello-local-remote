package sample;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = {"/"})
public class HelloServlet extends HttpServlet {
    static final String statefulHelloJndi = "java:module/StatefulHello";

    @EJB
    private StatelessBean statelessBean;

    @EJB
    private HelloIF statefulBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        statelessBean.foo();
        statefulBean.hello();
        out.println("After ejb invocation");
        out.flush();
        out.close();
    }

}