import java.util.*;

public class CollectionAdapterIterator<E> implements Iterator<E>
{
    private Vector<E> vector ;
    private boolean next ;
    private int index ;

    public CollectionAdapterIterator(Vector<E> vec)
    {
        vector = vec ;
        next = false ;
        index = 0 ;
    }

    /**
     * Returns true if the iteration has more elements. (In other words, returns true if next would return an element rather than throwing an exception.)
     *
     * @return true if the iterator has more elements
     */
    @Override
    public boolean hasNext()
    {
        return index < vector.size() ;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration.
     * @throws NoSuchElementException - iteration has no more elements.
     */
    @Override
    public E next()
    {
        if(!hasNext())
            throw new NoSuchElementException() ;

        next = true ;
        E returned = vector.elementAt(index) ;
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
        if(!next)                                        // se non è stato chiamato next() (next = false) o
            throw new IllegalStateException() ;          // se è già stato chiamato remove() (next = false) lancio eccezzione

        index-- ;
        vector.removeElementAt(index) ;                  // elimino ultimo elemento attraversato dall'iteratore
        next = false ;                                   // no più di un remove() di fila
    }
}