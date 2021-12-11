import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class MapAdapterTest
{
    private MapAdapter<Integer,String> adapter ;

    @Before
    public void setUp()
    {
        adapter = new MapAdapter() ;
    }

    @Test
    public void testSize()
    {
        assertEquals(0,adapter.size()) ;

        adapter.put(1,"ciao") ;
        assertEquals(1,adapter.size()) ;
    }

    @Test
    public void testIsEmpty()
    {
        assertTrue(adapter.isEmpty()) ;
        assertEquals(0, adapter.size()) ;

        adapter.put(1,"ciao") ;

        assertFalse(adapter.isEmpty()) ;
        assertEquals(1, adapter.size()) ;
    }

    @Test
    public void testClear()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"ciao") ;

        adapter.clear() ;

        assertTrue(adapter.isEmpty()) ;
    }

    @Test
    public void testGet()
    {
        assertNull(adapter.get(5));

        adapter.put(1,"ciao") ;

        assertEquals("ciao",adapter.get(1)) ;

        assertNull(adapter.get(10)) ;
    }

    @Test(expected = NullPointerException.class)
    public void testGetException()
    {
        adapter.get(null) ;
    }

    @Test
    public void testPut()
    {
        assertNull(adapter.put(1, "ciao")) ;

        assertEquals(1,adapter.size()) ;

        assertEquals("ciao",adapter.put(1,"Sauron")) ;

        assertEquals("Sauron",adapter.get(1)) ;
        assertEquals(1,adapter.size()) ;
    }

    @Test(expected = NullPointerException.class)
    public void testPutException1()
    {
        adapter.put(null,"ciao") ;
    }

    @Test(expected = NullPointerException.class)
    public void testPutException2()
    {
        adapter.put(1,null) ;
    }

    @Test
    public void testContainsKey()
    {
        assertFalse(adapter.containsKey(5)) ;

        adapter.put(1,"ciao") ;

        assertTrue(adapter.containsKey(1)) ;
        assertFalse(adapter.containsKey(7)) ;
    }

    @Test(expected = NullPointerException.class)
    public void testContainsKeyException()
    {
        adapter.containsKey(null) ;
    }

    @Test
    public void testContainsValue()
    {
        assertFalse(adapter.containsValue("ciao")) ;

        adapter.put(1,"Sauron") ;

        assertTrue(adapter.containsValue("Sauron")) ;
        assertFalse(adapter.containsValue("sauron")) ;
    }

    @Test(expected = NullPointerException.class)
    public void testContainsValueException()
    {
        adapter.containsValue(null) ;
    }

    @Test
    public void testRemove()
    {
        assertNull(adapter.remove(5)) ;

        adapter.put(1,"ciao") ;

        assertEquals("ciao",adapter.remove(1)) ;
        assertEquals(0,adapter.size()) ;
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveException()
    {
        adapter.remove(null) ;
    }

    @Test
    public void testEquals()
    {
        Map<Integer,String> map = new MapAdapter() ;

        adapter.put(1,"ciao") ;
        map.put(1,"ciao") ;
        assertTrue(adapter.equals(map)) ;

        map.put(2,"Sauron") ;
        assertFalse(adapter.equals(map)) ;

        map.remove(1) ;
        assertFalse(adapter.equals(map)) ;
    }

    @Test
    public void testEqualsVoid()
    {
        Map<Integer,String> map = new MapAdapter<Integer,String>() ;

        assertTrue(adapter.equals(map)) ;
    }

    @Test
    public void testEqualsInstanceOf()
    {
        Collection<Integer> c = new CollectionAdapter<Integer>() ;

        assertFalse(adapter.equals(c)) ;
    }

    @Test
    public void testHashCode()
    {
        Map<Integer,String> map = new MapAdapter<Integer,String>() ;

        adapter.put(1,"ciao") ;
        map.put(1,"ciao") ;

        assertEquals(map.hashCode(),adapter.hashCode()) ;

        adapter.put(2,"Sauron") ;

        assertNotEquals(map.hashCode(),adapter.hashCode()) ;
    }

    @Test
    public void testPutAll()
    {
        Map<Integer,String> map = new MapAdapter() ;

        map.put(1,"ciao") ;
        map.put(2,"Sauron") ;
        map.put(3,"Obi") ;

        adapter.putAll(map) ;

        assertEquals(3,adapter.size()) ;

        assertTrue(adapter.containsKey(1)) ;
        assertTrue(adapter.containsValue("ciao")) ;
        assertTrue(adapter.containsKey(2)) ;
        assertTrue(adapter.containsValue("Sauron")) ;
        assertTrue(adapter.containsKey(3)) ;
        assertTrue(adapter.containsValue("Obi")) ;
    }

    @Test(expected = NullPointerException.class)
    public void testPutAllException()
    {
        adapter.putAll(null) ;
    }

    /*

    TEST DI ENTRYSET

     */

    @Test
    public void testEntrySetBacking()
    {
        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;
        assertEquals(0,entrySet.size()) ;
        assertEquals(0,adapter.size()) ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        assertEquals(3,entrySet.size()) ;
        assertEquals(3,adapter.size()) ;
        assertTrue(entrySet.contains(new MapAdapter.Entry(1,"ciao"))) ;
        assertTrue(entrySet.contains(new MapAdapter.Entry(2,"Sauron"))) ;
        assertTrue(entrySet.contains(new MapAdapter.Entry(3,"Obi"))) ;

        adapter.remove(3) ;
        adapter.remove(2) ;

        assertEquals(1,entrySet.size()) ;
        assertEquals(1,adapter.size()) ;
        assertTrue(entrySet.contains(new MapAdapter.Entry(1,"ciao"))) ;

        adapter.clear() ;

        assertEquals(0,entrySet.size()) ;
        assertEquals(0,adapter.size()) ;
    }

    @Test
    public void testEntrySetSize()
    {
        Set<Map.Entry<Integer,String>> entrySet1 = adapter.entrySet() ;

        assertEquals(0, entrySet1.size());

        adapter.put(1,"ciao") ;

        assertEquals(1, entrySet1.size());

        Set<Map.Entry<Integer,String>> entrySet2 = adapter.entrySet() ;

        assertEquals(1,entrySet2.size()) ;
    }

    @Test
    public void testEntrySetIsEmpty()
    {
        Set<Map.Entry<Integer,String>> entrySet1 = adapter.entrySet() ;

        assertTrue(entrySet1.isEmpty()) ;

        adapter.put(1,"ciao") ;

        assertFalse(entrySet1.isEmpty()) ;

        Set<Map.Entry<Integer,String>> entrySet2 = adapter.entrySet() ;

        assertFalse(entrySet2.isEmpty()) ;
    }

    @Test
    public void testEntrySetClear()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        entrySet.clear() ;

        assertEquals(0,entrySet.size()) ;
        assertEquals(0,adapter.size()) ;
    }

    @Test
    public void testEntrySetHashCode()
    {
        Set<Map.Entry<Integer,String>> entrySet_1 = adapter.entrySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet_2 = adapter.entrySet() ;

        assertEquals(entrySet_1.hashCode(),entrySet_2.hashCode()) ;

        adapter.put(5,"Luke") ;

        Set<Map.Entry<Integer,String>> entrySet_3 = adapter.entrySet() ;

        assertEquals(entrySet_1.hashCode(),entrySet_3.hashCode()) ;

        Set<Map.Entry<Integer,String>> set = new SetAdapter() ;
        set.add(new MapAdapter.Entry(1,"ciao")) ;
        set.add(new MapAdapter.Entry(2,"Sauron")) ;

        assertNotEquals(entrySet_1.hashCode(),set.hashCode()) ;
    }

    @Test
    public void testEntrySetEquals()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet_1 = adapter.entrySet() ;

        Set<Map.Entry<Integer,String>> entrySet_2 = adapter.entrySet() ;

        assertTrue(entrySet_1.equals(entrySet_2)) ;

        Collection<Integer> c = new CollectionAdapter()  ;

        assertFalse(entrySet_1.equals(c)) ;
    }

    @Test
    public void testEntrySetEqualsInstanceOf()
    {
        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        Collection<Integer> c = new CollectionAdapter()  ;

        assertFalse(entrySet.equals(c)) ;
    }

    @Test
    public void testEntrySetContains()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        assertTrue(entrySet.contains(new MapAdapter.Entry(1,"ciao"))) ;
        assertTrue(entrySet.contains(new MapAdapter.Entry(2,"Sauron"))) ;
        assertTrue(entrySet.contains(new MapAdapter.Entry(3,"Obi"))) ;
        assertFalse(entrySet.contains(new MapAdapter.Entry(5,"Luke"))) ;

        adapter.put(5,"Luke") ;

        assertTrue(entrySet.contains(new MapAdapter.Entry(5,"Luke"))) ;

        adapter.remove(2) ;

        assertFalse(entrySet.contains(new MapAdapter.Entry(2,"Sauron"))) ;

        assertFalse(entrySet.contains(new MapAdapter.Entry(1,"CIAO"))) ;
        assertFalse(entrySet.contains(new MapAdapter.Entry(3,"ciao"))) ;
    }

    @Test(expected = NullPointerException.class)
    public void testEntrySetContainsException()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        entrySet.contains(null) ;
    }

    @Test
    public void testEntrySetContainsAll()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        Collection<MapAdapter.Entry<Integer,String>> c = new CollectionAdapter<MapAdapter.Entry<Integer, String>>() ;

        c.add(new MapAdapter.Entry(1,"ciao")) ;
        c.add(new MapAdapter.Entry(3,"Obi")) ;

        assertTrue(entrySet.containsAll(c)) ;

        c.add(new MapAdapter.Entry(1,"CIAO")) ;
        c.add(new MapAdapter.Entry(2,"ciao")) ;

        assertFalse(entrySet.containsAll(c)) ;
    }

    @Test
    public void testEntrySetContainsAllVoid()
    {
        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        Collection<MapAdapter.Entry<Integer,String>> c = new CollectionAdapter<MapAdapter.Entry<Integer, String>>() ;

        assertTrue(entrySet.containsAll(c)) ;
    }

    @Test(expected = NullPointerException.class)
    public void testEntrySetContainsAllException()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        entrySet.removeAll(null) ;
    }

    @Test
    public void testEntrySetRemove()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        assertTrue(entrySet.remove(new MapAdapter.Entry(2,"Sauron"))) ;

        assertEquals(2,entrySet.size()) ;
        assertEquals(2,adapter.size()) ;

        assertFalse(entrySet.contains(new MapAdapter.Entry(2, "Sauron"))) ;
        assertFalse(adapter.containsKey(2)) ;

        assertFalse(entrySet.remove(new MapAdapter.Entry(5,"Luke"))) ;
        assertFalse(entrySet.remove(new MapAdapter.Entry(1,"Mamma"))) ;
        assertFalse(entrySet.remove(new MapAdapter.Entry(2,"ciao"))) ;
    }

    @Test(expected = NullPointerException.class)
    public void testEntrySetRemoveException()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        entrySet.remove(null) ;
    }

    @Test
    public void testEntrySetRemoveAll()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        Collection<MapAdapter.Entry<Integer,String>> c = new CollectionAdapter<MapAdapter.Entry<Integer, String>>() ;

        c.add(new MapAdapter.Entry(1,"ciao")) ;
        c.add(new MapAdapter.Entry(3,"Obi")) ;

        assertTrue(entrySet.removeAll(c)) ;

        assertEquals(1,entrySet.size()) ;
        assertEquals(1,adapter.size()) ;

        c.add(new MapAdapter.Entry(1,"Zio")) ;
        c.add(new MapAdapter.Entry(12,"ciao")) ;

        assertFalse(entrySet.removeAll(c)) ;
    }

    @Test
    public void testEntrySetRemoveAllVoid()
    {
        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        Collection<MapAdapter.Entry<Integer,String>> c = new CollectionAdapter<MapAdapter.Entry<Integer, String>>() ;

        assertFalse(entrySet.removeAll(c)) ;
    }

    @Test(expected = NullPointerException.class)
    public void testEntrySetRemoveAllException()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        entrySet.removeAll(null) ;
    }

    @Test
    public void testEntrySetRetainAll()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        Collection<MapAdapter.Entry<Integer,String>> c = new CollectionAdapter<MapAdapter.Entry<Integer, String>>() ;

        c.add(new MapAdapter.Entry(1,"ciao")) ;
        c.add(new MapAdapter.Entry(3,"Obi")) ;

        assertTrue(entrySet.retainAll(c)) ;

        assertEquals(2,entrySet.size()) ;
        assertEquals(2,adapter.size()) ;

        assertTrue(entrySet.contains(new MapAdapter.Entry(1,"ciao"))) ;
        assertTrue(adapter.containsKey((new MapAdapter.Entry(1,"ciao")).getKey())) ;
        assertTrue(entrySet.contains(new MapAdapter.Entry(3,"Obi"))) ;
        assertTrue(adapter.containsKey((new MapAdapter.Entry(1,"ciao")).getKey())) ;

        assertFalse(entrySet.retainAll(c)) ;
    }

    @Test
    public void testEntrySetRetainAllVoid()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        Collection<MapAdapter.Entry<Integer,String>> c = new CollectionAdapter<MapAdapter.Entry<Integer, String>>() ;

        assertTrue(entrySet.retainAll(c)) ;

        assertEquals(0,entrySet.size()) ;
        assertEquals(0,adapter.size()) ;
    }

    @Test(expected = NullPointerException.class)
    public void testEntrySetRetainAllException()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        entrySet.retainAll(null) ;
    }

    @Test
    public void testEntrySetIteratorHasNext()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        Iterator<Map.Entry<Integer, String>> iterator = entrySet.iterator() ;

        int i = 0 ;
        while(iterator.hasNext())
        {
            iterator.next() ;
            i++ ;
        }

        assertEquals(3,i) ;
    }

    @Test
    public void testEntrySetIteratorNext()
    {
        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        Iterator<Map.Entry<Integer, String>> iterator = entrySet.iterator() ;

        assertEquals(new MapAdapter.Entry(3,"Sauron"),iterator.next()) ;
        assertEquals(new MapAdapter.Entry(2,"Obi"),iterator.next()) ;
        assertEquals(new MapAdapter.Entry(1,"ciao"),iterator.next()) ;
    }

    @Test(expected = NoSuchElementException.class)
    public void testEntrySetIteratorNextException()
    {
        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        Iterator<Map.Entry<Integer, String>> iterator = entrySet.iterator() ;

        iterator.next() ;
        iterator.next() ;
        iterator.next() ;
        iterator.next() ;
    }

    @Test
    public void testEntrySetIteratorRemove()
    {
        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        Iterator<Map.Entry<Integer, String>> iterator = entrySet.iterator() ;

        iterator.next() ;
        iterator.next() ;
        iterator.remove() ;

        assertEquals(2,entrySet.size()) ;
        assertEquals(2,adapter.size()) ;
        assertFalse(entrySet.contains(new MapAdapter.Entry(2, "Obi"))) ;
        assertFalse(adapter.containsKey(2));
    }

    @Test(expected = IllegalStateException.class)
    public void testEntrySetIteratorRemoveException1()
    {
        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        Iterator<Map.Entry<Integer, String>> iterator = entrySet.iterator() ;

        iterator.remove() ;
    }

    @Test(expected = IllegalStateException.class)
    public void testEntrySetIteratorRemoveException2()
    {
        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        Iterator<Map.Entry<Integer, String>> iterator = entrySet.iterator() ;

        iterator.next() ;
        iterator.remove() ;
        iterator.remove() ;
    }

    @Test
    public void testEntrySetToArray()
    {
        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        Iterator<Map.Entry<Integer, String>> iterator = entrySet.iterator() ;

        Object[] array = entrySet.toArray() ;

        assertEquals(array[0],iterator.next()) ;
        assertEquals(array[1],iterator.next()) ;
        assertEquals(array[2],iterator.next()) ;
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testEntrySetAdd()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        entrySet.add(new MapAdapter.Entry(5,"Luke")) ;
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testEntrySetAddAll()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Map.Entry<Integer,String>> entrySet = adapter.entrySet() ;

        Collection<Map.Entry<Integer,String>> c = new CollectionAdapter() ;

        c.add(new MapAdapter.Entry(5,"Luke")) ;

        entrySet.addAll(c) ;
    }

    /*

    TEST DI KEYSET

     */
    @Test
    public void testKeySetBacking()
    {
        Set<Integer> keySet = adapter.keySet() ;
        assertEquals(0,keySet.size()) ;
        assertEquals(0,adapter.size()) ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        assertEquals(3,keySet.size()) ;
        assertEquals(3,adapter.size()) ;
        assertTrue(keySet.contains(1)) ;
        assertTrue(keySet.contains(2)) ;
        assertTrue(keySet.contains(3)) ;

        adapter.remove(3) ;
        adapter.remove(2) ;

        assertEquals(1,keySet.size()) ;
        assertEquals(1,adapter.size()) ;
        assertTrue(keySet.contains(1)) ;

        adapter.clear() ;

        assertEquals(0,keySet.size()) ;
        assertEquals(0,adapter.size()) ;
    }

    @Test
    public void testKeySetSize()
    {
        Set<Integer> keySet = adapter.keySet() ;

        assertEquals(0,keySet.size()) ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        assertEquals(3,keySet.size()) ;

        Set<Integer> newKeySet = adapter.keySet() ;

        assertEquals(3,newKeySet.size()) ;
    }

    @Test
    public void testKeySetIsEmpty()
    {
        Set<Integer> keySet = adapter.keySet() ;

        assertTrue(keySet.isEmpty()) ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        assertFalse(keySet.isEmpty()) ;
    }

    @Test
    public void testKeySetClear()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        keySet.clear() ;

        assertEquals(0,keySet.size()) ;
        assertEquals(0,adapter.size()) ;
    }

    @Test
    public void testKeySetHashCode()
    {
        Set<Integer> keySet = adapter.keySet() ;

        assertEquals(0,keySet.hashCode()) ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Integer> newKeySet = adapter.keySet() ;

        assertEquals(keySet.hashCode(),newKeySet.hashCode()) ;

        Set<Integer> set = new SetAdapter() ;
        set.add(1) ;
        set.add(2) ;

        assertNotEquals(keySet.hashCode(),set.hashCode()) ;
    }

    @Test
    public void testKeySetEquals()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Integer> keySet = adapter.keySet() ;
        Set<Integer> newKeySet = adapter.keySet() ;

        assertTrue(keySet.equals(newKeySet)) ;
    }

    @Test
    public void testKeySetEqualsInstanceOf()
    {
        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Set<Integer> keySet = adapter.keySet() ;

        Collection<Integer> c = new CollectionAdapter() ;
        c.add(1) ;
        c.add(2) ;

        assertFalse(keySet.equals(c)) ;
    }

    @Test
    public void testKeySetContains()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        assertTrue(keySet.contains(2)) ;
        assertFalse(keySet.contains(5)) ;
    }

    @Test(expected = NullPointerException.class)
    public void testKeySetContainsException()
    {
        Set<Integer> keySet = adapter.keySet() ;

        keySet.contains(null) ;
    }

    @Test
    public void testKeySetContainsAll()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Collection<Integer> c = new CollectionAdapter() ;
        c.add(2) ;
        c.add(3) ;

        assertTrue(keySet.containsAll(c)) ;

        c.add(5) ;

        assertFalse(keySet.containsAll(c)) ;
    }

    @Test
    public void testKeySetContainsAllVoid()
    {
        Set<Integer> keySet = adapter.keySet() ;

        Collection<Integer> c = new CollectionAdapter() ;

        assertTrue(keySet.containsAll(c)) ;
    }

    @Test(expected = NullPointerException.class)
    public void testKeySetContainsAllException()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        keySet.containsAll(null) ;
    }

    @Test
    public void testKeySetRemove()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        assertTrue(keySet.remove(2)) ;

        assertEquals(2,keySet.size()) ;
        assertEquals(2,adapter.size()) ;
        assertFalse(keySet.contains(2)) ;
        assertFalse(adapter.containsKey(2)) ;

        assertFalse(keySet.remove(5)) ;

        assertEquals(2,keySet.size()) ;
        assertEquals(2,adapter.size()) ;
    }

    @Test(expected = NullPointerException.class)
    public void testKeySetRemoveException()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        keySet.remove(null) ;
    }

    @Test
    public void testKeySetRemoveAll()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Collection<Integer> c = new CollectionAdapter() ;
        c.add(1) ;
        c.add(2) ;

        assertTrue(keySet.removeAll(c)) ;

        assertEquals(1,keySet.size()) ;
        assertEquals(1,adapter.size()) ;
        assertTrue(keySet.contains(3)) ;
        assertTrue(adapter.containsKey(3)) ;

        assertFalse(keySet.removeAll(c)) ;
        assertEquals(1,keySet.size()) ;
        assertEquals(1,adapter.size()) ;
    }

    @Test
    public void testKeySetRemoveAllVoid()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Collection<Integer> c = new CollectionAdapter() ;

        assertFalse(keySet.removeAll(c)) ;
    }

    @Test(expected = NullPointerException.class)
    public void testKeySetRemoveAllException()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        keySet.removeAll(null) ;
    }

    @Test
    public void testKeySetRetainAll()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Collection<Integer> c = new CollectionAdapter() ;
        c.add(1) ;
        c.add(2) ;

        assertTrue(keySet.retainAll(c)) ;

        assertEquals(2,keySet.size()) ;
        assertEquals(2,adapter.size()) ;
        assertFalse(keySet.contains(3)) ;
        assertFalse(adapter.containsKey(3)) ;

        assertFalse(keySet.retainAll(c)) ;
        assertEquals(2,keySet.size()) ;
        assertEquals(2,adapter.size()) ;
    }

    @Test
    public void testKeySetRetainAllVoid()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Collection<Integer> c = new CollectionAdapter() ;

        assertTrue(keySet.retainAll(c)) ;

        assertEquals(0,keySet.size()) ;
        assertEquals(0,adapter.size()) ;
    }

    @Test(expected = NullPointerException.class)
    public void testKeySetRetainAllException()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        keySet.retainAll(null) ;
    }

    @Test
    public void testKeySetIteratorHasNext()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        Iterator<Integer> iterator = keySet.iterator() ;

        int i = 0 ;
        while(iterator.hasNext())
        {
            iterator.next() ;
            i++ ;
        }

        assertEquals(3,i) ;
    }

    @Test
    public void testKeySetIteratorNext()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;

        Iterator<Integer> iterator = keySet.iterator() ;

        assertEquals(3,(int)iterator.next()) ;
        assertEquals(2,(int)iterator.next()) ;
        assertEquals(1,(int)iterator.next()) ;
    }

    @Test(expected = NoSuchElementException.class)
    public void testKeySetIteratorNextException()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;

        Iterator<Integer> iterator = keySet.iterator() ;

        iterator.next() ;
        iterator.next() ;
        iterator.next() ;
        iterator.next() ;
    }

    @Test
    public void testKeySetIteratorRemove()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;

        Iterator<Integer> iterator = keySet.iterator() ;

        iterator.next() ;
        iterator.remove() ;

        assertEquals(2,keySet.size()) ;
        assertEquals(2,adapter.size()) ;
        assertFalse(keySet.contains(3)) ;
        assertFalse(adapter.containsKey(3)) ;
    }

    @Test(expected = IllegalStateException.class)
    public void testKeySetIteratorRemoveException1()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;

        Iterator<Integer> iterator = keySet.iterator() ;

        iterator.remove() ;
    }

    @Test(expected = IllegalStateException.class)
    public void testKeySetIteratorRemoveException2()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;

        Iterator<Integer> iterator = keySet.iterator() ;

        iterator.next() ;
        iterator.remove() ;
        iterator.remove() ;
    }

    @Test
    public void testKeySetToArray()
    {
        Set<Integer> keySet = adapter.keySet() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;

        Iterator<Integer> iterator = keySet.iterator() ;

        Object[] array = keySet.toArray() ;

        assertEquals(array[0],iterator.next()) ;
        assertEquals(array[1],iterator.next()) ;
        assertEquals(array[2],iterator.next()) ;
    }

    /*

    TEST DI VALUES

     */
    @Test
    public void testValuesBacking()
    {
        Collection<String> values = adapter.values() ;
        assertEquals(0,values.size()) ;
        assertEquals(0,adapter.size()) ;

        adapter.put(1,"ciao") ;
        adapter.put(2,"Sauron") ;
        adapter.put(3,"Obi") ;

        assertEquals(3,values.size()) ;
        assertEquals(3,adapter.size()) ;
        assertTrue(values.contains("ciao")) ;
        assertTrue(values.contains("Sauron")) ;
        assertTrue(values.contains("Obi")) ;

        adapter.remove(3) ;
        adapter.remove(2) ;

        assertEquals(1,values.size()) ;
        assertEquals(1,adapter.size()) ;
        assertTrue(values.contains("ciao")) ;

        adapter.clear() ;

        assertEquals(0,values.size()) ;
        assertEquals(0,adapter.size()) ;
    }

    @Test
    public void testValuesSize()
    {
        Collection<String> values = adapter.values() ;

        assertEquals(0,values.size()) ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        assertEquals(4,values.size()) ;
    }

    @Test
    public void testValuesIsEmpty()
    {
        Collection<String> values = adapter.values() ;

        assertTrue(values.isEmpty()) ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        assertFalse(values.isEmpty()) ;
    }

    @Test
    public void testValuesClear()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        values.clear() ;

        assertTrue(values.isEmpty()) ;
        assertTrue(adapter.isEmpty()) ;
    }

    @Test
    public void testValuesHashCode()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        Collection<String> newValues = adapter.values() ;

        assertEquals(newValues.hashCode(),values.hashCode()) ;

        Collection<String> c = new CollectionAdapter() ;
        c.add("ciao") ;
        c.add("Sauron") ;
        c.add("Luke") ;

        assertNotEquals(c.hashCode(),values.hashCode()) ;
    }

    @Test
    public void testValuesEquals()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        Collection<String> newValues = adapter.values() ;

        assertTrue(newValues.equals(values)) ;
    }

    @Test
    public void testValuesEqualsInstanceOf()
    {
        Collection<String> values = adapter.values() ;
        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;

        Set<String> set = new SetAdapter() ;
        set.add("ciao") ;
        set.add("Sauron") ;

        assertFalse(values.equals(set));
    }

    @Test
    public void testValuesContains()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        assertTrue(values.contains("ciao")) ;
        assertTrue(values.contains("Sauron")) ;
        assertFalse(values.contains("cinque")) ;
    }

    @Test(expected = NullPointerException.class)
    public void testValuesContainsException()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        values.contains(null) ;
    }

    @Test
    public void testValuesContainsAll()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        Collection<String> c = new CollectionAdapter() ;
        c.add("ciao") ;
        c.add("Sauron") ;

        assertTrue(values.containsAll(c)); ;

        c.add("Luke") ;

        assertFalse(values.containsAll(c)) ;
    }

    @Test
    public void testValuesContainsAllVoid()
    {
        Collection<String> values = adapter.values() ;

        Collection<String> c = new CollectionAdapter() ;

        assertTrue(values.containsAll(c)); ;
    }

    @Test(expected = NullPointerException.class)
    public void testValuesContainsAllException()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        values.containsAll(null) ;
    }

    @Test
    public void testValuesRemove()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        assertFalse(values.remove("Luke")) ;

        assertTrue(values.remove("ciao")) ;

        assertEquals(3,values.size()) ;
        assertEquals(3,adapter.size()) ;
    }

    @Test(expected = NullPointerException.class)
    public void testValuesRemoveException()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        values.remove(null) ;
    }

    @Test
    public void testValuesRemoveAll()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        Collection<String> c = new CollectionAdapter() ;
        c.add("ciao") ;
        c.add("Obi") ;

        assertTrue(values.removeAll(c)) ;

        assertEquals(2,values.size()) ;
        assertEquals(2,adapter.size()) ;

        assertTrue(values.removeAll(c)) ;

        assertEquals(1,values.size()) ;
        assertEquals(1,adapter.size()) ;

        assertFalse(values.removeAll(c)) ;
    }

    @Test
    public void testValuesRemoveAllVoid()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        Collection<String> c = new CollectionAdapter() ;

        assertFalse(values.removeAll(c)) ;
    }

    @Test(expected = NullPointerException.class)
    public void testValuesRemoveAllException()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        values.removeAll(null) ;
    }

    @Test
    public void testValuesRetainAll()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        Collection<String> c = new CollectionAdapter() ;
        c.add("Obi") ;

        assertTrue(values.retainAll(c)) ;

        assertEquals(1,values.size()) ;
        assertEquals(1,adapter.size()) ;
        assertTrue(values.contains("Obi")) ;
        assertTrue(values.contains("Obi")) ;

        assertFalse(values.retainAll(c)) ;
    }

    @Test
    public void testValuesRetainAllVoid()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        Collection<String> c = new CollectionAdapter() ;

        assertTrue(values.retainAll(c)) ;

        assertEquals(0,values.size()) ;
        assertEquals(0,adapter.size()) ;
    }

    @Test(expected = NullPointerException.class)
    public void testValuesRetainAllException()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        values.retainAll(null) ;
    }

    @Test
    public void testValuesIteratorHasNext()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        Iterator<String> iterator = values.iterator() ;

        int i = 0 ;
        while(iterator.hasNext())
        {
            iterator.next() ;
            i++ ;
        }

        assertEquals(4,i) ;
    }

    @Test
    public void testValuesIteratorNext()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        Iterator<String> iterator = values.iterator() ;

        assertEquals("ciao",iterator.next()) ;
        assertEquals("Sauron",iterator.next()) ;
        assertEquals("Obi",iterator.next()) ;
        assertEquals("ciao",iterator.next()) ;
    }

    @Test(expected = NoSuchElementException.class)
    public void testValuesIteratorNextException()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        Iterator<String> iterator = values.iterator() ;

        iterator.next() ;
        iterator.next() ;
        iterator.next() ;
        iterator.next() ;
        iterator.next() ;
    }

    @Test
    public void testValuesIteratorRemove()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        Iterator<String> iterator = values.iterator() ;

        iterator.next() ;
        iterator.next() ;
        iterator.remove() ;

        assertEquals(3,values.size()) ;
        assertEquals(3,adapter.size()) ;
        assertFalse(values.contains("Sauron")) ;
        assertFalse(adapter.containsValue("Sauron")) ;
    }

    @Test(expected = IllegalStateException.class)
    public void testValuesIteratorRemoveException1()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        Iterator<String> iterator = values.iterator() ;

        iterator.remove() ;
    }

    @Test(expected = IllegalStateException.class)
    public void testValuesIteratorRemoveException2()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        Iterator<String> iterator = values.iterator() ;

        iterator.next() ;
        iterator.remove() ;
        iterator.remove() ;
    }

    @Test
    public void testValuesToArray()
    {
        Collection<String> values = adapter.values() ;

        adapter.put(1,"ciao") ;
        adapter.put(3,"Sauron") ;
        adapter.put(2,"Obi") ;
        adapter.put(4,"ciao") ;

        Iterator<String> iterator = values.iterator() ;

        Object[] array = values.toArray() ;

        assertEquals(array[0],iterator.next()) ;
        assertEquals(array[1],iterator.next()) ;
        assertEquals(array[2],iterator.next()) ;
        assertEquals(array[3],iterator.next()) ;
    }
}