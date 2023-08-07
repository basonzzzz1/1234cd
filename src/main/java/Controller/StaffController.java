package Controller;

import Sevice.DepartmentSevice;
import Sevice.StaffSevice;
import model.Department;
import model.Staff;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StaffController", value = "/staffs")
public class StaffController extends HttpServlet {
    StaffSevice staffSevice = new StaffSevice();
    DepartmentSevice departmentSevice = new DepartmentSevice();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "findAll":
                try {
                    showHome(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "search":
                try {
                    showHome(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "edit":
                try {
                    showEditForm(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try {
                    deleteStudent(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "create":
                try {
                    showFormCreate(request , response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            default:
                response.sendRedirect(request.getContextPath() + "/staffs?action=findAll");
                break;
        }
    }
    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Department> departments = departmentSevice.findAll();
        request.setAttribute("departments", departments);
        RequestDispatcher dispatcher = request.getRequestDispatcher("create.jsp");
        dispatcher.forward(request, response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Staff staff = staffSevice.findById(id);
        if (staff != null) {
            request.setAttribute("staff", staff);
            List<Department> departments = departmentSevice.findAll();
            request.setAttribute("departments", departments);
            RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/staffs?action=findAll");
        }
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        staffSevice.delete(id);
        response.sendRedirect(request.getContextPath() + "/staffs?action=findAll");
    }
    private void showHome(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String action = request.getParameter("action");
        List<Staff> staffList = null;
        if(action.equals("findAll")){
            staffList = staffSevice.findAll();
        } else if (action.equals("search")) {
            String name = request.getParameter("nameSearch");
            staffList = staffSevice.findByName(name);
        }
        request.setAttribute("staffList", staffList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "edit":
                try {
                    editStudent(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "create":
                try {
                    createStudent(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
    private void editStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int staffId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        float salary = Float.parseFloat(request.getParameter("Salary"));
        String address = request.getParameter("address");
        int phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));
        int id = Integer.parseInt(request.getParameter("className"));
        Department classRoom = new Department(id);
        Staff staff = new Staff(name, salary, email, address, phoneNumber, classRoom);
        staffSevice.edit(staffId, staff);
        response.sendRedirect(request.getContextPath() + "/staff?action=findAll");
    }
    private void createStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        float salary = Float.parseFloat(request.getParameter("Salary"));
        String address = request.getParameter("address");
        int phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));
        int id = Integer.parseInt(request.getParameter("className"));
        Department classRoom = new Department(id);
        Staff staff = new Staff(name, salary, email, address, phoneNumber, classRoom);
        staffSevice.add(staff);
        response.sendRedirect(request.getContextPath() + "/staffs?action=findAll");
    }
}