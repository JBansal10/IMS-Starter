package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DBUtils;

public class ItemsDAOTest {
	
	private final ItemsDAO DAO = new ItemsDAO();
	
	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "root");
	}
	
	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
		}
	
	
	@Test
	public void testCreate() {
		final Items created = new Items("Cyberpunk 2077", 476L, 19.62D);
		final Items expected = new Items(2L, "Cyberpunk 2077", 476L, 19.62D);
		assertEquals(expected, DAO.create(created));
	}
	
	@Test
	public void testReadAll() {
		List<Items> expected = new ArrayList<>();
		expected.add(new Items(1L, "Cyberpunk 2077", 476L, 19.62D));
		assertEquals(expected,DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(new Items(1L, "Cyberpunk 2077", 476L, 19.62D),DAO.readItems(1L));
	}
}
