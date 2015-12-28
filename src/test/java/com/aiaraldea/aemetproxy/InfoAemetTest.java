package com.aiaraldea.aemetproxy;

import com.aiaraldea.aemetproxy.model.PrediccionAemet;
import com.aiaraldea.aemetproxy.model.PrediccionesAemet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author inaki
 */
public class InfoAemetTest {

    public InfoAemetTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getPrediccion method, of class InfoAemet.
     */
    @Test
    public void testGetPrediccion() throws Exception {
        System.out.println("getPrediccion");
        Integer aemetCode = 44050;
        InfoAemet instance = new InfoAemet();
        PrediccionesAemet result = instance.getPrediccion(aemetCode);
        assertNotNull(result);
        assertNotNull(result.getPrediccionesAemet());
        assertNotSame(0, result.getPrediccionesAemet().size());
//        assertEquals(expResult, result);
        for (PrediccionAemet r : result.getPrediccionesAemet()) {
            System.out.println(r);
        }
    }

}
