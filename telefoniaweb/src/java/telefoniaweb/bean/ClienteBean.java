package telefoniaweb.bean;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import telefoniaweb.dao.ClienteDAO;
import telefoniaweb.model.Cliente;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Charlito
 */
@WebServlet(name = "ClienteBean", urlPatterns = {"/"})
public class ClienteBean extends HttpServlet {
    
    private ClienteDAO clienteDAO;
    
    @Override
    public void init(){
        clienteDAO = new ClienteDAO();
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        
    
    
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getServletPath();
        
        try {            
            switch(action){
                case "/listar":
                    listar(request, response);
                    break;
                case "/editar":
                    mostrar(request, response);
                    break;
                case "/excluir":
                    excluir(request, response);
                    break;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
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
        
        String action = request.getServletPath();
        
        try {   
            
            switch(action){

                case"/inserir":
                    inserir(request, response);
                    break; 
                case "/editar":
                    editar(request, response);
                    break;                     
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }    
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
   public void inserir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {     
        Cliente cliente = new Cliente(request.getParameter("nome"), request.getParameter("sexo").charAt(0), request.getParameter("telefone"), request.getParameter("email"));
                
        String mensagem;    
        int status = clienteDAO.inserirCliente(cliente);

        if (status > 0) {
            mensagem = "Cliente inserido com sucesso!";
        } else {
            mensagem = "Não foi possível inserir o cliente!";
        }
        response.sendRedirect("listar?mensagem=" + mensagem);    
        
        //RequestDispatcher dispatcher = request.getRequestDispatcher("cliente.jsp");
        //dispatcher.forward(request, response);
    }

  public void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = clienteDAO.listarClientes();
        String mensagem = request.getParameter("mensagem");
        request.setAttribute("mensagem", mensagem);
        request.setAttribute("clientes", clientes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("clientes.jsp");
        dispatcher.forward(request, response);
    }
    
    public void mostrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        int id = Integer.parseInt(request.getParameter("id"));                      
        Cliente cliente = clienteDAO.buscarClientes(id);
        request.setAttribute("cliente", cliente);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("cliente.jsp");
        dispatcher.forward(request, response);
    }
     
    public void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        Cliente cliente = new Cliente(request.getParameter("nome"),request.getParameter("sexo").charAt(0), request.getParameter("telefone"), request.getParameter("email"));
        int codigo = Integer.parseInt(request.getParameter("id"));
        
        int id = Integer.parseInt(request.getParameter("id"));
        cliente.setId(id);
        String mensagem;
        
        int status = clienteDAO.editarCliente(cliente);
        
        if (status > 0) {
            mensagem = "Cliente editado com sucesso!";
        } else {
            mensagem = "Não foi possível editar o cliente!";
        }
        response.sendRedirect("listar?mensagem=" + mensagem);      
    }
    
    public void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        int id = Integer.parseInt(request.getParameter("id"));        
        String mensagem;
        
        int status = clienteDAO.excluirCliente(id);

        if (status > 0) {
            mensagem = "Cliente excluido com sucesso!";           
        } else {
            mensagem = "Não foi possível excluir o cliente!";           
        }
        response.sendRedirect("listar?mensagem=" + mensagem);    
    }
}

