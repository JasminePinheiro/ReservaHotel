//sistema reserva de hotel
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Statement;

public class DAO {
	/*modulo de conexao*/
	//parametros de conexao
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/hotel?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "123456";
	//metodo de conexao
		private Connection conectar() {
			Connection con = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, user, password);
				return con;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
		
		//teste de conexão
		//public void testeConexao() {
			//try {
				//Connection con = conectar();
				//System.out.println(con);
				//con.close();
			//} catch (Exception e) {
				//System.out.println(e);
			//}
		//}
		
		/*CRUD CREATE*/
		public void inserirCadastro(JavaBeans cadastro) {
			String create = "insert into clientes (nome, telefone, email, senha) values (?,?,?,?)";
			try {
				//abrir a conexão
				Connection con = conectar();
				//preparar a query para execusão no banco de dados
				PreparedStatement pst = con.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
				//substituir os parêmetros (?) pelo conteúdo das variáveis JavaBeans
				pst.setString(1, cadastro.getNome());
				pst.setString(2, cadastro.getTelefone());
				pst.setString(3, cadastro.getEmail());
				pst.setString(4, cadastro.getSenha());
				//executar a query e obter a chave primaria gerada automaticamente
				pst.executeUpdate();
				ResultSet keys = pst.getGeneratedKeys();
				// Se houver chaves geradas, definir o ID do cliente no objeto cadastro
		        if (keys.next()) {
		            cadastro.setCliente_id(keys.getString(1));
		        }
		        // Fechar recursos
		        keys.close();
		        pst.close();
				//encerrar a conexão com o banco
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		public void inserirReserva(JavaBeans reserva) {
			   String checkConflictQuery = "SELECT * FROM reservas " +
			                               "WHERE hotel_id = ? AND quarto_id = ? " +
			                               "AND ((? BETWEEN data_inicio AND data_fim) OR (? BETWEEN data_inicio AND data_fim))";

			   String insertReservaQuery = "INSERT INTO reservas (cliente_id, hotel_id, quarto_id, disponivel, data_inicio, data_fim) " +
			                               "VALUES (?, ?, ?, ?, ?, ?)";

			   try {
			      Connection con = conectar();

			      // Verificar se há conflito de datas para o mesmo quarto e hotel
			      PreparedStatement checkConflictStmt = con.prepareStatement(checkConflictQuery);
			      checkConflictStmt.setString(1, reserva.getHotel_id());
			      checkConflictStmt.setString(2, reserva.getQuarto_id());
			      checkConflictStmt.setString(3, reserva.getData_inicio());
			      checkConflictStmt.setString(4, reserva.getData_fim());
			      ResultSet conflictResult = checkConflictStmt.executeQuery();

			      if (conflictResult.next()) {
			         // Conflito de datas, a sala já está reservada para esse período
			         System.out.println("Conflito de datas. A sala já está reservada para esse período.");
			         // Adicione aqui a lógica para lidar com o conflito, como retornar uma mensagem de erro para o usuário.
			      } else {
			         // Sem conflito, pode inserir a reserva
			         PreparedStatement insertReservaStmt = con.prepareStatement(insertReservaQuery);
			         insertReservaStmt.setString(1, reserva.getCliente_id());
			         insertReservaStmt.setString(2, reserva.getHotel_id());
			         insertReservaStmt.setString(3, reserva.getQuarto_id());
			         insertReservaStmt.setBoolean(4, true); // Pode ajustar conforme necessário
			         insertReservaStmt.setString(5, reserva.getData_inicio());
			         insertReservaStmt.setString(6, reserva.getData_fim());

			         insertReservaStmt.executeUpdate();

			         System.out.println("Reserva realizada com sucesso!");
			      }

			      con.close();
			   } catch (Exception e) {
			      System.out.println(e);
			   }
			}
		
		/*CRUD READ*/
		public ArrayList<JavaBeans> listarReservas(JavaBeans cadastro){
			String cliente_Id = cadastro.getCliente_id();
			//Criando um objeto para acessar a classe JavaBeans
			ArrayList<JavaBeans> reserva = new ArrayList<>();
			String read = ("SELECT reserva_id, data_inicio FROM reservas WHERE cliente_id = ?");
			try {
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, cliente_Id);
				ResultSet rs = pst.executeQuery();
				//o laço abaixo será executado enquanto houver reservas
				while(rs.next()) {
					//variaveis de apoio que recebe os dados do banco
					String reserva_id = rs.getString(1);
					String data_inicio = rs.getString(2);
					//populando o ArrayList
					reserva.add(new JavaBeans(reserva_id, data_inicio));
				}
				con.close();
				return reserva;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
		
		/*CRUD DELETE*/
		public void deletarReserva(JavaBeans cadastro) {
			String delete = "delete from reservas where reserva_id=?";
			try {
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, cadastro.getReserva_id());
				pst.executeUpdate();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
}
