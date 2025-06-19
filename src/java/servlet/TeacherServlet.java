/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.TeacherDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.TeacherModel;

/**
 *
 * @author Nguyễn Hùng
 */
@WebServlet(name = "TeacherServlet", urlPatterns = {"/teacher"})
public class TeacherServlet extends HttpServlet {
private TeacherDAO teacherDAO;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TeacherServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TeacherServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void init() {
        teacherDAO = new TeacherDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
    if (action == null) action = "list";

    switch (action) {
        case "delete":
            deleteTeacher(request, response);
            break;
        default:
            listTeachers(request, response);
            break;
    }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
    String action = request.getParameter("action");

    switch (action) {
        case "insert":
            insertTeacher(request, response);
            break;
        case "update":
            updateTeacher(request, response);
            break;
        default:
            listTeachers(request, response);
            break;
    }
    }
    
     // Lấy danh sách tất cả giáo viên và chuyển tiếp đến JSP chính (giao diện đã gộp)
private void listTeachers(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    List<TeacherModel> teacherList = teacherDAO.getAllTeachers();
    request.setAttribute("teacherList", teacherList);
    RequestDispatcher dispatcher = request.getRequestDispatcher("teacher.jsp");
    dispatcher.forward(request, response);
}

// Thêm giáo viên
private void insertTeacher(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
    String id = request.getParameter("teacherId");
    String name = request.getParameter("name");
    String gender = request.getParameter("gender");
    String subject = request.getParameter("subject");

    TeacherModel teacher = new TeacherModel(id, name, gender, subject);
    teacherDAO.insertTeacher(teacher);
    response.sendRedirect("teacher");
}

// Cập nhật thông tin giáo viên
private void updateTeacher(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
    String id = request.getParameter("teacherId");
    String name = request.getParameter("name");
    String gender = request.getParameter("gender");
    String subject = request.getParameter("subject");

    TeacherModel teacher = new TeacherModel(id, name, gender, subject);
    teacherDAO.updateTeacher(teacher);
    response.sendRedirect("teacher");
}

// Xóa giáo viên
private void deleteTeacher(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
    String id = request.getParameter("id");
    teacherDAO.deleteTeacher(id);
    response.sendRedirect("teacher");
}

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
