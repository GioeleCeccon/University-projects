import java.util.*;

public class MapSetIterator<K,V> implements Iterator<Map.Entry<K,V>>
{
    Hashtable<K,V> table ;

    private int index ;
    private boolean next ;

    public MapSetIterator(Hashtable<K,V> tab)
    {
        table = tab ;
        index = 0 ;
        next = false ;
    }

    /**
     * Returns true if the iteration has more elements. (In other words, returns true if next would return an element rather than throwing an exception.)
     *
     * @return true if the iterator has more elements
     */
    @Override
    public boolean hasNext()
    {
        return index < table.size() ;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration.
     * @throws NoSuchElementException - iteration has no more elements.
     */
    @Override
    public Map.Entry<K,V> next()
    {
        if(!hasNext())
            throw new NoSuchElementException() ;

        Enumeration elements = table.keys() ;
        Object[] out = new Object[table.size()] ;

        int i = 0 ;
        while(elements.hasMoreElements())
        {
            out[i] = elements.nextElement() ;
            i++ ;
        }

        next = true ;
        Map.Entry<K,V> returned = new MapAdapter.Entry(out[index],table.get(out[index])) ;
        index++ ;

        return returned ;
    }

    /**
     * Removes from the underlying collection the last element returned by the iterator (optional operation). This method can be called only once per call to next. The behavior of an iterator is unspecified if the underlying collection is modified while the iteration is in progress in any way other than by calling this method.
     *
     * @throws IllegalStateException - if the next method has not yet been called, or the remove method has already been called after the last call to the next method.
     */
    @Override
    public void remove()
    {
        if(!next)
            throw new IllegalStateException() ;

        Enumeration elements = table.keys() ;
        Object[] out = new Object[table.size()] ;

        int i = 0 ;
        while(elements.hasMoreElements())
        {
            out[i] = elements.nextElement() ;
            i++ ;
        }

        index-- ;
        table.remove(out[index]) ;
        next = false ;
    }
}
