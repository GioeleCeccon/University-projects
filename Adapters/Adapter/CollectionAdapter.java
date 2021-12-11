import java.util.*;

/**
 * This class implements the Collection inteface. The instances of this class do not contain null elements or duplicates.
 *
 * @param <E> elements' type.
 */
public class CollectionAdapter<E> implements Collection<E>
{
    private Vector<E> vector ;

    public CollectionAdapter()
    {
        vector = new Vector<E>() ;
    }

    /**
     * Returns the number of elements in this collection. If this collection contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     *
     * @return the number of elements in this collection
     */
    @Override
    public int size()
    {
        if(vector.size() > Integer.MAX_VALUE)
            return Integer.MAX_VALUE ;
        else
            return vector.size() ;
    }

    /**
     * Returns true if this collection contains no elements.
     *
     * @return true if this collection contains no elements
     */
    @Override
    public boolean isEmpty()
    {
        return vector.isEmpty() ;
    }

    /**
     *  Removes all of the elements from this collection. This collection will be empty after this method returns.
     */
    @Override
    public void clear()
    {
        vector.removeAllElements() ;
    }

    /**
     * Ensures that this collection contains the specified element. Returns true if this collection changed as a result of the call.
     * Return false if the collection already contains the specified element.
     *
     * @param e - element whose presence in this collection is to be ensured.
     * @return true if this collection changed as a result of the call
     * @throws NullPointerException - if the specified element is null
     * @throws ClassCastException - class of the specified element prevents it from being added to this collection.
     */
    @Override
    public boolean add(E e)
    {
        if(e == null)
            throw new NullPointerException() ;

        if(vector.contains(e))
            return false ;

        vector.addElement(e) ;
        return true ;
    }

    /**
     * Adds all of the elements in the specified collection to this collection. The behavior of this operation is undefined if the specified collection is modified while the operation is in progress. (This implies that the behavior of this call is undefined if the specified collection is this collection, and this collection is nonempty.)
     *
     * @param c elements to be inserted into this collection.
     * @return true if this collection changed as a result of the call
     * @throws NullPointerException - if the specified collection contains one or more null elements, or if the specified collection is null.
     * @throws ClassCastException - if the types of one or more elements in this collection are incompatible with the specified collection
     */
    @Override
    public boolean addAll(Collection<? extends E> c)
    {
        if(c == null)
            throw new NullPointerException() ;

        E[] elements = (E[])c.toArray() ;

        boolean out = false ;

        for(int i = 0; i < elements.length; i++)
        {
            if(!vector.contains(elements[i]))
            {
                vector.addElement(elements[i]) ;
                out = true ;
            }
        }

        return out ;
    }

    /**
     * Removes a single instance of the specified element from this collection, if it is present.
     * Returns true if this collection contained the specified element (or equivalently, if this collection changed as a result of the call).
     *
     * @param o - element to be removed from this collection, if present.
     * @return true if this collection changed as a result of the call
     * @throws NullPointerException - if the specified element is null and this collection does not support null elements.
     * @throws ClassCastException - if the type of the specified element is incompatible with this collection
     */
    @Override
    public boolean remove(Object o)
    {
        if(o == null)
            throw new NullPointerException() ;

        return vector.removeElement(o) ;      //non sono ammessi duplicati
    }

    /**
     * Removes all this collection's elements that are also contained in the specified collection. After this call returns, this collection will contain no elements in common with the specified collection.
     *
     * @param c - elements to be removed from this collection.
     * @return true if this collection changed as a result of the call
     * @throws NullPointerException - if this collection contains one or more null elements or if the specified collection is null.
     * @throws ClassCastException - if the types of one or more elements in this collection are incompatible with the specified collection
     */
    @Override
    public boolean removeAll(Collection<?> c)
    {
        if(c == null)
            throw new NullPointerException() ;

        E[] elements = (E[])c.toArray() ;

        boolean out = false ;

        for(int i = 0; i < elements.length; i++)
        {
            if(vector.contains(elements[i]))
                out = vector.removeElement(elements[i]) ;
        }

        return out ;
    }

    /**
     * Returns true if this collection contains the specified element. More formally, returns true if and only if this collection contains at least one element e such that (o==null ? e==null : o.equals(e)).
     *
     * @param o - element whose presence in this collection is to be tested.
     * @return true if this collection contains the specified element
     * @throws NullPointerException - if the specified element is null and this collection does not support null elements
     * @throws ClassCastException - if the type of the specified element is incompatible with this collection
     */
    @Override
    public boolean contains(Object o)
    {
        if(o == null)
            throw new NullPointerException() ;

        return vector.contains(o);
    }

    /**
     * Returns true if this collection contains all of the elements in the specified collection.
     *
     * @param c - collection to be checked for containment in this collection.
     * @return true if this collection contains all of the elements in the specified collection
     * @throws NullPointerException - if the specified collection contains one or more null elements or if the specified collection is null.
     * @throws ClassCastException - if the types of one or more elements in this collection are incompatible with the specified collection
     */
    @Override
    public boolean containsAll(Collection<?> c)
    {
        if(c == null)
            throw new NullPointerException() ;

        if(c.isEmpty() && this.isEmpty())
            return true ;

        Object[] elements = c.toArray() ;

        boolean out = false ;

        for(int i = 0; i < elements.length; i++)
        {
            if(vector.contains(elements[i]))
                out = true ;
            else
            {
                out = false ;
                break ;
            }
        }

        return out ;
    }

    /**
     * Retains only the elements in this collection that are contained in the specified collection
     *
     * @param c - elements to be retained in this collection
     * @return true if this collection changed as a result of the call
     * @throws NullPointerException - if the specified collection contains one or more null elements or if the specified collection is null
     * @throws ClassCastException - if the types of one or more elements in this collection are incompatible with the specified collection
     */
    @Override
    public boolean retainAll(Collection<?> c)
    {
        if(c == null)
            throw new NullPointerException() ;

        Iterator<E> iterator = this.iterator() ;
        boolean out = false ;

        while(iterator.hasNext())
        {
            E obj = iterator.next() ;

            if(!c.contains(obj))
            {
                iterator.remove() ;
                out = true ;
            }
        }

        return out ;
    }

    /**
     * Compares the specified object with this collection for equality.
     *
     * @param o - Object to be compared for equality with this collection.
     * @return true if the specified object is equal to this collection
     */
    @Override
    public boolean equals(Object o)
    {
        try{
        CollectionAdapter<E> colAdO = (CollectionAdapter)o ;

        if(colAdO.size() == vector.size())
        {
            E[] elements = (E[])colAdO.toArray() ;

            for(int i = 0; i < vector.size() ; i++)
            {
                if(!vector.contains(elements[i]))
                    return false ;
            }
        }
        else
        {
            return false ;
        }

        return true ;
        }
        catch(ClassCastException exc){
            return false ;
        }
    }

    /**
     * Returns the hash code value for this collection.
     *
     * @return the hash code value for this collection
     */
    @Override
    public int hashCode()
    {
        int hashCode = 0 ;
        Iterator<E> i = this.iterator() ;

        while (i.hasNext())
        {
            Object obj = i.next() ;
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode()) ;
        }

        return hashCode ;
    }

    /**
     * Returns an iterator over the elements in this collection. There are no guarantees concerning the order in which the elements are returned.
     *
     * @return an Iterator over the elements in this collection
     */
    @Override
    public Iterator<E> iterator()
    {
        return new CollectionAdapterIterator<E>(vector) ;
    }

    /**
     * Returns an array containing all of the elements in this collection. If the collection makes any guarantees as to what order its elements are returned by its iterator, this method must return the elements in the same order.
     * The returned array will be "safe" in that no references to it are maintained by this collection. (In other words, this method must allocate a new array even if this collection is backed by an array). The caller is thus free to modify the returned array.
     *
     * This method acts as bridge between array-based and collection-based APIs.
     *
     * @return an array containing all of the elements in this collection
     */
    @Override
    public Object[] toArray()
    {
        Object[] out = new Object[vector.size()] ;

        for(int i = 0; i < out.length; i++)
            out[i] = vector.elementAt(i) ;

        return out ;
    }

    /**
     * @throws UnsupportedOperationException whenever the method is called
     */
    @Override
    public <T> T[] toArray(T[] a)
    {
        throw new UnsupportedOperationException() ;
    }
}
