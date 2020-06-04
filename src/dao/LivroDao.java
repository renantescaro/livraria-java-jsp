package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Editora;
import entity.Empresa;
import com.mysql.jdbc.PreparedStatement;

public class LivroDao{
	
	public List<Editora> selecionarTudo() throws SQLException{
		
		Conexao conexao = new Conexao();
		EmpresaDao empresaDao = new EmpresaDao();
		
		String sql = "SELECT * FROM livro";
		
		conexao.conectar();
		PreparedStatement statement = (PreparedStatement) conexao.cnx.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		
		List<Editora> editoras = new ArrayList<Editora>();
		
		while(rs.next()){
			Editora editora = new Editora();
			Empresa empresa = empresaDao.selecionarPorId(rs.getInt("empresa_id"));
			
			editora.setId(rs.getInt("id"));
			editora.setEmpresa(empresa);
			
			editoras.add(editora);
		}
		conexao.fecharConexao();
		return editoras;
	}
	
	public Editora selecionarPorId(int id) throws SQLException{
		
		Conexao conexao = new Conexao();
		Editora editora = new Editora();
		EmpresaDao empresaDao = new EmpresaDao();
		
		String sql = "SELECT * FROM editora WHERE id=?";

		conexao.conectar();
		PreparedStatement statement = (PreparedStatement) conexao.cnx.prepareStatement(sql);
		
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		
		if(rs != null){		
			rs.next();
			
			Empresa empresa = empresaDao.selecionarPorId(rs.getInt("empresa_id"));
			
			editora.setId(rs.getInt("id"));
			editora.setEmpresa(empresa);
		}		
		conexao.fecharConexao();
		return editora;
	}
	
	public boolean inserir(Editora editora) throws SQLException {
		
		Conexao conexao = new Conexao();
		
		String sql = "INSERT INTO editora(empresa_id) "+
					 "VALUES(?)";
		
		conexao.conectar();
		PreparedStatement statement = (PreparedStatement) conexao.cnx.prepareStatement(sql);
		
		statement.setInt(1, editora.getEmpresa().getId());
		return statement.execute();
	}
	
	public boolean deletarPorId(int id) throws SQLException{
		
		Conexao conexao = new Conexao();
		
		String sql = "DELETE FROM editora WHERE id=?";

		conexao.conectar();
		PreparedStatement statement = (PreparedStatement) conexao.cnx.prepareStatement(sql);
		
		statement.setInt(1, id);
		return statement.execute();
	}
	
	public boolean atualizar(Editora editora) throws SQLException{
		
		Conexao conexao = new Conexao();
		String sql = "UPDATE editora SET empresa_id=? WHERE id=?";

		conexao.conectar();
		PreparedStatement statement = (PreparedStatement) conexao.cnx.prepareStatement(sql);
		
		statement.setInt(1, editora.getEmpresa().getId());
		statement.setInt(2, editora.getId());
		
		return statement.execute();
	}
}
