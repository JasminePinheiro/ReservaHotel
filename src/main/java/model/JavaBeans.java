//sistema reserva de hotel
package model;

public class JavaBeans {
	private String cliente_id;
	private String nome;
	private String email;
	private String telefone;
	private String senha;
	private String quarto_id;
	private String hotel_id;
	private String preco;
	private boolean disponivel;
	private String reserva_id; 
	private String data_inicio;
	private String data_fim;
	private String valor_total;
	
	public JavaBeans() {
		super();
	}
	
	public JavaBeans(String reserva_id2, String data_inicio2) {
		super();
		this.data_inicio = data_inicio2;
		this.reserva_id = reserva_id2;
	}
	
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(String hotel_id) {
		this.hotel_id = hotel_id;
	}

	public String getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(String cliente_id) {
		this.cliente_id = cliente_id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getQuarto_id() {
		return quarto_id;
	}
	public void setQuarto_id(String quarto_id) {
		this.quarto_id = quarto_id;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public boolean getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	public String getReserva_id() {
		return reserva_id;
	}
	public void setReserva_id(String reserva_id) {
		this.reserva_id = reserva_id;
	}
	public String getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(String data_inicio) {
		this.data_inicio = data_inicio;
	}
	public String getData_fim() {
		return data_fim;
	}
	public void setData_fim(String data_fim) {
		this.data_fim = data_fim;
	}
	public String getValor_total() {
		return valor_total;
	}
	public void setValor_total(String valor_total) {
		this.valor_total = valor_total;
	}
	
	
}
