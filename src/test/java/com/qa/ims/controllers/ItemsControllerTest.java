package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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
		
		Mockito.when(utils.getLong()).thenReturn(ID, STOCK);
		Mockito.when(utils.getString()).thenReturn(I_NAME);
		Mockito.when(utils.getDouble()).thenReturn(PRICE);
		
		Mockito.when(itemsDAO.create(Mockito.any(Items.class))).thenReturn(created);
		
		assertEquals(created, controller.create());
		
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(itemsDAO, Mockito.times(1)).create(Mockito.any(Items.class));
		
	}
	
	@Test
	public void testReadAll() {
		List<Items> items = new ArrayList<>();
		items.add(new Items(1L, "Stardew Valley", 356L, 8.99D));
		
		Mockito.when(itemsDAO.readAll()).thenReturn(items);
		
		assertEquals(items, controller.readAll());
		
		Mockito.verify(itemsDAO, Mockito.times(1)).readAll();
	}
	
	@Test
	public void testUpdate() {
		Items updated = new Items(1L, "Star Citizen", 353L, 43.99D);
		
		Mockito.when(this.utils.getLong()).thenReturn(1L, 353L);
		Mockito.when(this.utils.getString()).thenReturn(updated.getItemName());
		Mockito.when(this.utils.getLong()).thenReturn(353L);
		Mockito.when(this.utils.getDouble()).thenReturn(43.99D);
		
		Mockito.when(itemsDAO.update(Mockito.any(Items.class))).thenReturn(updated);

		
		assertEquals(updated, this.controller.update());
		
		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getDouble();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
	}
	
	@Test
	public void testDelete() {
		final long item_id = 1L;
		Mockito.when(utils.getLong()).thenReturn(item_id);
		Mockito.when(itemsDAO.delete(item_id)).thenReturn(1);
		
		assertEquals(1L, this.controller.delete());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(itemsDAO, Mockito.times(1)).delete(item_id);
		
	}

}
