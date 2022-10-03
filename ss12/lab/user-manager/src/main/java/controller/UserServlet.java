package controller;

import model.User;
import service.IUserService;
import service.impl.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = {"/user", ""})
public class UserServlet extends HttpServlet {
    private static IUserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null) {
            action = "";
        }
        switch (action) {
            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("id",id);
                User user = userService.getUserById(id);
                request.setAttribute("name", user.getName());
                request.setAttribute("email", user.getEmail());
                request.setAttribute("country", user.getCountry());
                request.getRequestDispatcher("user/edit.jsp").forward(request,response);
                break;
            case "add":
                response.sendRedirect("/user/add.jsp");
                break;
            case "delete":
                delete(request, response);
                break;
            case "sort":
                sortByName(request, response);
                break;
            default:
                listUser(request, response);
                break;
        }
    }

    private void sortByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       List<User> userListSortByName =  userService.sortByName();
       listUser(request, response, userListSortByName);

    }



    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        userService.delete(id);
        response.sendRedirect("/user");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null) {
            action = "";
        }
        switch (action) {
            case "update":
                update(request, response);
                break;
            case "create":
                create(request, response);
                break;
            case "search":
                search(request, response);
                break;
            default:
                listUser(request, response);
                break;
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        List<User> findUser = null;
        if(search.equals("")) {
            findUser = userService.getAll();
        } else {
            findUser = userService.search(search);
        }
        listUser(request, response, findUser);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        User user = new User(name, email, country);
        String mess="Add fail!";
        if(userService.add(user)){
            mess = "Add success!";
        }
        request.setAttribute("mess", mess);
        request.getRequestDispatcher("/user/add.jsp").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        if(userService.getUserById(id) != null) {
            User user = new User(id, name, email, country);
            userService.update(id, user);
        }
        response.sendRedirect("/user");
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = userService.getAll();
        request.setAttribute("users", userList);
        request.getRequestDispatcher("/user/list.jsp").forward(request, response);
    }
    private void listUser(HttpServletRequest request, HttpServletResponse response, List<User> userList) throws ServletException, IOException {
        request.setAttribute("users", userList);
        request.getRequestDispatcher("/user/list.jsp").forward(request, response);
    }

}
