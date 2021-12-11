import java.util.*;

public class ListAdapterListIterator<E> extends ListAdapterIterator<E> implements ListIterator<E>
{
    private boolean previous ;

    public ListAdapterListIterator(int f, int t, List<E> l)
    {
        super(f, t, l) ;
        previous = false ;
    }

    /**
     * Returns true if this list iterator has more elements when traversing the list in the forward direction. (In other words, returns true if next would return an element rather than throwing an exception.)
     *
     * @return true if the list iterator has more elements when traversing the list in the forward direction.
     */
    @Override
    public boolean hasNext()
    {
        return super.hasNext() ;
    }

    /**
     * Returns true if this list iterator has more elements when traversing the list in the reverse direction. (In other words, returns true if previous would return an element rather than throwing an exception.)
     *
     * @return true if the list iterator has more elements when traversing the list in the reverse direction.
     */
    @Override
    public boolean hasPrevious()
    {
        return index > 0 ;
    }

    /**
     * Returns the next element in the list. This method may be called repeatedly to iterate through the list, or intermixed with calls to previous to go back and forth. (Note that alternating calls to next and previous will return the same element repeatedly.)
     *
     * @return the next element in the list.
     * @throws NoSuchElementException if the iteration has no next element.
     */
    @Override
    public E next()
    {
        E out = super.next() ;
        previous = false ;

        return out ;
    }

    /**
     * Returns the previous element in the list. This method may be called repeatedly to iterate through the list backwards, or intermixed with calls to next to go back and forth. (Note that alternating calls to next and previous will return the same element repeatedly.)
     *
     * @return the previous element in the list.
     * @throws NoSuchElementException if the iteration has no previous element.
     */
    @Override
    public E previous()
    {
        if(!hasPrevious())
            throw new NoSuchElementException() ;

        previous = true ;
        next = false ;
        index-- ;

        return list.get(index) ;
    }

    /**
     * Returns the index of the element that would be returned by a subsequent call to next. (Returns list size if the list iterator is at the end of the list.)
     *
     * @return the index of the element that would be returned by a subsequent call to next, or list size if list iterator is at end of list.
     */
    @Override
    public int nextIndex()
    {
        return index ;
    }

    /**
     * Returns the index of the element that would be returned by a subsequent call to previous. (Returns -1 if the list iterator is at the beginning of the list.)
     *
     * @return the index of the element that would be returned by a subsequent call to previous, or -1 if list iterator is at beginning of list.
     */
    @Override
    public int previousIndex()
    {
        if(index == 0)
            return -1 ;

        return index - 1 ;
    }

    /**
     * Inserts the specified element into the list. The element is inserted immediately before the next element that would be returned by next, if any, and after the next element that would be returned by previous, if any. (If the list contains no elements, the new element becomes the sole element on the list.) The new element is inserted before the implicit cursor: a subsequent call to next would be unaffected, and a subsequent call to previous would return the new element. (This call increases by one the value that would be returned by a call to nextIndex or previousIndex.)
     *
     * @param e the element to insert.
     * @throws IllegalArgumentException if some aspect of this element prevents it from being added to this list.
     * @throws ClassCastException if the class of the specified element prevents it from being added to this list.
     */
    @Override
    public void add(E e)
    {
        if(e == null)
            throw new IllegalArgumentException() ;

        list.add(index,e) ;

        index++ ;
        to++ ;
        next = false ;
        previous = false ;
    }

    /**
     * Removes from the list the last element that was returned by next or previous. This call can only be made once per call to next or previous. It can be made only if ListIterator.add has not been called after the last call to next or previous.
     */
    @Override
    public void remove()
    {
        if(next)
        {
            super.remove() ;
            previous = false ;
        }
        else if(previous)
        {
            list.remove(index) ;
            to-- ;
            next = false ;
            previous = false ;
        }
        else
        {
            throw new IllegalStateException() ;
        }

    }

    /**
     * Replaces the last element returned by next or previous with the specified element. This call can be made only if neither ListIterator.remove nor ListIterator.add have been called after the last call to next or previous.
     *
     * @param e the element with which to replace the last element returned by next or previous.
     * @throws IllegalStateException if neither next nor previous have been called, or remove or add have been called after the last call to next or previous.
     * @throws IllegalArgumentException if some aspect of the specified element prevents it from being added to this list.
     * @throws ClassCastException if the class of the specified element prevents it from being added to this list.
     */
    @Override
    public void set(E e)
    {
        if(e == null)
            throw new IllegalArgumentException() ;

        if(next)
        {
            list.set(index - 1,e) ;
            next = false ;
        }
        else if(previous)
        {
            list.set(index,e) ;
            previous = false ;
        }
        else
            throw new IllegalStateException() ;
    }
}