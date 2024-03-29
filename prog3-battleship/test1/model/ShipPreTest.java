package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ShipPreTest {
	final static int BOUNDING_SQUARE_SIZE = 5;
	static ArrayList<Coordinate> north, east, south, west;
    static String sNorth, sEast, sSouth, sWest;
	Ship bergantin, goleta, fragata, galeon;
    final static int shape[][] = new int[][] {
	      { 0, 0, 0, 0, 0,
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,
	    	0, 0, 0, 0, 0},
	      { 0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0,	
		    0, 1, 1, 1, 0,	
		    0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0},
	      { 0, 0, 0, 0, 0,
		    0, 0, 1, 0, 0,	
		    0, 0, 1, 0, 0,	
		    0, 0, 1, 0, 0,
		    0, 0, 0, 0, 0},
		  { 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0,	
		    0, 1, 1, 1, 0,	
		    0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0}}; 
		
		    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		    //Coordinates relativas de las distintas orientaciones de un Ship
			north = new ArrayList<Coordinate>();
			east = new ArrayList<Coordinate>();
			south = new ArrayList<Coordinate>();
			west = new ArrayList<Coordinate>();
			for (int i=1; i < 4; i++) {
				north.add(new Coordinate(2,i));
				east.add(new Coordinate(i,2));
				south.add(new Coordinate(2,i));
				west.add(new Coordinate(i,2));
			}
			
			 sEast = "Bergantín (EAST)\n -----\n|     |\n|     |\n| BBB |\n|     |\n|     |\n -----";
			 sNorth ="Goleta (NORTH)\n -----\n|     |\n|  G  |\n|  G  |\n|  G  |\n|     |\n -----";
			 sSouth ="Galeón (SOUTH)\n -----\n|     |\n|  A  |\n|  A  |\n|  A  |\n|     |\n -----";
			 sWest = "Fragata (WEST)\n -----\n|     |\n|     |\n| FFF |\n|     |\n|     |\n -----";
	}	    
		    
		    
		    
		    
	@Before
	public void setUp() throws Exception {
		bergantin = new Ship(Orientation.EAST,'B',"Bergantín");
		goleta = new Ship(Orientation.NORTH,'G',"Goleta");
		fragata = new Ship(Orientation.WEST,'F',"Fragata");
		galeon = new Ship(Orientation.SOUTH,'A',"Galeón");
	}

 
	//TODO testGetPosition
	/* Comprueba que la posición inicial de un Ship es null.
	 * Comprueba también que getPosition hace copia defensiva. Para ello:
	 * 1- Posiciona el Ship en una Coordinate concreta. 
	 * 2- Comprueba que la posición del Ship y la Coordinate son iguales, pero no
	 *    tienen la misma referencia.
	 */
	@Test
	public void testGetPosition() {
		
		if (bergantin.getPosition() != null) {
			fail("No es null");
		}
		
		Coordinate c = new Coordinate(2, 2);
		
		bergantin.setPosition(c);
		
		assertEquals(c, bergantin.getPosition());
		assertNotSame(c, bergantin.getPosition());
		
		// fail("Realiza el test testGetPosition()");
	}

	/* Se comprueba que getName() es correcto. 
	 */
	@Test
	public void testGetName() {
		assertEquals ("Bergantín",bergantin.getName());
		assertEquals ("Fragata",fragata.getName());
	}

	/* Se comprueba que getOrientation() es correcto.
	 */
	@Test
	public void testGetOrientation() {
		assertEquals (Orientation.NORTH, goleta.getOrientation());
		assertEquals (Orientation.EAST, bergantin.getOrientation());
		assertEquals (Orientation.SOUTH, galeon.getOrientation());
		assertEquals (Orientation.WEST, fragata.getOrientation());
	}

	/* Se comprueba que getSymbol() es correcto.
	 */
	@Test
	public void testGetSymbol() {
		assertEquals ('B',bergantin.getSymbol());
		assertEquals ('G',goleta.getSymbol());
		assertEquals ('A',galeon.getSymbol());
		assertEquals ('F',fragata.getSymbol());
	}

	/* Se comprueba que la matriz shape del alumno es correcta 
	 */
	@Test
	public void testGetShape() {
		int [][] shapeAux = goleta.getShape();
		for (int i=0; i< shape.length; i++) 
			for (int j=0; j<shape[i].length; j++)
				assertEquals("shape["+i+"]["+j+"]==shapeAux["+i+"]["+j+"]", shape[i][j],shapeAux[i][j]);
	}

	//TODO testGetShapeIndex
	/* Comprueba, para todas las coordenadas relativas, que getShapeIndex(Coordinate c):
	 * 1- Devuelve un valor entre 0 y 24 (ambos inclusives)
	 * 2- Los correspondientes valores dentro de shape[][] para las distintas orientaciones y 
	 *    los distintos valores devueltos por getShapeIndex, son correctos.
	 */
	@Test
	public void testGetShapeIndex() {
		
		for (Coordinate c : north) {
			if (this.goleta.getShapeIndex(c) >= 0 && this.goleta.getShapeIndex(c) <= 24) {
				assertEquals(1, this.goleta.getShape()[this.goleta.getOrientation().ordinal()][this.goleta.getShapeIndex(c)]);
			}
			else {
				fail("Error");
			}
		}
		
		for (Coordinate c : south) {
			if (this.galeon.getShapeIndex(c) >= 0 && this.galeon.getShapeIndex(c) <= 24) {
				assertEquals(1, this.galeon.getShape()[this.galeon.getOrientation().ordinal()][this.galeon.getShapeIndex(c)]);
			}
			else {
				fail("Error");
			}
		}

		for (Coordinate c : east) {
			if (this.bergantin.getShapeIndex(c) >= 0 && this.bergantin.getShapeIndex(c) <= 24) {
				assertEquals(1, this.bergantin.getShape()[this.bergantin.getOrientation().ordinal()][this.bergantin.getShapeIndex(c)]);
			}
			else {
				fail("Error");
			}
		}

		for (Coordinate c : west) {
			if (this.fragata.getShapeIndex(c) >= 0 && this.fragata.getShapeIndex(c) <= 24) {
				assertEquals(1, this.fragata.getShape()[this.fragata.getOrientation().ordinal()][this.fragata.getShapeIndex(c)]);
			}
			else {
				fail("Error");
			}
		}
		
		// fail("Realiza el test testGetShapeIndex()");
	}

	//TODO testGetAbsolutePositionsNorth
	/* Comprueba que las posiciones absolutas que devuelve el método 
	 * getAbsolutePositions(Coordinate) de un barco con orientación NORTH 
	 * a partir de una Coordinate son correctas. Ten en cuenta que el 
	 * ArrayList estático 'north', declarado al principio, contiene 
	 * las Coordinates relativas del barco para la orientación NORTH.
	 */
	@Test
	public void testGetAbsolutePositionsNorth() {
		
		Coordinate c1 = new Coordinate(13,27);
		Set<Coordinate> pos = goleta.getAbsolutePositions(c1);
		
		Coordinate checkC = null;
		
		for (Coordinate c : north) {
			checkC = c.add(c1);
			assertTrue(pos.contains(checkC));
		}
		
		//fail("Realiza el test GetAbsolutePositionsNorth()");
	}
	
	

	/* Se dispara a un Ship que todavía no ha sido posicionado. Se comprueba que
	 * hit devuelve false
	 */
	@Test
	public void testHitShipPositionNull() {
		Coordinate c1 = new Coordinate(2,1);
		assertFalse(goleta.hit(c1));
	}
	
	//TODO testHitWater
	/* Posiciona un Ship en una Coordinate y realiza disparos al agua.
	 * Comprueba que hit devuelve siempre false
	 */
	@Test
	public void testHitWater() {
		
		this.goleta.setPosition(new Coordinate(2, 2));
		
		assertFalse(this.goleta.hit(new Coordinate(2, 3)));
		
		// fail ("Realiza el test HitWater");
	}
	
	//TODO testHitShip
	/* Posiciona un Ship en una Coordinate, y realiza los primeros disparos a 
	 * las posiciones del Ship y comprueba que hit devuelve true. 
	 * Vuelve a disparar a las mismas posiciones y comprueba que hit ahora 
	 * devuelve false.
	 */
	@Test
	public void testHitShip() {
		
		this.goleta.setPosition(new Coordinate(2, 2));
		
		assertTrue(this.goleta.hit(new Coordinate(4, 3)));
		
		// fail ("Realiza el test HitShip");
	}

	
	//TODO testIsShotDown2
	/* Comprueba que:
	 * 1- isShotDown() devuelve false tras realizar disparos a todas las posiciones del
	 *    Ship excepto una. 
	 * 2- isShotDown() devuelve true tras disparar a la única posición no dañada.
	 */
	@Test
	public void testIsShotDown2() {
		this.bergantin.setPosition(new Coordinate(2, 2));
		// fail ("Realiza el test IsShotDown2");
		
	}
	
	//TODO testIsHit
	/* Comprueba que:	
	 * 1- isHit sobre las Coordinates de un Ship devuelve false.
	 * 2- isHit sobre las Coordinates de un Ship devuelve true después 
	 *    de disparar sobre ellas (hit) 
	 *     
	 */
	@Test
	public void testIsHit() {
		assertFalse(this.goleta.isHit(new Coordinate(4, 3)));
		
		this.goleta.setPosition(new Coordinate(2, 2));
		
		assertFalse(this.goleta.isHit(new Coordinate(4, 3)));
		
		this.goleta.hit(new Coordinate(4, 3));
		
		assertTrue(this.goleta.isHit(new Coordinate(4, 3)));
		
		// fail ("Realiza el test IsHit");
	}

	/* Se comprueba que las salidas de los distintos Ships en distintas orientaciones
	 * son correctas
	 */
	@Test
	public void testToString() {
		System.out.println(sNorth);
		System.out.println(goleta.toString());
		assertEquals(sNorth,goleta.toString());
		assertEquals(sSouth,galeon.toString());
		assertEquals(sEast,bergantin.toString());
		assertEquals(sWest,fragata.toString());
	}
}
