import java.util.*;

/**
 * This class implements the Set interface. The instances of this class do not contain null elements.
 *
 * @param <E> elements' type.
 */
public class SetAdapter<E> implements Set<E>
{
    private Hashtable<E,E> table ;

    public SetAdapter()
    {
        table = new Hashtable<E,E>() ;
    }

    /**
     * Returns the number of elements in this set. If this set contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     *
     * @return the number of elements in this set
     */
    @Override
    public int size()
    {
        if(table.size() > Integer.MAX_VALUE)
            return Integer.MAX_VALUE ;

        return table.size() ;
    }

    /**
     * Returns true if this set contains no elements.
     *
     * @return true if this set contains no elements
     */
    @Override
    public boolean isEmpty()
    {
        return table.isEmpty() ;
    }


    /**
     *  Removes all of the elements from this set. This set will be empty after this method returns.
     */
    @Override
    public void clear()
    {
        table.clear() ;
    }

    /**
     * Adds the specified element to this set if it is not already present. If this set already contains the specified element, the call leaves this set unchanged and returns false. In combination with the restriction on constructors, this ensures that sets never contain duplicate elements.
     *
     * @param e - element to be added to this set
     * @return true if this set did not already contain the specified element.
     * @throws NullPointerException - if the specified element is null
     * @throws ClassCastException - if the class of the specified element prevents it from being added to this set.
     */
    @Override
    public boolean add(E e)
    {
        if(e == null)
            throw new NullPointerException() ;

        boolean out = false ;

        if(!table.containsKey(e))
        {
            table.put(e,e) ;
            out = true ;
        }

        return out ;
    }

    /**
     * Adds all of the elements in the specified collection to this set if they're not already present.  If the specified collection is also a set, the addAll operation effectively modifies this set so that its value is the union of the two sets. The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress.
     *
     * @param c - collection whose elements are to be added to this set.
     * @return true if this set changed as a result of the call.
     * @throws NullPointerException - if the specified collection contains one or more null elements or if the specified collection is null.
     * @throws ClassCastException - if the class of some element of the specified collection prevents it from being added to this set.
     */
    @Override
    public boolean addAll(Collection<? extends E> c)
    {
        if(c == null)
            throw new NullPointerException() ;

        boolean out = false ;

        E[] elements = (E[])c.toArray() ;
        for(int i = 0; i < elements.length; i++)
        {
            if(!table.contains(elements[i]))
            {
                table.put(elements[i],elements[i]) ;
                out = true ;
            }
        }

        return out ;
    }

    /**
     * Removes the specified element from this set if it is present. More formally, removes an element e such that (o==null ? e==null : o.equals(e)), if the set contains such an element. Returns true if the set contained the specified element (or equivalently, if the set changed as a result of the call). (The set will not contain the specified element once the call returns.)
     *
     * @param o - object to be removed from this set, if present.
     * @return true if the set contained the specified element.
     * @throws NullPointerException - if the specified element is null
     * @throws ClassCastException - if the type of the specified element is incompatible with this set
     */
    @Override
    public boolean remove(Object o)
    {
        if(o == null)
            throw new NullPointerException() ;

        boolean out = false ;

        if(table.containsKey((E)o))
        {
            out = true ;
            table.remove(o) ;
        }

        return out ;
    }

    /**
     * Removes from this set all of its elements that are contained in the specified collection.  If the specified collection is also a set, this operation effectively modifies this set so that its value is the asymmetric set difference of the two sets.
     *
     * @param c - collection that defines which elements will be removed from this set.
     * @return - collection that defines which elements will be removed from this set.
     * @throws ClassCastException - if the types of one or more elements in this set are incompatible with the specified collection
     * @throws NullPointerException - if this collection contains a null element
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
            if(table.containsKey(elements[i]))
            {
                out = true ;
                table.remove(elements[i]) ;
            }
        }

        return out ;
    }

    /**
     * Returns true if this set contains the specified element. More formally, returns true if and only if this set contains an element e such that (o==null ? e==null : o.equals(e)).
     *
     * @param o - element whose presence in this set is to be tested.
     * @return true if this set contains the specified element.
     * @throws NullPointerException - if the specified element is null
     * @throws ClassCastException - if the type of the specified element is incompatible with this set
     */
    @Override
    public boolean contains(Object o)
    {
        if(o == null)
            throw new NullPointerException() ;

        return table.containsKey((E)o) ;
    }

    /**
     * Returns true if this set contains all of the elements of the specified collection. If the specified collection is also a set, this method returns true if it is a subset of this set.
     *
     * @param c  - collection to be checked for containment in this set.
     * @return true if this set contains all of the elements of the specified collection.
     * @throws NullPointerException - if the specified collection contains one or more null elements or  if the specified collection is null.
     * @throws ClassCastException - if the types of one or more elements in the specified collection are incompatible with this set
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
            if(table.contains(elements[i]))
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
     * Retains only the elements in this set that are contained in the specified collection. In other words, removes from this set all of its elements that are not contained in the specified collection. If the specified collection is also a set, this operation effectively modifies this set so that its value is the intersection of the two sets.
     *
     * @param c - collection that defines which elements this set will retain.
     * @return true if this collection changed as a result of the call.
     * @throws NullPointerException - if this collection contains a null element or if the specified collection is null.
     * @throws ClassCastException - if the types of one or more elements in this set are incompatible with the specified collection
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
     * Compares the specified object with this set for equality. Returns true if the specified object is also a set, the two sets have the same size, and every member of the specified set is contained in this set (or equivalently, every member of this set is contained in the specified set). This definition ensures that the equals method works properly across different implementations of the set interface.
     *
     * @param o - Object to be compared for equality with this set.
     * @return true if the specified Object is equal to this set.
     */
    @Override
    public boolean equals(Object o)
    {
        try {
        Set<E> setAdO = (SetAdapter)o ;

        if(table.size() == setAdO.size())
        {
            E[] elements = (E[])setAdO.toArray() ;

            for(int i = 0; i < table.size() ; i++)
            {
                if(!table.contains(elements[i]))
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
     * Returns the hash code value for this set. The hash code of a set is defined to be the sum of the hash codes of the elements in the set, where the hashcode of a null element is defined to be zero. This ensures that s1.equals(s2) implies that s1.hashCode()==s2.hashCode() for any two sets s1 and s2, as required by the general contract of the Object.hashCode method
     *
     * @return the hash code value for this set
     */
    @Override
    public int hashCode()
    {
        int hashCode = 0 ;

        Iterator<E> iterator = this.iterator() ;

        while(iterator.hasNext())
        {
            hashCode += iterator.next().hashCode() ;
        }

        return hashCode ;
    }

    /**
     * Returns an iterator over the elements in this set. The elements are returned in no particular order
     *
     * @return an iterator over the elements in this set
     */
    @Override
    public Iterator<E> iterator()
    {
        return new SetAdapterIterator(table) ;
    }

    /**
     * Returns an array containing all of the elements in this set. Obeys the general contract of the Collection.toArray method.
     *
     * @return an array containing all of the elements in this set.
     */
    @Override
    public Object[] toArray()
    {
        Enumeration elements = table.keys() ;
        Object[] out = new Object[table.size()] ;

        int i = 0 ;
        while(elements.hasMoreElements())
        {
            out[i] = elements.nextElement() ;
            i++ ;
        }

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