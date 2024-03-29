package model;

//@author ADRIÁN BERENGUER AGULLÓ, 74445262N

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoardPreTest {

	static final int MAX_BOARD_SIZE = 20;
	static final int  MIN_BOARD_SIZE = 5;
	final static int DIM = 10;
	Board board;
	Ship fragata, galeon, bergantin, goleta;
	static String sboardEmpty,sboard, sboardHide1, sboardHits1,
				sboardHits2,sboardHits3, sboardHide2; //= new String();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sboardHide1 = "?????\n?????\n?????\n?????\n?????";
		
		sboardHide2 = "A ??•\n" + 
			      "A ?? \n" + 
			      "A  ??\n" + 
			      "   ?•\n" + 
			      "?•??•";
		
		sboardEmpty = "     \n     \n     \n     \n     ";
		
		sboard="A FFF\n" + 
			   "A    \n" + 
			   "A   G\n" + 
			   "    G\n" + 
			   "BBB G";
		
		sboardHits1 ="• FFF\n" + 
				     "•    \n" + 
				     "•   G\n" + 
				     "    G\n" + 
				     "BBB G";
		
		sboardHits2 ="• FF•\n" + 
				     "•    \n" + 
				     "•   G\n" + 
				     "    •\n" + 
				     "BBB •";
		
		sboardHits3 = "• FF•\n" + 
				      "•    \n" + 
				      "•   G\n" + 
				      "    •\n" + 
				      "B•B •";
		
	}

	@Before
	public void setUp() throws Exception {
		fragata = new Ship(Orientation.WEST,'F',"Barbanegra");
		galeon = new Ship(Orientation.SOUTH,'A',"Francis Drake");
		bergantin = new Ship(Orientation.EAST,'B',"Benito Soto");
		goleta = new Ship(Orientation.NORTH,'G',"Hook");
		board = new Board(DIM);
		
	}

	//TODO testBoardGetSize
	/* Comprueba los límites del tamaño en el constructor,
	 * tanto dentro como justo fuera. Comprueba que al superarlos
	 * el tamaño que toma el Board es el mínimo 
	 */
	@Test
	public void testBoardGetSize() {
		//Dentro de los límites
		board = new Board(MIN_BOARD_SIZE+1);
		assertEquals(MIN_BOARD_SIZE+1,board.getSize());
		board = new Board(MAX_BOARD_SIZE-1);
		assertEquals(MAX_BOARD_SIZE-1,board.getSize());
		board = new Board(MIN_BOARD_SIZE-1);
		assertEquals(MIN_BOARD_SIZE,board.getSize());
		board = new Board(MAX_BOARD_SIZE+1);
		assertEquals(MIN_BOARD_SIZE,board.getSize());
		// fail ("Termina el test superando los límites en 1");		
	}
	
	/* Se comprueba checkCoordinate en los límites del tamaño 
	 * del Board 
	 */
	@Test
	public void testCheckCoordinate() {
		final int SIZE = 15;
		Board board = new Board(SIZE);
		assertFalse(board.checkCoordinate(new Coordinate(0,SIZE)));
		assertFalse(board.checkCoordinate(new Coordinate(-1,SIZE-1)));
		assertFalse(board.checkCoordinate(new Coordinate(-1,SIZE)));
		assertFalse(board.checkCoordinate(new Coordinate(SIZE,0)));
		assertFalse(board.checkCoordinate(new Coordinate(SIZE-1,-1)));
		assertFalse(board.checkCoordinate(new Coordinate(SIZE,-1)));
		assertTrue(board.checkCoordinate(new Coordinate(0,SIZE-1)));
		assertTrue(board.checkCoordinate(new Coordinate(SIZE-1,0)));
		
	}

	//TODO testAddShipsOk()
	/* Posicionamientos correctos entre Ships. Posiciona de forma correcta 
	 * los 4 ships galeon, fragata, goleta y bergantín y comprueba que  se
	 * han posicionado los Ships en el Board.
	 */
	@Test
	public void testAddShipsOk() {
		assertTrue(board.addShip(galeon, new Coordinate(0,1)));
		for (int i=2; i<5; i++)	
			assertNotNull("x,y = 2,"+i,board.getShip(new Coordinate(2,i)));
		
		assertTrue(board.addShip(goleta, new Coordinate(3,1)));
		for (int i=2; i<5; i++)	
			assertNotNull("x,y = 5,"+i,board.getShip(new Coordinate(5,i)));
		
		assertTrue(board.addShip(fragata, new Coordinate(0,4)));
		for (int i=1; i<4; i++)	
			assertNotNull("x,y = " + i + ",6",board.getShip(new Coordinate(i,6)));
		
		assertTrue(board.addShip(bergantin, new Coordinate(-1,-2)));
		for (int i=0; i<3; i++)	
			assertNotNull("x,y = " + i + ",0",board.getShip(new Coordinate(i,0)));
		
		// fail("Sigue comprobando addShip igualmente para fragata, goleta y bergantín");
	}

	//TODO testAddShipsOutOfBoard
	/* Posiciona los 4 Ships fuera del tablero y comprueba que
	 * addShip devuelve false y que además no se han posicionado
	 * los Ships en el Board
	 */
	@Test
	public void testAddShipsOutOfBoard() {
		assertFalse(this.board.addShip(this.bergantin, new Coordinate(8, 8)));
		assertFalse(this.board.addShip(this.fragata, new Coordinate(8, 8)));
		assertFalse(this.board.addShip(this.galeon, new Coordinate(8, 8)));
		assertFalse(this.board.addShip(this.goleta, new Coordinate(8, 8)));
		// fail("Realiza el test testAddShipsOutOfBoard()");
	}
	
	//TODO testAddShipNextOther
	/* Posiciona un Ship junto a otro y comprueba que addShip devuelve
	 * false y que además no se ha posicionado el Ship en el Board
	 */
	@Test
	public void testAddShipNextOther() {
		
		assertTrue(this.board.addShip(this.galeon, new Coordinate(2, 2)));
		
		assertNotNull(this.board.getShip(new Coordinate(4, 3)));
		
		assertFalse(this.board.addShip(this.goleta, new Coordinate(3, 2)));
		
		assertNull(this.board.getShip(new Coordinate(5, 3)));
		// fail("Realiza el test testAddShipNextOther()");
	}
	
	//TODO testGetShip
	/* Se posiciona un Ship en una Coordinate.
	 * 1- Prueba getShip en una Coordinate que no contiene al Ship
	 * 2- Prueba getShip en todas las posiciones que ocupa el Ship
	 */
	@Test
	public void testGetShip() {
		assertTrue(board.addShip(fragata, new Coordinate(3,1)));

		assertNull(board.getShip(new Coordinate(0, 0)));
		
		for (int i = 4; i < 7; i ++) {
			assertNotNull(board.getShip(new Coordinate(i, 3)));
		}
		// fail ("Termina el test testGetShip()");
	}
	
	
    /* Se comprueba isSeen antes y después de disparar al agua
     * en un Board sin Ships */
	@Test
	public void testIsSeen1() {

		for (int i=0; i<board.getSize(); i++)
			for (int j=0; j<board.getSize(); j++) {
				assertFalse(board.isSeen(new Coordinate(i,j)));
				board.hit(new Coordinate(i,j));
				assertTrue(board.isSeen(new Coordinate(i,j)));
			}
				
	}

	//TODO testIsSeen2
  /* Posiciona un Ship en el Board y comprueba isSeen 
   * antes y después de disparar a las distintas partes del Ship.
   * Cuando el Ship se ha hundido entonces comprueba que las
   * Coordinates vecinas del Ship también se han marcado como
   * vistas */
	@Test
	public void testIsSeen2() {
		this.board.addShip(this.galeon, new Coordinate(0, 1));
		
		for (int i = 2; i < 5; i ++) {
			if (i < 4) {
				assertFalse(this.board.isSeen(new Coordinate(2, i)));
				assertEquals(this.board.hit(new Coordinate(2, i)), CellStatus.HIT);
				assertTrue(this.board.isSeen(new Coordinate(2, i)));
			}
			else {
				assertFalse(this.board.isSeen(new Coordinate(2, i)));
				assertEquals(this.board.hit(new Coordinate(2, i)), CellStatus.DESTROYED);
				assertTrue(this.board.isSeen(new Coordinate(2, i)));
			}
		}
		
		Set<Coordinate> vecinos = this.board.getNeighborhood(this.galeon);
		
		for (Coordinate c : vecinos) {
			assertTrue(this.board.isSeen(c));
		}
		
		// fail ("Realiza el test testIsSeen2()");
	}

	//TODO testHit
	/* Coloca un Ship en el Board en una Coordinate. Comprueba que:
	 * 1- al disparar (hit) sobre las posiciones alrededor del Ship el 
	 *    resultado es WATER.
	 * 2- al disparar (hit) sobre las posiciones del Ship, excepto la última,
	 *    el resultado es HIT.
	 * 3- al disparar (hit) sobre la última posición del Ship, el resultado 
	 *    es DESTROYED
	 * 
	 */
	@Test
	public void testHit() {
		
		board.addShip(galeon, new Coordinate(0,1));
		
		assertEquals(this.board.hit(new Coordinate(2, 6)), CellStatus.WATER);
		
		for (int i = 2; i < 5; i ++) {
			if (i < 4) {
				assertEquals(this.board.hit(new Coordinate(2, i)), CellStatus.HIT);
			}
			else {
				assertEquals(this.board.hit(new Coordinate(2, i)), CellStatus.DESTROYED);
			}
		}
				
		// fail("Realiza el test testHit()");
	}

	//TODO testAreAllCraftsDestroyed
	/* Comprueba que:
	 * 1- en un Board sin Ships, areAllCraftsDestroyed devuelve true.
	 * 2- al posicionar dos Ships en el Board, tras cada posicionamiento,
	 *    areAllCraftsDestroyed devuelve false.
	 * 3- tras cada disparo sobre el primer Ship, hundiéndolo, areAllCraftsDestroyed 
	 *    devuelve false.
	 * 4- tras cada disparo sobre el segundo Ship, areAllCraftsDestroyed devuelve
	 *    false, excepto tras el último disparo que debe devolver true.
	 * 5- añade un nuevo Ship, entonces areAllCraftsDestroyed debe devolver
	 *    false.
	 */
	@Test
	public void testAreAllCraftsDestroyed() {
		assertTrue("numCrafts=destroyedCrafts=0",board.areAllCraftsDestroyed());
		board.addShip(galeon, new Coordinate(0,1));
		assertFalse("numCrafts=1; destroyedCrafts=0",board.areAllCraftsDestroyed());
		board.addShip(fragata, new Coordinate(3,1));
		assertFalse("numCrafts=2; destroyedCrafts=0",board.areAllCraftsDestroyed());
		
		for (int i = 2; i < 5; i ++) {
			this.board.hit(new Coordinate(2, i));
			assertFalse("numCrafts=2; destroyedCrafts=0",board.areAllCraftsDestroyed());
		}
		
		for (int i = 4; i < 7; i ++) {
			this.board.hit(new Coordinate(i, 3));
			if (i < 6) {
				assertFalse("numCrafts=2; destroyedCrafts=1",board.areAllCraftsDestroyed());
			}
		}
		
		assertTrue("numCrafts=2; destroyedCrafts=2",board.areAllCraftsDestroyed());
		// fail ("Termina las pruebas 3, 4 y 5");
	}

	
	/* Se comprueba getNeighborhood(Ship) donde el Ship y todas sus 
	 * Coordinate vecinas están dentro de Board.
	 */
	@Test
	public void testGetNeighborhoodShipCompletelyIn1() {
		board.addShip(fragata, new Coordinate(5,1));		
		Set<Coordinate> neighborhood = board.getNeighborhood(fragata);
		assertEquals(12,neighborhood.size());
		for (int i=5; i<10; i++) {
			for (int j=2; j<4; j++) {
				if ((j==3) && (i>=6)&&(i<=8))
					assertFalse("x,y = "+i+","+j,neighborhood.contains(new Coordinate(i,j)));
				else 
					assertTrue("x,y = "+i+","+j,neighborhood.contains(new Coordinate(i,j)));	
			}
		}
	}
	
	//TODO testGetNeighborhoodShipOutOfBounds
	/* Comprueba:
	 * 1- getNeighborhood(Ship) para un Ship que no se ha puesto en el Board 
	 *    debe devolver un Set vacío.
	 * 
	 * 2- getNeighborhood(Ship, Coordinate) donde el Ship sale de los límites
	 *    del Board. El conjunto de Coordinate vecinas solo recoge aquellas
	 *    que están dentro del Board
	 */
	@Test
	public void testGetNeighborhoodShipOutOfBounds() {
		assertTrue(this.board.getNeighborhood(this.bergantin).isEmpty());
		this.board.addShip(this.galeon, new Coordinate(-2, -1));
		
		// Las coordinadas que tendrian que salir son (1, 0), (1, 1), (1, 2), (1, 3) y (0, 3)
		
		for (int i = 0; i < 4; i ++) {
			assertTrue(this.board.getNeighborhood(this.galeon).contains(new Coordinate(1, i)));
		}
		
		assertTrue(this.board.getNeighborhood(this.galeon).contains(new Coordinate(0, 3)));
		
		// No deberia contener (-1, 1) aunque este sea vecino, porque esta fuera del board
	
		assertFalse(this.board.getNeighborhood(this.galeon).contains(new Coordinate(-1, 1)));
		
		// fail ("Realiza el test testGetNeighborhoodShipOutOfBounds() ");
	}
	
	
	/* Se crea un tablero de tamaño 5 sin Ships. Se comprueba que lo devuelto
	 * por show(true) y show(false) es correcto.
	 * 
	 */
	@Test
	public void testShowBoardEmty() {
		board = new Board(5);
		String hideShips = board.show(false);
		
		assertEquals(sboardHide1,hideShips);
		String showShips = board.show(true);
		assertEquals(sboardEmpty,showShips);
	}
	
	//TODO testShowBoardWithShips
	/* Se crea un tablero de tamaño 5.
	 * 1- Se añaden los 4 ships en las posiciones indicadas en la variable estática 'sboard'
	 *    definida en setUp().
	 * 2- Se comprueba que show(false) devuelve lo mismo que la variable estática sboardHide1
	 *    y que show(true) lo mismo que el contenido de la variable estática 'sboard' 
	 */
	@Test
	public void testShowBoardWithShips() {
		board = new Board(5);
		board.addShip(galeon, new Coordinate(-2,-1)); 
		board.addShip(fragata, new Coordinate(1,-2));
		board.addShip(goleta, new Coordinate(2,1));
		board.addShip(bergantin, new Coordinate(-1,2));
		
		assertEquals(sboard, this.board.show(true));
		
//		for(int i = 0; i < 3; i ++) {
//			System.out.println(this.board.show(true));
//			System.out.println(this.board.show(false));
//			this.board.hit(new Coordinate(0, i));
//		}
//		
//		this.board.hit(new Coordinate(2, 2));
//		
//		System.out.println(this.board.show(true));
//		System.out.println(this.board.show(false));
		
		// fail ("Sigue añadiendo la goleta y el bergantín en sus posiciones y haz las comprobaciones indicadas");

	}	
	
	
	//TODO testToString1
	/* Añade los 4 Ships del setUp() en el Board y comprueba toString() con
	 * la salida correcta.
	 */
	@Test
	public void testToString1() {
		this.board.addShip(this.bergantin, new Coordinate(-1, -1));
		this.board.addShip(this.fragata, new Coordinate(0, 2));
		this.board.addShip(this.galeon, new Coordinate(4, 4));
		this.board.addShip(this.goleta, new Coordinate(7, 0));
		//fail("Añade los 4 Ships en el Board");
		assertEquals("Board 10; crafts: 4; destroyed: 0",board.toString());
	}
	
	//TODO testToString2
    /* Se toma el ejemplo del test testAreAllCraftsDestroyed().
     * 1- Destruye el galeón y comprueba que la salida que debe dar es correcta.
     * 2- Realiza disparos sobre la fragata comprobando que las salidas que debe
     *    dar son correctas.
    */
	@Test
	public void testToString2() {
		
		board.addShip(galeon, new Coordinate(0,1));
		
		board.addShip(fragata, new Coordinate(3,1));
		
		assertEquals("Board 10; crafts: 2; destroyed: 0",board.toString());
		
		for (int i = 2; i < 5; i ++) {
			this.board.hit(new Coordinate(2, i));
		}
		
		assertEquals("Board 10; crafts: 2; destroyed: 1",board.toString());
		
		for (int i = 4; i < 7; i ++) {
			this.board.hit(new Coordinate(i, 3));
		}
		
		// fail ("Realiza el test testToString2()");
		assertEquals("Board 10; crafts: 2; destroyed: 2",board.toString());
	}

}
