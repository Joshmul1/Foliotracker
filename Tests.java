package junitTests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import model.Folio;
import model.FolioTracker;
import model.Portfolio;
import model.Stock;

class Tests {
	

    //Testing Folio

	@Test
	void testGetFolioName() {
		FolioTracker folioTracker1 = new FolioTracker();
        Folio fol = new Folio("TEST FOLIO 1");
        folioTracker1.addFolio(fol);
        assertEquals(fol.getFolioName(),"TEST FOLIO 1");
	}
	
	@Test
	void testGetTotalHolding() {
		FolioTracker folioTracker2 = new FolioTracker();
        Folio fol2 = new Folio("TEST FOLIO 1");
        folioTracker2.addFolio(fol2);
        fol2.createStock("Ticker", "Saeed", 2, 3);
		
		FolioTracker folioTracker1 = new FolioTracker();
        Folio fol = new Folio("TEST FOLIO 1");
        folioTracker1.addFolio(fol);
        fol.createStock("Ticker", "Saeed", 2, 3);

        assertTrue(fol.getTotalHoldings()== fol2.getTotalHoldings());
	}
	

	
	@Test void testHasTickerTrue() {
		FolioTracker folioTracker1 = new FolioTracker();
        Folio fol = new Folio("TEST FOLIO 1");
        folioTracker1.addFolio(fol);
        fol.createStock("Ticker", "Saeed", 2, 3);

        assertTrue(fol.hasTicker("Ticker")==true);
		
	}
	
	@Test 
	void testHasTickerFalse() {
		FolioTracker folioTracker1 = new FolioTracker();
        Folio fol = new Folio("TEST FOLIO 1");
        folioTracker1.addFolio(fol);
        fol.createStock("Ticker", "Saeed", 2, 3);

        assertFalse(fol.hasTicker("dsflkjsdlk")==true);
	}
	
	
	
	//Testing Stock
	
	@Test
	void testGetStockName() {
		Stock s = new Stock("Ticker", "Saeed", 2, 3);
		assertTrue(s.getStockName()=="Saeed");
	}
	
	@Test
	void testSetStockName() {
		Stock s = new Stock("Ticker", "Saeed", 2, 3);
		s.setStockName("Darren");
		assertTrue(s.getStockName()=="Darren");
		assertFalse(s.getStockName()=="Saeed");
	}
	
	@Test
	void testGetNumberOfShares() {
		Stock s = new Stock("Ticker", "Saeed", 2, 3);
		assertTrue(s.getNumberOfShares()==2);
	}
	
	@Test
	void testSetNumberOfShares() {
		Stock s = new Stock("Ticker", "Saeed", 2, 3);
		s.setNumberOfShares(4);
		assertTrue(s.getNumberOfShares()==4);
		assertFalse(s.getNumberOfShares()==2);
	}
	
	@Test
	void testGetPricePerShare() {
		Stock s = new Stock("Ticker", "Saeed", 2, 3);
		
		assertTrue(s.getPricePerShare()==3);
	}
	
	@Test
	void testSetPricePerShare() {
		Stock s = new Stock("Ticker", "Saeed", 2, 3);
		s.setPricePerShare(5);
		assertTrue(s.getPricePerShare()==5);
		assertFalse(s.getPricePerShare()==3);
	}
	
	@Test
	void  testAddShares() {
		Stock s = new Stock("Ticker", "Saeed", 2, 3);
		s.addShares(3);
		assertTrue(s.getNumberOfShares()==5);
	}
	
		//Test for more than one addition
	@Test
	void testAddShares2() {
		Stock s = new Stock("Ticker", "a", 5, 3);
		s.addShares(5);
		assertEquals(10, s.getNumberOfShares());
		s.addShares(150);
		assertEquals(160, s.getNumberOfShares());
		s.addShares(10);
		assertEquals(170, s.getNumberOfShares());
	}
	
	@Test
	void testRemoveSharesTrue() {
		Stock s = new Stock("Ticker", "Saeed", 2, 3);
		s.removeShares(1);
		assertTrue(s.getNumberOfShares()==1);
	}
	
	@Test
	void testRemoveSharesFalse() {
		Stock s = new Stock("Ticker", "Saeed", 2, 3);
		assertFalse(s.removeShares(3));
	}
	
		@Test 
	void testRemoveSharesMore() {
		Stock s = new Stock("Ticker", "Saeed", 2, 3);
		s.addShares(5);
		s.removeShares(6);
		assertEquals(1, s.getNumberOfShares());
		s.removeShares(1);
		assertEquals(0, s.getNumberOfShares());
		s.addShares(50);
		s.addShares(-15);
		assertEquals(50, s.getNumberOfShares()); 
	}
	
	@Test
	void testGetValueOfHolding() {
		Stock s = new Stock("Ticker", "Saeed", 2, 3);
		assertTrue(s.getValueOfHolding()==6);
	}
	
	//Test FolioTracker
	
	void testFolioTracker() {
		FolioTracker p = new FolioTracker();
		Folio fol = new Folio("TEST FOLIO 1");
		p.addFolio(fol);
		assertTrue(p.getAllFolio().contains(fol));
		
	}
		
	}
	

	
	

}
