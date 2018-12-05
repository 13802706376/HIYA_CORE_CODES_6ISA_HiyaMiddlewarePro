package com.hiya.middleware;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
public class CustomServerByJetty extends AbstractHandler
{

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        System.out.println(target);
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        PrintWriter out = response.getWriter();
        if (target.equals("/favicon.ico"))
        {
            System.out.println(1);
            out.println("404");
        } else
        {
            System.out.println(2);
            out.println("hello jetty");
            if (request.getParameter("name") != null)
            {
                out.println(request.getParameter("name"));
            }
        }
    }
    
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        server.setHandler(new CustomServerByJetty());
        server.start();
        server.join();  
    }
}