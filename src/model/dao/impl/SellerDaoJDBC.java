package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

	// injeção de dependencia
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ? ");

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) { // a linha 0 da tabela é vazia, dados da linha 1 em diante

				Department dpto = intanciateDpartament(rs);

				Seller sel = instanceSeller(rs, dpto);

				return sel;

			} else {
				return null; // return somente 0 sig que não há elemento na tabela com id
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			// não precisa fechar a conn este pode ser usado para outros mtd
		}

	}

	private Seller instanceSeller(ResultSet rs, Department dpto) throws SQLException {

		Seller sel = new Seller();

		sel.setId(rs.getInt("Id"));
		sel.setName(rs.getString("Name"));
		sel.setEmail(rs.getString("Email"));
		sel.setBirthDate(rs.getDate("BirthDate"));
		sel.setBaseSalay(rs.getDouble("BaseSalary"));
		sel.setDepartment(dpto);
		return sel;
	}

	private Department intanciateDpartament(ResultSet rs) throws SQLException {

		Department dpto = new Department(); // nedd bring dpto relacionado com Seller

		dpto.setId(rs.getInt("DepartmentId")); // find dpto relacionado com Seller
		dpto.setName(rs.getString("DepName"));
		return dpto;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartament(Department department) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(

					"SELECT seller.*,department.Name as DepName " + 
					"FROM seller INNER JOIN department "+
					"ON seller.DepartmentId = department.Id "+ 
					"WHERE DepartmentId = ? "+
					"ORDER BY Name "
					);
			
			st.setInt(1, department.getId());
			rs = st.executeQuery();

			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = intanciateDpartament(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}

				Seller obj = instanceSeller(rs, dep);
				list.add(obj);
			}

			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

}
