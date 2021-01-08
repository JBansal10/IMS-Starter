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
		final Items expected = new Items(4L, "Cyberpunk 2077", 476L, 19.62D);
		assertEquals(expected, DAO.create(created));
	}
	
	@Test
	public void testReadAll() {
		List<Items> expected = new ArrayList<>();
		expected.add(new Items(1L, "Valhalla", 476L, 19.62D));
		expected.add(new Items(2L, "Legion", 356L, 8.99D));
		expected.add(new Items(3L, "Piers Humour", 678L, 0.01D));
		assertEquals(expected,DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(new Items(3L, "Piers Humour", 678L, 0.01D),DAO.readLatest());
	}
	
	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Items(ID, "Valhalla", 476L, 19.62D),DAO.readItems(ID));
	}	
	@Test
	public void testUpdate() {
		final Items updated = new Items(1L, "Dirt 5", 256L, 32.76D);
		assertEquals(updated, DAO.update(updated));
	}
	
	@Test
	public void testDelete() {
		assertEquals(1L, DAO.delete(1L));
	}
	
}
