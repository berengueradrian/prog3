package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;


//@author ADRIÁN BERENGUER AGULLÓ, 74445262N
/**
 * The Class CoordinatePreTest.
 */
/* Para realizar los test se sugiere usar métodos de la librería de junit como:
 * assertEquals, assertNotEquals, assertSame; assertNotSame, assertTrue; assertFalse
 */
public class CoordinatePreTest {
	
    /** The vcoordinates. */
    List vcoordinates = new ArrayList<int[]>();
    
    /** The vcoor. */
    int []vcoor= {0,0,-70,-2,20}; //Para crear Coordinates
    
    /** The dim. */
    final int DIM = vcoor.length;
    
    /** The lcoor. */
    List<Coordinate> lcoor;
    
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		lcoor = new ArrayList<Coordinate>();
		//Se crean las Coordinates (0,0),(0,-70), (-70,-2),(-2,20);
		for (int i=0; i<DIM-1; i++) {
			lcoor.add(new Coordinate(vcoor[i],vcoor[i+1]));
		}
		
	}

	/**
	 * Test hash code.
	 */
	/* Se comprueba que cuando dos Coordinate son iguales, el resultado del hash ha 
	 * de ser el mismo. 
	 * Si los Coordinate son distintos el hashCode puede ser igual o no.
	 */
	@Test
	public void testHashCode() {
		Coordinate c1 = lcoor.get(2);
		Coordinate c2 = new Coordinate(c1);
		
		assertEquals (c1,c2);
		assertEquals (c1.hashCode(), c2.hashCode());
	}

	/**
	 * Test coordinate constructor.
	 */
	/* Se comprueba que el Constructor funciona bien. Para ello se analiza que las 
	 * las componentes  '0' y '1' de cada Coordinate creada en el setUp() son las 
	 * correctas.
	 */
	@Test
	public void testCoordinateConstructor() {
		int j=0;
		for (int i=0; i<DIM-1; i++) {			
			assertEquals("x",vcoor[i],lcoor.get(j).get(0));
			assertEquals("y",vcoor[i+1],lcoor.get(j).get(1));
			j++;
		}
	}

	/**
	 * Test coordinate constructor copy.
	 */
	/* Comprobar que el constructor de copia crea una nueva Coordinate con
	 * los mismos valores que las componentes respectivas del Coordinate copiado.
	 * Y eso se hace para cada Coordinate creada en setUp();
	 */
	@Test
	public void testCoordinateConstructorCopy() {
		//fail("Realiza el tes del constructor copia");
		Coordinate c1;
		Coordinate c2;
		for(int i=0;i<lcoor.size();i++) {
			c1=lcoor.get(i);
			c2= new Coordinate(c1);
			assertEquals(c2,c1);
		}
	}

	/**
	 * Test get set.
	 */
	/* Se comprueba que el método get(int) para cada componente de una Coordinate 
	 * funciona correctamente.
	 * Modificar los valores de las componentes de la Coordinate anterior con el 
	 * método set(int, int) y comprobar con get(int) que los valores de sus 
	 * componentes han cambiado a dichos valores.
	 */
	@Test
	public void testGetSet() {
		Coordinate c = lcoor.get(2);
		assertEquals("x==-70", -70, c.get(0));
		assertEquals("x==-2", -2, c.get(1));
		
		c.set(0, 18);
		c.set(1, 16);
		assertEquals("x==18", 18, c.get(0));
		assertEquals("x==16", 16, c.get(1));
		
		//fail ("Modifica los valores de los componentes de 'c' y comprueba que se han modificado");
	}

	/**
	 * Test add.
	 */
	/* Se suman las Coordinate creadas en el setUp() y comprueba, conforme se van 
	 * sumando, que los valores de sus componentes van tomando los valores correctos 
	 * y que el Coordinate que devuelve no es el mismo que el Coordinate que invoca 
	 * al método.
	 */
	@Test  //{0,0,-70,-2,20}
	public void testAdd() {
		Coordinate caux1 = lcoor.get(0);
		Coordinate caux2;
		
		int sumx = caux1.get(0);
		int sumy = caux1.get(1);
		for (int i=0; i<DIM-2; i++) {	
		   caux2 = caux1;
		   caux1 = caux1.add(lcoor.get(i+1));	  
		   sumx += (vcoor[i+1]);
		   sumy += (vcoor[i+2]);
		   assertEquals(caux1.get(0),sumx);
		   assertEquals(caux1.get(1),sumy);
		   assertNotEquals(caux1,caux2);
		   
		   /*Usa aquí los métodos de junit adecuados para comprobar:
		    * - que sumx y sumy son iguales a los componentes '0' y '1' 
		    *   respectivamente de caux1.
		    * - que el Coordinate que devuelve 'add' no es el mismo que
	        *   el Coordinate que invocó al método.
		    */
		   //fail ("Realiza las comprobaciones sugeridas anteriormente");
		}
	}

	/**
	 * Test subtract.
	 */
	/* Resta las Coordinate creadas en el setUp() y comprueba, conforme se van restando, 
	 * que los valores de sus componentes van tomando los valores correctos y que el 
	 * Coordinate que devuelve no es el mismo que el Coordinate que invoca 
	 * al método.
	 */
	@Test
	public void testSubtract() {
		//fail("Realiza la resta de los Coordinate propuesta");
		Coordinate caux1 = lcoor.get(0);
		Coordinate caux2;
		
		int sumx = caux1.get(0);
		int sumy = caux1.get(1);
		for (int i=0; i<DIM-2; i++) {	
		   caux2 = caux1;
		   caux1 = caux1.subtract(lcoor.get(i+1));	  
		   sumx -= (vcoor[i+1]);
		   sumy -= (vcoor[i+2]);
		   assertEquals(caux1.get(0),sumx);
		   assertEquals(caux1.get(1),sumy);
		   assertNotEquals(caux1,caux2);
		   

		}		
	}

	
	/**
	 * Test to string.
	 */
	/* Se comprueba, para el método toString(), que las Coordinate creadas en el setUp() 
	 * tienen el formato correcto.
	 */
	@Test
	public void testToString() {
		assertEquals ("(0, 0)",lcoor.get(0).toString());
		assertEquals ("(0, -70)",lcoor.get(1).toString());
		assertEquals ("(-70, -2)",lcoor.get(2).toString());
		assertEquals ("(-2, 20)",lcoor.get(3).toString());
	}

	/**
	 * Test equals object.
	 */
	/* Se toma una Coordinate y se comprueba todas las posibles condiciones bajo 
	 * las cuales, nuestra función equals() devuelve true o false
	 */
	@Test
	public void testEqualsObject() {
		Object obj = new String("(0, 0)");
		Coordinate c = lcoor.get(0);
		// equals() devuelve falso cuando le paso null
		assertFalse(c.equals(null));
		// equals() devuelve falso cuando le paso un objeto que no es de tipo Coordinate
		assertFalse(c.equals(obj));
		
		Coordinate c2;
		for(int i=0;i<lcoor.size()-1;i++) {
			c=lcoor.get(i);
			c2=lcoor.get(i++);
			if(c.equals(c2)) {
				assertTrue(c.equals(c2));
			}
			else {
				assertFalse(c.equals(c2));
			}
			if(c.equals(c)) {
				assertTrue(c.equals(c));
			}

		}
		
		/* Sigue comprobando lo siguiente:
		 *  1. equals() devuelve false cuando el valor de alguno de los componentes de los 
		 *     Coordinate es distinto
		 *  2. equals() devuelve true cuando se compara un objeto Coordinate consigo mismo
		 *  3. equals() devuelve true cuando comparo dos objetos Coordinate distintos
		 *     y los valores de sus componentes respectivos son iguales.
		 */
		//fail ("Completa el test equals()");
	}

}