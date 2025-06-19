/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.ClassDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.ClassModel;

/**
 *
 * @author Nguyễn Hùng
 */
@WebServlet(name = "ClassServlet", urlPatterns = {"/class"})
public class ClassServlet extends HttpServlet {
  private ClassDAO classDAO;
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
            out.println("<title>Servlet ClassServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClassServlet at " + request.getContextPath() + "</h1>");
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
    public void init() throws ServletException {
        classDAO = new ClassDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String action = request.getParameter("action");
    if (action == null) action = "list";

    switch (action) {
        case "delete":
            deleteClass(request, response);
            break;
        default:
            listClass(request, response);
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
         String action = request.getParameter("action");
    if (action == null) action = "";

    switch (action) {
        case "insert":
            insertClass(request, response);
            break;
        case "update":
            updateClass(request, response);
            break;
        default:
            response.sendRedirect("class");
            break;
    }
    }
    
    
   // Hiển thị danh sách lớp (được gọi khi vào /class hoặc sau khi thêm/sửa/xóa)
private void listClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<ClassModel> list = classDAO.getAllClasses();
    request.setAttribute("classList", list);
    RequestDispatcher dispatcher = request.getRequestDispatcher("class-list.jsp");
    dispatcher.forward(request, response);
}

// Thêm lớp học (submit từ modal thêm trong class-list.jsp)
private void insertClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String classId = request.getParameter("classId");
    String name = request.getParameter("name");
    String department = request.getParameter("department");

    ClassModel c = new ClassModel(classId, name, department);
    classDAO.insertClass(c);

    // Quay lại trang danh sách sau khi thêm
    response.sendRedirect("class");
}

// Cập nhật lớp học (submit từ modal sửa trong class-list.jsp)
private void updateClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String classId = request.getParameter("classId");
    String name = request.getParameter("name");
    String department = request.getParameter("department");

    ClassModel c = new ClassModel(classId, name, department);
    classDAO.updateClass(c);

    // Quay lại trang danh sách sau khi cập nhật
    response.sendRedirect("class");
}

// Xoá lớp học (khi bấm nút xóa trong class-list.jsp)
private void deleteClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String id = request.getParameter("id");
    classDAO.deleteClass(id);

    // Quay lại danh sách sau khi xóa
    response.sendRedirect("class");
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
