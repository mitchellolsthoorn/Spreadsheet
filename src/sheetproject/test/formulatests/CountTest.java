package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Count;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class CountTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	
	@Test
	public void testEvaluatePositive() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Count.evaluate("=COUNT(4,7)", data), "2");		
	}
	
	@Test
	public void testEvaluatePositiveNegative1() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Count.evaluate("=COUNT(-4,7)", data), "2");		
	}
	
	@Test
	public void testEvaluatePositiveNegative2() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Count.evaluate("=COUNT(4,-7)", data), "2");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Count.evaluate("=COUNT(4, COUNT(4,5))", data), "2");		
	}
	
	@Test
	public void testEvaluatePositiveCell() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("7"), 1, 1);
		assertEquals(Count.evaluate("=COUNT(4, A1)", data), "2");		
	}
	
	@Test
	public void testEvaluateNegativeCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Count.evaluate("=COUNT(4, A1)", data), "1");		
	}
	
	@Test
	public void testEvaluateNegativeCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Count.evaluate("=COUNT(A1, A1)", data), "0");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Count.evaluate("=COUNT()", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchLessArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Count.evaluate("=COUNT(4)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Count.evaluate("=COUNT(4,5,6)", data), "");
	}

}