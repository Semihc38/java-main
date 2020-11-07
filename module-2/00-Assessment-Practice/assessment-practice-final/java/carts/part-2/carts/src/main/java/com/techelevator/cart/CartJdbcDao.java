package com.techelevator.cart;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class CartJdbcDao implements CartDao {

	private JdbcTemplate jdbcTemplate;

	public CartJdbcDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// To insert a date into a prepared statement, wrap the date in `Date.valueOf()`
	// method: `Date.valueOf(myObject.getCreated())`

	@Override
	public void save(Cart newCart) {
		String sqlInsertCart =
				"INSERT INTO carts(username, cookie_value, created) "
						+ "VALUES(?, ?, ?)";
		jdbcTemplate.update(sqlInsertCart, newCart.getUsername(),
				newCart.getCookieValue(), Date.valueOf(newCart.getCreated()));
	}

	@Override
	public List<Cart> getAllCarts() {
		List<Cart> allCarts = new ArrayList<>();
		String sqlGetAllCarts = "SELECT id, username, cookie_value, created FROM carts";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllCarts);
		while(results.next()) {
			allCarts.add(mapRowToCart(results));
		}

		return allCarts;
	}
	private Cart mapRowToCart(SqlRowSet results) {
		Cart cartRow = new Cart();
		cartRow.setId(results.getLong("id"));
		cartRow.setUsername(results.getString("username"));
		cartRow.setCookieValue(results.getString("cookie_value"));
		cartRow.setCreated(results.getDate("created").toLocalDate());
		return cartRow;
	}

}
