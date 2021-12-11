import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class ListAdapterTest {
    private ListAdapter adapter;

    @Before
    public void setUp() {
        adapter = new ListAdapter();
    }

    @Test
    public void testNewSize() {
        assertEquals(0, adapter.size());
    }

    @Test
    public void testSize() {
        adapter.add(1);

        assertEquals(1, adapter.size());
    }

    @Test
    public void testNewIsEmpty() {
        assertTrue(adapter.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        adapter.add(1);

        assertFalse(adapter.isEmpty());
    }

    @Test
    public void testClear() {
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);

        adapter.clear();

        assertEquals(0, adapter.size());
    }

    @Test
    public void testGet()
    {
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);

        assertEquals(2, (int) adapter.get(1));
        assertEquals(1, (int) adapter.get(0));
        assertEquals(3, (int) adapter.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetException1() {
        adapter.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetException2() {
        adapter.add(1);

        adapter.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetException3() {
        adapter.add(1);

        adapter.get(adapter.size());
    }

    @Test
    public void testSet() {
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);

        assertEquals(2, (int) adapter.set(1, 5));

        assertEquals(5, (int) adapter.get(1));
        assertEquals(3,adapter.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetException1() {
        adapter.set(0, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetException2() {
        adapter.add(1);

        adapter.set(-1, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetException3() {
        adapter.add(1);

        adapter.set(adapter.size(), 5);
    }

    @Test(expected = NullPointerException.class)
    public void testSetException4() {
        adapter.add(1);

        adapter.set(0, null);
    }

    @Test
    public void testIndexOf() {
        adapter.add(2) ;
        adapter.add(1) ;
        adapter.add(5) ;
        adapter.add(1) ;
        adapter.add(3) ;
        adapter.add(1) ;

        assertEquals(1, adapter.indexOf(1)) ;
        assertEquals(-1,adapter.indexOf(7)) ;
    }

    @Test(expected = NullPointerException.class)
    public void testIndexOfException() {
        adapter.indexOf(null) ;
    }

    @Test
    public void testLastIndexOf() {
        adapter.add(2) ;
        adapter.add(1) ;
        adapter.add(5) ;
        adapter.add(1) ;
        adapter.add(3) ;
        adapter.add(1) ;

        assertEquals(5, adapter.lastIndexOf(1)) ;
        assertEquals(-1,adapter.lastIndexOf(7)) ;
    }

    @Test(expected = NullPointerException.class)
    public void testLastIndexOfException() {
        adapter.lastIndexOf(null) ;
    }

    @Test
    public void testHashCode() {
        List<Integer> c = new ListAdapter();
        c.add(1);
        c.add(2);

        adapter.add(1);
        adapter.add(2);

        assertEquals(adapter.hashCode(), c.hashCode());

        adapter.add(3);

        assertNotEquals(adapter.hashCode(), c.hashCode());
    }

    // Test add(int index, E element)
    @Test
    public void testAddWithIndex() {
        adapter.add(1);
        adapter.add(1);
        adapter.add(5);

        adapter.add(2, 2);

        assertEquals(2, adapter.get(2));

        assertEquals(5, adapter.get(3));

        assertEquals(4, adapter.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddWithIndexException1() {
        adapter.add(1, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddWithIndexException2() {
        adapter.add(0);

        adapter.add(-1, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddWithIndexException3() {
        adapter.add(0);

        adapter.add(2, 1);
    }

    // Test add(E e)
    @Test
    public void testAdd() {
        adapter.add(10);

        assertTrue(adapter.add(2));

        assertEquals(2, adapter.get(1));

        assertEquals(2, adapter.size());

        assertFalse(adapter.add(2));

        assertEquals(3, adapter.size());
    }

    @Test(expected = NullPointerException.class)
    public void testAddException() {
        adapter.add(null);
    }

    @Test
    public void testAddAllWithIndex() {
        Collection<Integer> c = new CollectionAdapter();
        c.add(1);
        c.add(2);
        c.add(3);

        adapter.add(1);
        adapter.add(5);

        assertTrue(adapter.addAll(1, c));

        assertEquals(1, adapter.get(0));
        assertEquals(1, adapter.get(1));
        assertEquals(2, adapter.get(2));
        assertEquals(3, adapter.get(3));
        assertEquals(5, adapter.get(4));

        assertEquals(5, adapter.size());

        assertFalse(adapter.addAll(1, c));

        assertEquals(1, adapter.get(0));
        assertEquals(1, adapter.get(1));
        assertEquals(2, adapter.get(2));
        assertEquals(3, adapter.get(3));
        assertEquals(1, adapter.get(4));
        assertEquals(2, adapter.get(5));
        assertEquals(3, adapter.get(6));
        assertEquals(5, adapter.get(7));

        assertEquals(8, adapter.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllWithIndexException1() {
        Collection<Integer> c = new CollectionAdapter();
        c.add(0);

        adapter.add(1);

        adapter.addAll(-1, c);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllWithIndexException2() {
        Collection<Integer> c = new CollectionAdapter();
        c.add(0);

        adapter.add(1);

        adapter.addAll(adapter.size() + 1, c);
    }

    @Test(expected = NullPointerException.class)
    public void testAddAllWithIndexException3() {
        adapter.add(1);

        adapter.addAll(0, null);
    }

    @Test
    public void testAddAll() {
        Collection<Integer> c = new CollectionAdapter();
        c.add(1);
        c.add(2);

        adapter.add(1);
        adapter.add(5);

        assertTrue(adapter.addAll(c));

        assertEquals(1, adapter.get(0));
        assertEquals(5, adapter.get(1));
        assertEquals(1, adapter.get(2));
        assertEquals(2, adapter.get(3));

        assertEquals(4, adapter.size());

        assertFalse(adapter.addAll(c));

        assertEquals(1, adapter.get(0));
        assertEquals(5, adapter.get(1));
        assertEquals(1, adapter.get(2));
        assertEquals(2, adapter.get(3));
        assertEquals(1, adapter.get(4));
        assertEquals(2, adapter.get(5));

        assertEquals(6, adapter.size());
    }

    @Test
    public void testAddAllVoid() {
        Collection<Integer> c = new CollectionAdapter();

        adapter.add(1);
        adapter.add(5);

        assertFalse(adapter.addAll(c));
    }


    @Test(expected = NullPointerException.class)
    public void testAddAllException() {
        adapter.addAll(null);
    }

    @Test
    public void testRemoveWithIndex() {
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);

        assertEquals(2, adapter.remove(1));

        assertEquals(2, adapter.size());

        assertEquals(1, adapter.get(0));
        assertEquals(3, adapter.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveWithIndexException1() {
        adapter.add(1);

        adapter.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveWithIndexException2() {
        adapter.add(1);

        adapter.remove(1);
    }


    @Test
    public void testRemove() {
        adapter.add(1);
        adapter.add(2);
        adapter.add(1);

        assertTrue(adapter.remove((Integer) 1));
        assertFalse(adapter.remove((Integer)7));

        assertEquals(2, adapter.size());

        assertEquals(1, adapter.get(1));
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveException() {
        adapter.add(0);

        adapter.remove(null);
    }

    @Test
    public void testRemoveAll() {
        Collection<Integer> c = new CollectionAdapter();
        c.add(3);
        c.add(4);

        adapter.add(0);
        adapter.add(1);
        adapter.add(1);
        adapter.add(2);
        adapter.add(1);

        assertFalse(adapter.removeAll(c));

        assertEquals(5, adapter.size());

        c.add(2);
        c.add(1);

        assertTrue(adapter.removeAll(c));

        assertEquals(1, adapter.size());

        assertEquals(0, adapter.get(0));
    }

    @Test
    public void testRemoveAllVoid() {
        Collection<Integer> c = new CollectionAdapter();

        adapter.add(0);
        adapter.add(1);
        adapter.add(1);
        adapter.add(2);
        adapter.add(1);

        assertFalse(adapter.removeAll(c));
    }


    @Test(expected = NullPointerException.class)
    public void testRemoveAllException() {
        adapter.removeAll(null);
    }

    @Test
    public void testContains() {
        adapter.add(1);
        adapter.add(1);

        assertTrue(adapter.contains(1));
        assertFalse(adapter.contains(2));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsException() {
        adapter.add(1);

        adapter.contains(null);
    }

    @Test
    public void testContainsAll() {
        Collection<Integer> c = new CollectionAdapter();
        c.add(1);
        c.add(2);

        adapter.add(1);
        adapter.add(1);
        adapter.add(2);

        assertTrue(adapter.containsAll(c));

        c.add(7) ;

        assertFalse(adapter.containsAll(c));
    }

    @Test
    public void testContainsAllVoid() {
        Collection<Integer> c = new CollectionAdapter();

        assertTrue(adapter.containsAll(c));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllException() {
        adapter.add(1);

        adapter.containsAll(null);
    }


    @Test
    public void testRetainAll() {
        Collection<Integer> c = new CollectionAdapter();
        c.add(1);
        c.add(2);

        adapter.add(1);
        adapter.add(3);
        adapter.add(3);

        assertTrue(adapter.retainAll(c));

        assertEquals(1, adapter.size());

        assertEquals(1, adapter.get(0));

        assertFalse(adapter.retainAll(c));

        assertEquals(1, adapter.size());
    }

    @Test
    public void testRetainAllVoid() {
        Collection<Integer> c = new CollectionAdapter();

        adapter.add(1);
        adapter.add(3);
        adapter.add(3);

        assertTrue(adapter.retainAll(c));

        assertEquals(0, adapter.size());
    }

    @Test(expected = NullPointerException.class)
    public void retainAllException() {
        adapter.retainAll(null);
    }

    @Test
    public void testEquals() {
        List<Integer> c = new ListAdapter();
        c.add(1);
        c.add(2);

        adapter.add(1);
        adapter.add(2);

        assertTrue(adapter.equals(c));

        c.add(1);

        assertFalse(adapter.equals(c));
    }

    @Test
    public void testEqualsVoid() {
        List<Integer> c = new ListAdapter();

        assertTrue(adapter.equals(c));
    }

    @Test
    public void testEqualsInstanceOf() {
        Set<Integer> c = new SetAdapter();

        c.add(1);

        adapter.add(1);

        assertFalse(adapter.equals(c));
    }

    @Test
    public void toArray() {
        adapter.add(1);
        adapter.add(2);
        adapter.add(1);
        adapter.add(3);

        Iterator<Integer> iterator = adapter.iterator();

        Object[] elements = adapter.toArray();

        int i = 0;
        while (iterator.hasNext()) {
            assertEquals(iterator.next(), elements[i]);
            i++;
        }
    }

    /*

    TEST ITERATOR()

     */

    @Test
    public void testIteratorHasNext() {
        adapter.add(1);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);

        Iterator<Integer> iterator = adapter.iterator();

        int k = 0;
        while (iterator.hasNext()) {
            iterator.next();
            k++;
        }

        assertEquals(k, adapter.size());
    }

    @Test
    public void testIteratorNext() {
        adapter.add(1);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);

        Iterator<Integer> iterator = adapter.iterator();

        assertEquals(1, (int) iterator.next());
        assertEquals(1, (int) iterator.next());
        assertEquals(2, (int) iterator.next());
        assertEquals(3, (int) iterator.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNextException() {
        adapter.add(1);

        Iterator<Integer> iterator = adapter.iterator();

        iterator.next();
        iterator.next();
    }

    @Test
    public void testIteratorRemove() {
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(2);

        Iterator<Integer> iterator = adapter.iterator();

        iterator.next();
        iterator.next();

        iterator.remove();

        assertNotEquals(2, adapter.get(1));

        assertEquals(3, adapter.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testIteratorRemoveException1() {
        adapter.add(1);

        Iterator<Integer> iterator = adapter.iterator();

        iterator.next();
        iterator.remove();

        iterator.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testIteratorRemoveException2() {
        adapter.add(1);

        adapter.iterator().remove();
    }

    /*

    TEST LISTITERATOR()

     */

    @Test
    public void testListIteratorHasNext() {
        adapter.add(1);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);

        ListIterator<Integer> iterator = adapter.listIterator();

        int k = 0;
        while (iterator.hasNext()) {
            iterator.next();
            k++;
        }

        assertEquals(k, adapter.size());
    }

    @Test
    public void testListIteratorHasPrevious() {
        adapter.add(1);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);

        ListIterator<Integer> iterator = adapter.listIterator();

        while (iterator.hasNext()) {
            iterator.next();
        }

        int k = adapter.size();
        while (iterator.hasPrevious()) {
            iterator.previous();
            k--;
        }

        assertEquals(0, k);
    }

    @Test
    public void testListIteratorNext() {
        adapter.add(1);
        adapter.add(2);
        adapter.add(1);
        adapter.add(3);

        ListIterator<Integer> iterator = adapter.listIterator();

        assertEquals(1, (int) iterator.next());
        assertEquals(2, (int) iterator.next());
        assertEquals(1, (int) iterator.next());
        assertEquals(3, (int) iterator.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void testListIteratorNextException() {
        adapter.add(1);

        ListIterator<Integer> iterator = adapter.listIterator();

        iterator.next();
        iterator.next();
    }

    @Test
    public void testListIteratorPrevious() {
        adapter.add(1);
        adapter.add(2);
        adapter.add(1);
        adapter.add(3);

        ListIterator<Integer> iterator = adapter.listIterator();

        while (iterator.hasNext()) {
            iterator.next();
        }

        assertEquals(3, (int) iterator.previous());
        assertEquals(1, (int) iterator.previous());
        assertEquals(2, (int) iterator.previous());
        assertEquals(1, (int) iterator.previous());
    }

    @Test(expected = NoSuchElementException.class)
    public void testListIteratorPreviousException() {
        ListIterator<Integer> iterator = adapter.listIterator();

        iterator.previous();
    }

    @Test
    public void testListIteratorNextIndex() {
        adapter.add(1);
        adapter.add(2);
        adapter.add(1);
        adapter.add(3);

        ListIterator<Integer> iterator = adapter.listIterator();

        assertEquals(0, iterator.nextIndex());
        iterator.next();

        assertEquals(1, iterator.nextIndex());
        iterator.next();

        assertEquals(2, iterator.nextIndex());
        iterator.next();

        assertEquals(3, iterator.nextIndex());
        iterator.next();

        assertEquals(adapter.size(), iterator.nextIndex());
    }

    @Test
    public void testListIteratorPreviousIndex()
    {
        adapter.add(1);
        adapter.add(2);
        adapter.add(1);
        adapter.add(3);

        ListIterator<Integer> iterator = adapter.listIterator();

        while (iterator.hasNext()) {
            iterator.next();
        }

        assertEquals(3, iterator.previousIndex());
        iterator.previous();

        assertEquals(2, iterator.previousIndex());
        iterator.previous();

        assertEquals(1, iterator.previousIndex());
        iterator.previous();

        assertEquals(0, iterator.previousIndex());
        iterator.previous();

        assertEquals(-1, iterator.previousIndex());
    }

    @Test
    public void testListIteratorAdd()
    {
        adapter.add(1);
        adapter.add(5);

        ListIterator<Integer> iterator = adapter.listIterator();

        iterator.next() ;

        iterator.add(2);

        assertEquals(1, adapter.get(0));
        assertEquals(2, adapter.get(1));
        assertEquals(5, adapter.get(2));

        assertEquals(3, adapter.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListIteratorAddException() {
        adapter.add(1);

        ListIterator<Integer> iterator = adapter.listIterator();

        iterator.add(null);
    }

    @Test
    public void testListIteratorRemove() {
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);

        ListIterator<Integer> iterator = adapter.listIterator();

        iterator.next();
        iterator.remove();

        assertFalse(adapter.contains(1));

        assertEquals(2, adapter.size());

        iterator.next();
        iterator.previous();
        iterator.remove();

        assertFalse(adapter.contains((2)));

        assertEquals(1, adapter.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testListIteratorRemoveException1() {
        adapter.add(1);

        ListIterator<Integer> iterator = adapter.listIterator();

        iterator.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testListIteratorRemoveException2() {
        adapter.add(1);
        adapter.add(2);

        ListIterator<Integer> iterator = adapter.listIterator();

        iterator.next();
        iterator.remove();

        iterator.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testListIteratorRemoveException3() {
        adapter.add(1);
        adapter.add(2);

        ListIterator<Integer> iterator = adapter.listIterator();

        iterator.next();
        iterator.next();
        iterator.previous();
        iterator.remove();

        iterator.remove();
    }

    @Test
    public void testListIteratorSet()
    {
        adapter.add(0) ;
        adapter.add(1) ;
        adapter.add(2) ;
        adapter.add(3) ;
        adapter.add(4) ;

        ListIterator<Integer> iterator = adapter.listIterator() ;

        iterator.next() ;
        iterator.next() ;
        iterator.set(7) ;

        assertEquals(7,adapter.get(1)) ;
        assertFalse(adapter.contains(1)) ;
        assertEquals(5,adapter.size()) ;

        iterator.next() ;
        iterator.next() ;
        iterator.previous() ;
        iterator.set(9) ;

        assertEquals(9,adapter.get(3)) ;
        assertFalse(adapter.contains(3)) ;
        assertEquals(5,adapter.size()) ;
    }

    @Test(expected = IllegalStateException.class)
    public void testListIteratorSetException1()
    {
        adapter.add(0) ;
        adapter.add(1) ;
        adapter.add(2) ;
        adapter.add(3) ;
        adapter.add(4) ;

        ListIterator<Integer> iterator = adapter.listIterator() ;

        iterator.set(5) ;
    }

    @Test(expected = IllegalStateException.class)
    public void testListIteratorSetException2()
    {
        adapter.add(0) ;
        adapter.add(1) ;
        adapter.add(2) ;
        adapter.add(3) ;
        adapter.add(4) ;

        ListIterator<Integer> iterator = adapter.listIterator() ;

        iterator.next() ;
        iterator.remove() ;

        iterator.set(5) ;
    }

    @Test(expected = IllegalStateException.class)
    public void testListIteratorSetException3()
    {
        adapter.add(0) ;
        adapter.add(1) ;
        adapter.add(2) ;
        adapter.add(3) ;
        adapter.add(4) ;

        ListIterator<Integer> iterator = adapter.listIterator() ;

        iterator.next() ;
        iterator.add(9) ;

        iterator.set(5) ;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListIteratorSetException4()
    {
        adapter.add(0) ;
        adapter.add(1) ;
        adapter.add(2) ;
        adapter.add(3) ;
        adapter.add(4) ;

        ListIterator<Integer> iterator = adapter.listIterator() ;

        iterator.next() ;
        iterator.set(null) ;
    }

    /*

    TEST LISTITERATOR(INDEX)

     */

    @Test(expected = IndexOutOfBoundsException.class)
    public void testListIteratorWithIndexException1() {
        adapter.add(1);

        ListIterator<Integer> iterator = adapter.listIterator(adapter.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testListIteratorWithIndexException2() {
        adapter.add(1);

        ListIterator<Integer> iterator = adapter.listIterator(-1);
    }

    /*

    TEST SUBLIST

     */

    @Test
    public void testSubListSize() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        assertEquals(3, sub.size());
    }

    @Test
    public void testSubListIsEmpty() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 1);

        assertTrue(sub.isEmpty());
        assertEquals(0,sub.size()) ;

        List<Integer> newSub = adapter.subList(1, 4);

        assertFalse(newSub.isEmpty());
        assertEquals(3,newSub.size());
    }

    @Test
    public void testSubListClear()
    {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        sub.clear();

        assertTrue(sub.isEmpty());

        assertEquals(2, adapter.size());

        assertTrue(adapter.contains((Integer) 4));
        assertTrue(adapter.contains((Integer) 0));
    }

    @Test
    public void testSubListGet() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        assertEquals(1, (int) sub.get(0));
        assertEquals(2, (int) sub.get(1));
        assertEquals(3, (int) sub.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListGetException1() {
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(0, 3);

        sub.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListGetException2() {
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(0, 3);

        sub.get(sub.size());
    }

    @Test
    public void testSubListSet() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        assertEquals(2, (int) sub.set(1, 7));

        assertEquals(7, (int) sub.get(1));
        assertEquals(3, (int) sub.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListSetException1() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        sub.set(-1, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListSetException2() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        sub.set(sub.size(), 5);
    }

    @Test(expected = NullPointerException.class)
    public void testSubListSetException3() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        adapter.set(0, null);
    }

    @Test
    public void testSubListHashCode() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub1 = adapter.subList(1, 4);

        List<Integer> list2 = new ListAdapter();

        list2.add(1);
        list2.add(2);
        list2.add(3);

        assertEquals(sub1.hashCode(), list2.hashCode());

        list2.add(10);

        assertNotEquals(sub1.hashCode(), list2.hashCode());
    }

    @Test
    public void testSubListContains() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        assertTrue(sub.contains(1));

        assertFalse(sub.contains(0));
    }

    @Test(expected = NullPointerException.class)
    public void testSubListContainsException() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        sub.contains(null);
    }

    // Test di add(E e)
    @Test
    public void testSubListAdd() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        assertTrue(sub.add(7));

        assertEquals(4, sub.size());
        assertEquals(6, adapter.size());

        assertEquals(7, (int) sub.get(3));
        assertEquals(7, (int) adapter.get(4));

        assertFalse(sub.add(2));
    }

    @Test(expected = NullPointerException.class)
    public void testSubListAddException() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        sub.add(null);
    }

    // Test di add(int index, E element)
    @Test
    public void testSubListAddWithIndex() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        sub.add(1, 7);

        assertEquals(4, sub.size());
        assertEquals(6, adapter.size());

        assertEquals(7, (int) sub.get(1));
        assertEquals(7, (int) adapter.get(2));

        assertEquals(2, (int) sub.get(2));
        assertEquals(2, (int) adapter.get(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListAddWithIndexException1() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        sub.add(-5, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListAddWithIndexException2() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        sub.add(sub.size() + 1, 1);
    }

    @Test(expected = NullPointerException.class)
    public void testSubListAddWithIndexException3() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        sub.add(0, null);
    }

    // Test di remove(Object o)
    @Test
    public void testSubListRemove() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(1);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        assertTrue(sub.remove((Integer) 1));
        assertFalse(sub.remove((Integer) 5));

        assertEquals(2, sub.size());
        assertEquals(4, adapter.size());

        assertEquals(2, (int) sub.get(0));
        assertEquals(2, (int) adapter.get(1));
    }

    @Test(expected = NullPointerException.class)
    public void testSubListRemoveException() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(1);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        sub.remove(null);
    }

    // Test di remove(int index)
    @Test
    public void testSubListRemoveWithIndex() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        assertEquals(1, (int) sub.remove(0));

        assertFalse(sub.contains(1));
        assertFalse(adapter.contains(1));

        assertEquals(2, sub.size());
        assertEquals(4, adapter.size());

        assertEquals(2, (int) sub.get(0));
        assertEquals(2, (int) adapter.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListRemoveWithIndexException1() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        sub.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListRemoveWithIndexException2() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(3);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        sub.remove(sub.size());
    }

    @Test
    public void testSubListContainsAll() {
        Collection<Integer> c = new CollectionAdapter();
        c.add(1);
        c.add(2);

        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(1);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        assertTrue(sub.containsAll(c));

        c.add(5);

        assertFalse(sub.containsAll(c));
    }

    @Test
    public void testSubListContainsAllVoid() {
        Collection<Integer> c = new CollectionAdapter();


        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(1);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 1);

        assertTrue(sub.containsAll(c));
    }

    @Test(expected = NullPointerException.class)
    public void testSubListContainsAllException() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(1);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        sub.containsAll(null);
    }

    // Test di addAll(Collection c)
    @Test
    public void testSubListAddAll() {
        Collection<Integer> c = new CollectionAdapter();
        c.add(1);
        c.add(2);

        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(7);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        assertFalse(sub.addAll(c));

        assertEquals(5, sub.size());
        assertEquals(7, adapter.size());

        assertEquals(7, (int) sub.get(2));
        assertEquals(1, (int) sub.get(3));
        assertEquals(2, (int) sub.get(4));
        assertEquals(1, (int) adapter.get(4));
        assertEquals(2, (int) adapter.get(5));

        c.add(9);

        assertTrue(sub.addAll(c));
    }

    @Test
    public void testSubListAddAllVoid() {
        Collection<Integer> c = new CollectionAdapter();

        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(7);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        assertFalse(sub.addAll(c));

        assertEquals(3, sub.size());
        assertEquals(5, adapter.size());
    }

    @Test(expected = NullPointerException.class)
    public void testSubListAddAllException() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(2);
        adapter.add(1);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        sub.containsAll(null);
    }

    // Test di addAll(int index, Collection c)
    @Test
    public void testSubListAddAllWithIndex() {
        Collection<Integer> c = new CollectionAdapter();
        c.add(2);
        c.add(1);

        adapter.add(0);
        adapter.add(1);
        adapter.add(4);
        adapter.add(2);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        assertFalse(sub.addAll(1, c));

        assertEquals(5, sub.size());
        assertEquals(7, adapter.size());

        assertEquals(2, (int) sub.get(1));
        assertEquals(1, (int) sub.get(2));
        assertEquals(4, (int) sub.get(3));

        assertEquals(2, (int) adapter.get(2));
        assertEquals(1, (int) adapter.get(3));
        assertEquals(4, (int) adapter.get(4));

        c.add(9);

        assertTrue(sub.addAll(2, c));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListAddAllWithIndexException1() {
        Collection<Integer> c = new CollectionAdapter();
        c.add(2);
        c.add(1);

        adapter.add(0);
        adapter.add(1);
        adapter.add(4);
        adapter.add(2);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        sub.addAll(sub.size() + 1, c);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListAddAllWithIndexException2() {
        Collection<Integer> c = new CollectionAdapter();
        c.add(2);
        c.add(1);

        adapter.add(0);
        adapter.add(1);
        adapter.add(4);
        adapter.add(2);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        sub.addAll(-1, c);
    }

    @Test(expected = NullPointerException.class)
    public void testSubListAddAllWithIndexException3() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(4);
        adapter.add(2);
        adapter.add(4);

        List<Integer> sub = adapter.subList(1, 4);

        sub.addAll(2, null);
    }

    @Test
    public void testSubListRemoveAll() {
        Collection<Integer> c = new CollectionAdapter();
        c.add(2);
        c.add(5);

        adapter.add(0);
        adapter.add(1);
        adapter.add(4);
        adapter.add(1);
        adapter.add(3);

        List<Integer> sub = adapter.subList(1, 4);

        assertFalse(sub.removeAll(c));

        c.add(1);

        assertTrue(sub.removeAll(c));

        assertEquals(1, sub.size());
        assertEquals(3, adapter.size());

        assertEquals(4, (int) sub.get(0));
        assertEquals(4, (int) adapter.get(1));
    }

    @Test
    public void testSubListRemoveAllVoid() {
        Collection<Integer> c = new CollectionAdapter();

        adapter.add(0);
        adapter.add(1);
        adapter.add(4);
        adapter.add(1);
        adapter.add(3);

        List<Integer> sub = adapter.subList(1, 4);

        assertFalse(sub.removeAll(c));

        assertEquals(3, sub.size());
        assertEquals(5, adapter.size());
    }

    @Test(expected = NullPointerException.class)
    public void testSubListRemoveAllException() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(4);
        adapter.add(1);
        adapter.add(3);

        List<Integer> sub = adapter.subList(1, 4);

        sub.removeAll(null);
    }

    @Test
    public void testSubListRetainAll()
    {
        Collection<Integer> c1 = new CollectionAdapter();
        c1.add(1);
        c1.add(4);

        Collection<Integer> c2 = new CollectionAdapter();
        c2.add(4);

        adapter.add(0);
        adapter.add(1);
        adapter.add(4);
        adapter.add(1);
        adapter.add(1);

        List<Integer> sub = adapter.subList(1, 4);

        assertFalse(sub.retainAll(c1));

        assertEquals(3, sub.size());
        assertEquals(5, adapter.size());

        assertTrue(sub.retainAll(c2));

        assertEquals(1, sub.size());
        assertEquals(3, adapter.size());

        assertEquals(4, (int) sub.get(0));
        assertEquals(4, (int) adapter.get(1));
    }

    @Test
    public void testSubListRetainAllVoid()
    {
        Collection<Integer> c = new CollectionAdapter();

        adapter.add(0);
        adapter.add(1);
        adapter.add(4);
        adapter.add(1);
        adapter.add(1);

        List<Integer> sub = adapter.subList(1, 4);

        assertTrue(sub.retainAll(c)) ;

        assertEquals(0,sub.size());
        assertEquals(2,adapter.size());
    }

    @Test(expected = NullPointerException.class)
    public void testSubListRetainAllException() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(4);
        adapter.add(1);
        adapter.add(1);

        List<Integer> sub = adapter.subList(1, 4);

        sub.retainAll(null);
    }

    @Test
    public void testSubListEquals() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(4);
        adapter.add(1);
        adapter.add(1);

        List<Integer> sub0 = adapter.subList(1, 3);

        List<Integer> sub1 = adapter.subList(1, 3);
        assertTrue(sub0.equals(sub1));

        List<Integer> sub2 = adapter.subList(1, 4);
        assertFalse(sub0.equals(sub2));

        List<Integer> sub3 = adapter.subList(1, 2);
        assertFalse(sub0.equals(sub2));

        List<Integer> sub4 = adapter.subList(1, 3);
        sub4.remove((Integer) 4);
        sub4.add(7);
        assertTrue(sub0.equals(sub4));
    }

    @Test
    public void testSubListEqualsInstanceOf()
    {
        adapter.add(0);
        adapter.add(1);
        adapter.add(4);
        adapter.add(1);
        adapter.add(1);

        List<Integer> sub = adapter.subList(1, 3);

        Set<Integer> set = new SetAdapter();
        set.add(1);
        set.add(4);

        assertFalse(sub.equals(set));
    }

    @Test
    public void testSubListIndexOf() {
        adapter.add(1);
        adapter.add(1);
        adapter.add(4);
        adapter.add(1);
        adapter.add(1);

        List<Integer> sub = adapter.subList(1, 4);

        assertEquals(0, sub.indexOf(1));
    }

    @Test(expected = NullPointerException.class)
    public void testSubListIndexOfException() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(4);
        adapter.add(1);
        adapter.add(1);

        List<Integer> sub = adapter.subList(1, 4);
        sub.indexOf(null);
    }

    @Test
    public void testSubListLastIndexOf() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(4);
        adapter.add(1);
        adapter.add(1);

        List<Integer> sub = adapter.subList(1, 4);

        assertEquals(2, sub.lastIndexOf(1));
    }

    @Test(expected = NullPointerException.class)
    public void testSubListLastIndexOfException() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(4);
        adapter.add(1);
        adapter.add(1);

        List<Integer> sub = adapter.subList(1, 4);

        sub.lastIndexOf(null);
    }

    @Test
    public void testSubListToArray() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(4);
        adapter.add(1);
        adapter.add(1);

        List<Integer> sub = adapter.subList(1, 4);

        Object[] elements = sub.toArray();

        for (int i = 0; i < elements.length; i++)
        {
            assertEquals(elements[i], sub.get(i));
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSubListSubList() {
        adapter.add(0);
        adapter.add(1);
        adapter.add(4);
        adapter.add(1);
        adapter.add(1);

        List<Integer> sub = adapter.subList(1, 4);

        List<Integer> newSub = sub.subList(0, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListException1() {
        adapter.add(1);
        adapter.add(2);

        adapter.subList(-1, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListException2() {
        adapter.add(1);
        adapter.add(2);

        adapter.subList(0, adapter.size() + 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListException3() {
        adapter.add(1);
        adapter.add(2);

        adapter.subList(1, 0);
    }

    /*

    TEST DI LISTITERATOR DI SUBLIST
    Testo solamente i metodi add(), set() e remove() dell'iteratore,
    in modo da capire se le modifiche apportate alla sublist si
    ripercuotono sulla list.
    Gli altri metodi non vengono testati in quanto l'iteratore di
    sublist è identico a quello di list.

     */

    @Test
    public void testSubListListIteratorAdd()
    {
        adapter.add(0) ;
        adapter.add(1) ;
        adapter.add(2) ;
        adapter.add(3) ;
        adapter.add(4) ;

        List<Integer> sub = adapter.subList(1, 4) ;

        ListIterator<Integer> iterator = sub.listIterator() ;

        iterator.add(7) ;

        iterator.next() ;

        iterator.add(13) ;

        assertEquals(7,(int)sub.get(0)) ;
        assertEquals(7, adapter.get(1)) ;

        assertEquals(13,(int)sub.get(2)) ;
        assertEquals(13, adapter.get(3)) ;

        assertEquals(5, sub.size()) ;
        assertEquals(7, adapter.size()) ;
    }

    @Test
    public void testSubListListIteratorRemove()
    {
        adapter.add(0) ;
        adapter.add(1) ;
        adapter.add(2) ;
        adapter.add(3) ;
        adapter.add(4) ;

        List<Integer> sub = adapter.subList(1, 4) ;

        ListIterator<Integer> iterator = sub.listIterator() ;

        iterator.next() ;

        iterator.remove() ;

        assertTrue(!sub.contains(1)) ;
        assertTrue(!sub.contains(1)) ;

        iterator.next() ;
        iterator.next() ;
        iterator.previous() ;

        iterator.remove() ;

        assertTrue(!sub.contains(3)) ;
        assertTrue(!adapter.contains(3)) ;
    }

    @Test
    public void testSubListListIteratorSet()
    {
        adapter.add(0) ;
        adapter.add(1) ;
        adapter.add(2) ;
        adapter.add(3) ;
        adapter.add(4) ;

        List<Integer> sub = adapter.subList(1, 4) ;

        ListIterator<Integer> iterator = sub.listIterator() ;

        iterator.next() ;

        iterator.set(7) ;

        assertEquals(7,(int)sub.get(0)) ;

        iterator.next() ;
        iterator.previous() ;

        iterator.set(9) ;

        assertEquals(9,(int)sub.get(1)) ;
        assertFalse(sub.contains(2)) ;
        assertFalse(adapter.contains(2)) ;

        assertEquals(3,sub.size()) ;
        assertEquals(5,adapter.size()) ;
    }
}
