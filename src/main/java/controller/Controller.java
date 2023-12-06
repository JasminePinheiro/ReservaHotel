//sistema reserva de hotel
package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/data", "/delete", "/home" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       DAO dao = new DAO();
       JavaBeans cadastro = new JavaBeans();
    public Controller() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println("ação: " + action);
		if (action.equals("/main")) {
			receberQuartoId(request, response);
		}else if(action.equals("/insert")){
			novoCadastro(request, response);
		}else if (action.equals("/data")) {
			data(request, response);
		}else if (action.equals("/delete")) {
			removerReservas(request, response);
		}else if (action.equals("/home")) {
			quarto(request, response);
		}else {
			response.sendRedirect("index.html");
		}
	}
	
	//redirecionar para o documento cadastro.html
		protected void receberQuartoId(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			//recuperando o id do quarto escolhido
			String quartoId = request.getParameter("quarto");
			cadastro.setQuarto_id(quartoId);
			System.out.println("id do quarto: " + cadastro.getQuarto_id());
			//redirecionando para cadastro.html
			 response.sendRedirect("cadastro.html");
		}
	
	//novo cadastro
	protected void novoCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*//teste de recebimento dos dados do formulário, cada parâmetro é o nome de uma caixa de texto
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("telefone"));
		System.out.println(request.getParameter("email"));*/
		//setar as variáveis JavaBeans
		cadastro.setNome(request.getParameter("nome"));
		cadastro.setTelefone(request.getParameter("telefone"));
		cadastro.setEmail(request.getParameter("email"));
		cadastro.setSenha(request.getParameter("senha"));
		//invocar o método inserirCadastro passando o objeto cadastro
		dao.inserirCadastro(cadastro);
		//testando se foi recuperado do banco de dados o cliente_id
		System.out.println("id do cliente: " + cadastro.getCliente_id());
		//redirecionar para o documento data.jsp
		RequestDispatcher rd = request.getRequestDispatcher("data.jsp");
		rd.forward(request, response);		
		return;
	}
	
	//recebendo data reserva
	protected void data(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*//teste de recebimento da data de reserva
		System.out.println(request.getParameter("data"));*/
		//setar a variável JavaBeans
		cadastro.setData_inicio(request.getParameter("data_inicio"));
		cadastro.setData_fim(request.getParameter("data_fim"));
		cadastro.setDisponivel(false);
		//invocar o método inserirReserva passando o objeto cadastro
		dao.inserirReserva(cadastro);
		//listar reservas
		listarReservas(request, response);
		//redirecionar para reservado.jsp
		RequestDispatcher rd = request.getRequestDispatcher("reservado.jsp");
		rd.forward(request, response);
		
	}
	
	//listar reservas
	protected void listarReservas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//criar um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarReservas(cadastro);
		/*//teste de recebimento da lista
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).getData_inicio());
			System.out.println(lista.get(i).getReserva_id());			
		}*/		
		//Encaminhar a lista de reserva ao documento reservado.jsp
		request.setAttribute("reserva", lista);
		RequestDispatcher rd = request.getRequestDispatcher("reservado.jsp");
		rd.forward(request, response);
	}
	
	//Remover reservas
	protected void removerReservas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recebimento do id da reserva a ser excluida (confirmador.js)
		String reserva_id = request.getParameter("reserva_id");
		//testando se foi recebido o id da reserva
		System.out.println("id da reserva a ser excluida: " + reserva_id);
		//setar a variável idcon JavaBeans
		cadastro.setReserva_id(reserva_id);
		//executar metodo deletar reserva passando cadastro como parâmetro
		dao.deletarReserva(cadastro);
		//redirecionar para o documento cancelamento.html
		RequestDispatcher rd = request.getRequestDispatcher("cancelamento.html");
		rd.forward(request, response);
	}
	
	//redirecionar para quarto.html
	protected void quarto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recuperando o id do hotel escolhido
		String hotelId = request.getParameter("hotel");
		cadastro.setHotel_id(hotelId);
		System.out.println("id do hotel: " + cadastro.getHotel_id());
		RequestDispatcher rd = request.getRequestDispatcher("quarto.html");
		rd.forward(request, response);
	}
	
}
