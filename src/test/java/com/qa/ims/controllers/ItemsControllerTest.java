package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;

import com.qa.ims.controller.ItemsController;
import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemsControllerTest {
	
	@Mock
	private Utils utils;
	
	@Mock
	private ItemsDAO itemsDAO;
	
	@InjectMocks
	private ItemsController controller;
	
	@Test
	public void testCreate() {
		final Long ID = 1L;
		final String I_NAME = "Minecraft";
		final Long STOCK = 355L;
		final Double PRICE = 15.99D;
		final Items created = new Items(ID, I_NAME, STOCK, PRICE);
		
		when(utils.getLong()).thenReturn(ID, STOCK);
		when(utils.getString()).thenReturn(I_NAME);
		when(utils.getDouble()).thenReturn(PRICE);
		
		when(itemsDAO.create(any(Items.class))).thenReturn(created);
		
		assertEquals(created, controller.create());
		
		verify(utils, times(1)).getString();
		verify(utils, times(1)).getLong();
		verify(utils, times(1)).getDouble();
		verify(itemsDAO, times(1)).create(any(Items.class));
		
	}
	
	@Test
	public void testReadAll() {
		List<Items> items = new ArrayList<>();
		items.add(new Items(1L, "Stardew Valley", 356L, 8.99D));
		
		when(itemsDAO.readAll()).thenReturn(items);
		
		assertEquals(items, controller.readAll());
		
		verify(itemsDAO, times(1)).readAll();
	}
	
	@Test
	public void testUpdate() {
		Items updated = new Items(1L, "Star Citizen", 353L, 43.99D);
		
		when(this.utils.getLong()).thenReturn(1L, 353L);
		when(this.utils.getString()).thenReturn(updated.getItemName());
		when(this.utils.getLong()).thenReturn(353L);
		when(this.utils.getDouble()).thenReturn(43.99D);
		
		when(itemsDAO.update(any(Items.class))).thenReturn(updated);

		
		assertEquals(updated, this.controller.update());
		
		verify(this.utils, times(2)).getLong();
		verify(this.utils, times(1)).getDouble();
		verify(this.utils, times(1)).getString();
	}
	
	@Test
	public void testDelete() {
		final long item_id = 1L;
		when(utils.getLong()).thenReturn(item_id);
		when(itemsDAO.delete(item_id)).thenReturn(1);
		
		assertEquals(1L, this.controller.delete());
		
		verify(utils, times(1)).getLong();
		verify(itemsDAO, times(1)).delete(item_id);
		
	}

}
