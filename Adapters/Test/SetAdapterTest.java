import org.junit.Before;
import org.junit.Test;
import java.util.* ;

import static org.junit.Assert.*;

public class SetAdapterTest
{
    SetAdapter<Integer> adapter ;

    @Before
    public void setUp()
    {
        adapter = new SetAdapter() ;
    }

    @Test
    public void testSize()
    {
        assertEquals(0, adapter.size()) ;

        adapter.add(1) ;

        assertEquals(1,adapter.size());
    }

    @Test
    public void testIsEmpty()
    {
        assertTrue(adapter.isEmpty()) ;

        adapter.add(1) ;

        assertFalse(adapter.isEmpty()) ;
    }

    @Test
    public void testClear()
    {
        adapter.add(1) ;
        adapter.add(2) ;
        adapter.add(3) ;

        adapter.clear() ;

        assertEquals(0,adapter.size()) ;
        assertTrue(adapter.isEmpty()) ;
    }

    @Test
    public void testAdd()
    {
        assertTrue(adapter.add(1)) ;
        assertFalse(adapter.add(1)) ;

        adapter.add(2) ;

        assertEquals(2,adapter.size()) ;
        assertTrue(adapter.contains(1)) ;
        assertTrue(adapter.contains(2)) ;
    }

    @Test(expected = NullPointerException.class)
    public void testAddException()
    {
        adapter.add(null) ;
    }

    @Test
    public void testAddAll()
    {
        Collection<Integer> c = new CollectionAdapter() ;
        c.add(1) ;
        c.add(2) ;
        c.add(1) ;
        c.add(3) ;

        adapter.add(2) ;

        assertTrue(adapter.addAll(c)) ;

        assertEquals(3,adapter.size()) ;
        assertTrue(adapter.contains(1)) ;
        assertTrue(adapter.contains(2)) ;
        assertTrue(adapter.contains(3)) ;

        assertFalse(adapter.addAll(c)) ;
        assertEquals(3,adapter.size()) ;
    }

    @Test
    public void testAddAllVoid()
    {
        Collection<Integer> c = new CollectionAdapter() ;

        assertFalse(adapter.addAll(c)) ;

        assertEquals(0,adapter.size()) ;
    }

    @Test(expected = NullPointerException.class)
    public void testAddAllException()
    {
        adapter.addAll(null) ;
    }

    @Test
    public void testRemove()
    {
        adapter.add(1) ;
        adapter.add(2) ;
        adapter.add(3) ;

        assertTrue(adapter.remove(2)) ;

        assertEquals(2,adapter.size()) ;
        assertFalse(adapter.contains(2)) ;

        assertFalse(adapter.remove(4)) ;
        assertEquals(2,adapter.size()) ;
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveException()
    {
        adapter.remove(null) ;
    }

    @Test
    public void testRemoveAll()
    {
        Collection<Integer> c = new CollectionAdapter() ;
        c.add(1) ;
        c.add(2) ;
        c.add(3) ;
        c.add(4) ;

        adapter.add(2) ;
        adapter.add(3) ;

        assertTrue(adapter.removeAll(c)) ;
        assertEquals(0,adapter.size()) ;

        adapter.add(5) ;

        assertFalse(adapter.removeAll(c)) ;
        assertEquals(1,adapter.size()) ;
    }

    @Test
    public void testRemoveAllVoid()
    {
        Collection<Integer> c = new CollectionAdapter() ;

        adapter.add(2) ;
        adapter.add(3) ;

        assertFalse(adapter.removeAll(c)) ;
        assertEquals(2,adapter.size()) ;
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllException()
    {
        adapter.removeAll(null) ;
    }

    @Test
    public void testContains()
    {
        adapter.add(1) ;

        assertTrue(adapter.contains(1)) ;
        assertFalse(adapter.contains(2)) ;
    }

    @Test(expected = NullPointerException.class)
    public void testContainsException()
    {
        adapter.contains(null) ;
    }

    @Test
    public void testContainsAll()
    {
        Collection<Integer> c = new CollectionAdapter() ;
        c.add(1) ;
        c.add(2) ;

        adapter.add(1) ;
        adapter.add(2) ;

        assertTrue(adapter.containsAll(c)) ;

        c.add(5) ;

        assertFalse(adapter.containsAll(c)) ;
    }

    @Test
    public void testContainsAllVoid()
    {
        Collection<Integer> c = new CollectionAdapter() ;

        assertTrue(adapter.containsAll(c)) ;

        adapter.add(1) ;
        adapter.add(2) ;
        adapter.add(3) ;

        assertFalse(adapter.containsAll(c)) ;
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllException()
    {
        adapter.containsAll(null) ;
    }

    @Test
    public void testRetainAll()
    {
        Collection<Integer> c = new CollectionAdapter() ;
        c.add(1) ;
        c.add(2) ;

        adapter.add(1) ;
        adapter.add(3) ;
        adapter.add(4) ;

        assertTrue(adapter.retainAll(c)) ;
        assertEquals(1,adapter.size()) ;
        assertTrue(adapter.contains(1)) ;

        assertFalse(adapter.retainAll(c)) ;
        assertEquals(1,adapter.size()) ;
    }

    @Test
    public void testRetainAllVoid()
    {
        Collection<Integer> c = new CollectionAdapter() ;

        adapter.add(1) ;
        adapter.add(3) ;
        adapter.add(4) ;

        assertTrue(adapter.retainAll(c)) ;
        assertEquals(0,adapter.size()) ;
    }


    @Test(expected = NullPointerException.class)
    public void testRetainAllException()
    {
        adapter.retainAll(null) ;
    }

    @Test
    public void testEquals()
    {
        Set<Integer> c = new SetAdapter() ;
        c.add(1) ;
        c.add(2) ;

        adapter.add(1) ;
        adapter.add(2) ;

        assertTrue(adapter.equals(c)) ;

        c.add(3) ;

        assertFalse(adapter.equals(c)) ;
    }

    @Test
    public void testEqualsVoid()
    {
        Set<Integer> c = new SetAdapter() ;

        assertTrue(adapter.equals(c)) ;
    }

    @Test
    public void testEqualsInstanceOf()
    {
        Collection<Integer> c = new CollectionAdapter() ;
        c.add(1) ;
        c.add(2) ;

        adapter.add(1) ;
        adapter.add(2) ;

        assertFalse(adapter.equals(c)) ;
    }

    @Test
    public void testHashCode()
    {
        Set<Integer> c = new SetAdapter() ;
        c.add(1) ;
        c.add(2) ;

        adapter.add(1) ;
        adapter.add(2) ;

        assertEquals(adapter.hashCode(),c.hashCode());

        adapter.add(3) ;

        assertNotEquals(adapter.hashCode(),c.hashCode());
    }

    @Test
    public void testIteratorHasNext()
    {
        adapter.add(1) ;
        adapter.add(2) ;
        adapter.add(3) ;

        Iterator<Integer> iterator = adapter.iterator() ;

        int k = 0 ;
        while(iterator.hasNext())
        {
            iterator.next() ;
            k++ ;
        }

        assertEquals(k,adapter.size()) ;
    }

    @Test
    public void testIteratorNext()
    {
        adapter.add(1) ;
        adapter.add(2) ;
        adapter.add(3) ;

        Iterator<Integer> iterator = adapter.iterator() ;

        assertEquals(3, (int) iterator.next()) ;
        assertEquals(2, (int) iterator.next()) ;
        assertEquals(1, (int) iterator.next()) ;
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNextException()
    {
        adapter.iterator().next() ;
    }


    @Test
    public void testIteratorRemove()
    {
        adapter.add(1) ;
        adapter.add(2) ;
        adapter.add(3) ;

        Iterator<Integer> iterator = adapter.iterator() ;

        iterator.next() ;
        iterator.next() ;

        iterator.remove() ;

        assertFalse(adapter.contains(2)) ;
        assertEquals(2,adapter.size()) ;
    }

    @Test(expected = IllegalStateException.class)
    public void testIteratorRemoveException1()
    {
        adapter.iterator().remove() ;
    }

    @Test(expected = IllegalStateException.class)
    public void testIteratorRemoveException2()
    {
        adapter.add(1) ;


        Iterator<Integer> iterator = adapter.iterator() ;

        iterator.next() ;
        iterator.remove() ;
        iterator.remove() ;
    }

    @Test
    public void testToArray()
    {
        adapter.add(1) ;
        adapter.add(2) ;
        adapter.add(3) ;

        Iterator<Integer> iterator = adapter.iterator() ;

        Object[] elements = adapter.toArray() ;

        int i = 0 ;
        while(iterator.hasNext())
        {
            assertEquals(iterator.next(),elements[i]);
            i++ ;
        }
    }
}