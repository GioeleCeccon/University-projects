import java.util.*;

/**
 * This class implements the List interface. The instances of this class do not contain null elements.
 *
 * @param <E> elements' type.
 */
public class ListAdapter<E> implements List<E>
{
    private Vector<E> vector ;

    public ListAdapter()
    {
        vector = new Vector<E>() ;
    }

    /**
     * Returns the number of elements in this list. If this list contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     *
     * @return the number of elements in this list
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
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    @Override
    public boolean isEmpty()
    {
        return vector.isEmpty() ;
    }

    /**
     *  Removes all of the elements from this list. This collection will be empty after this method returns.
     */
    @Override
    public void clear()
    {
        vector.removeAllElements() ;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index - index of element to return.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size()).
     */
    @Override
    public E get(int index)
    {
        if(index < 0 || index >= vector.size())
            throw new IndexOutOfBoundsException() ;

        return vector.elementAt(index) ;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element
     *
     * @param index - index of element to replace.
     * @param element - element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @throws NullPointerException - if the specified element is null.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size()).
     * @throws ClassCastException - if the class of the specified element prevents it from being added to this list.
     */
    @Override
    public E set(int index, E element)
    {
        if(element == null)
            throw new NullPointerException() ;

        if(index < 0 || index >= vector.size())
            throw new IndexOutOfBoundsException() ;

        E removed = vector.elementAt(index) ;

        vector.setElementAt(element,index) ;

        return removed ;
    }

    /**
     * Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element. More formally, returns the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index.
     *
     * @param o - element to search for.
     * @return the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     * @throws NullPointerException - if the specified element is null and this list does not support null elements
     * @throws ClassCastException - if the type of the specified element is incompatible with this list
     */
    @Override
    public int indexOf(Object o)
    {
        if(o == null)
            throw new NullPointerException() ;

        return vector.indexOf((E)o) ;
    }

    /**
     * Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element. More formally, returns the highest index i such that (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index.
     *
     * @param o - element to search for
     * @return the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     * @throws NullPointerException - if the specified element is null and this list does not support null elements
     * @throws ClassCastException - if the type of the specified element is incompatible with this list
     */
    @Override
    public int lastIndexOf(Object o)
    {
        if(o == null)
            throw new NullPointerException() ;

        if(vector.contains((E)o))
            return vector.lastIndexOf((E)o) ;

        return -1 ;
    }

    /**
     * Returns the hash code value for this list. The hash code of a list is defined to be the result of the following calculation:
     *   hashCode = 1;
     *   Iterator i = list.iterator();
     *   while (i.hasNext()) {
     *       Object obj = i.next();
     *       hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
     *   }
     *
     * This ensures that list1.equals(list2) implies that list1.hashCode()==list2.hashCode() for any two lists, list1 and list2, as required by the general contract of Object.hashCode.
     *
     * @return the hash code value for this list.
     */
    @Override
    public int hashCode()
    {
        int hashCode = 1;
        Iterator i = this.iterator();

        while (i.hasNext())
        {
            Object obj = i.next();
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
        }

        return hashCode ;
    }

    /**
     * Inserts the specified element at the specified position in this list. Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
     *
     * @param index - index at which the specified element is to be inserted.
     * @param element - element to be inserted.
     * @throws NullPointerException - if the specified element is null
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size()).
     * @throws ClassCastException - if the class of the specified element prevents it from being added to this list.
     */
    @Override
    public void add(int index, E element)
    {
        if(element == null)
            throw new NullPointerException() ;

        if(index < 0 || index > vector.size())
            throw new IndexOutOfBoundsException() ;

        vector.insertElementAt(element,index) ;
    }

    /**
     * Appends the specified element to the end of this list
     *
     * @param e - element to be appended to this list
     * @return true (as per the general contract of the Collection.add method).
     * @throws NullPointerException - if the specified element is null
     * @throws ClassCastException - if the class of the specified element prevents it from being added to this list.
     */
    @Override
    public boolean add(E e)
    {
        if(e == null)
            throw new NullPointerException() ;

        boolean out = false ;

        if(!vector.contains(e))
            out = true ;

        vector.addElement(e) ;

        return out ;
    }

    /**
     * Inserts all of the elements in the specified collection into this list at the specified position. Shifts the element currently at that position (if any) and any subsequent elements to the right (increases their indices). The new elements will appear in this list in the order that they are returned by the specified collection's iterator. The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress. (Note that this will occur if the specified collection is this list, and it's nonempty.)
     *
     * @param index - index at which to insert first element from the specified collection.
     * @param c - elements to be inserted into this list.
     * @return true if this list changed as a result of the call.
     * @throws NullPointerException - if the specified collection contains one or more null elements or if the specified collection is null.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size()).
     * @throws ClassCastException - if the class of one of elements of the specified collection prevents it from being added to this list.
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c)
    {
        if(c == null)
            throw new NullPointerException() ;

        if(index < 0 || index > vector.size())
            throw new IndexOutOfBoundsException() ;

        Iterator<E> iterator = (Iterator<E>)c.iterator();

        boolean out = false ;

        while(iterator.hasNext())
        {
            E added = iterator.next();

            if(!vector.contains(added))
                out = true ;

            vector.insertElementAt(added,index) ;
            index++ ;
        }

        return out ;
    }

    /**
     * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator. The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress. (Note that this will occur if the specified collection is this list, and it's nonempty.)
     *
     * @param c - collection whose elements are to be added to this list.
     * @return true if this list changed as a result of the call.
     * @throws NullPointerException - if the specified collection contains one or more null elements or if the specified collection is null.
     * @throws ClassCastException - if the class of an element in the specified collection prevents it from being added to this list.
     */
    @Override
    public boolean addAll(Collection<? extends E> c)
    {
        if(c == null)
            throw new NullPointerException() ;

        Iterator<E> iterator = (Iterator<E>) c.iterator();
        boolean out = false ;

        while(iterator.hasNext())
        {
            E added = iterator.next();

            if(!vector.contains(added))
                out = true ;

            vector.addElement(added) ;
        }

        return out ;
    }

    /**
     * Removes the element at the specified position in this list. Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
     *
     * @param index - the index of the element to removed.
     * @return the element previously at the specified position.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size()).
     */
    @Override
    public E remove(int index)
    {
        if(index < 0 || index >= vector.size())
            throw new IndexOutOfBoundsException() ;

        E removed = vector.elementAt(index) ;

        vector.removeElementAt(index) ;

        return removed ;
    }

    /**
     * Removes the first occurrence in this list of the specified element. If this list does not contain the element, it is unchanged. More formally, removes the element with the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists).
     *
     * @return true if this list contained the specified element.
     * @throws NullPointerException - if the specified element is null
     * @throws ClassCastException - if the type of the specified element is incompatible with this list
     */
    @Override
    public boolean remove(Object o)
    {
        if(o == null)
            throw new NullPointerException() ;

        return vector.removeElement((E)o) ;
    }

    /**
     * Removes from this list all the elements that are contained in the specified collection.
     *
     * @param c - collection that defines which elements will be removed from this list.
     * @return true if this list changed as a result of the call.
     * @throws NullPointerException - if this list contains one or more null elements or if the specified collection is null.
     * @throws ClassCastException - if the types of one or more elements in this list are incompatible with the specified collection
     */
    @Override
    public boolean removeAll(Collection<?> c)
    {
        if(c == null)
            throw new NullPointerException() ;

        boolean out = false ;

        Iterator<E> iterator = (Iterator<E>)c.iterator() ;

        while(iterator.hasNext())
        {
            E obj = iterator.next() ;

            if(vector.contains(obj))
            {
                while(vector.indexOf(obj) != -1)
                {
                    vector.removeElementAt(vector.indexOf(obj));
                }

                out = true ;
            }
        }

        return out ;
    }

    /**
     * Returns true if this list contains the specified element. More formally, returns true if and only if this list contains at least one element e such that (o==null ? e==null : o.equals(e)).
     *
     * @param o - element whose presence in this list is to be tested.
     * @return true if this list contains the specified element.
     * @throws NullPointerException - if the specified element is null
     * @throws ClassCastException - if the type of the specified element is incompatible with this list
     */
    @Override
    public boolean contains(Object o)
    {
        if(o == null)
            throw new NullPointerException() ;

        return vector.contains((E)o) ;
    }

    /**
     * Returns true if this list contains all of the elements of the specified collection.
     *
     * @param c - collection to be checked for containment in this list.
     * @return true if this list contains all of the elements of the specified collection.
     * @throws NullPointerException - if the specified collection contains one or more null elements or if the specified collection is null.
     * @throws ClassCastException - if the types of one or more elements in the specified collection are incompatible with this list
     */
    @Override
    public boolean containsAll(Collection<?> c)
    {
        if(c == null)
            throw new NullPointerException() ;

        E[] elements = (E[])c.toArray() ;
        boolean out = true ;

        for(int i = 0; i < elements.length; i++)
        {
            if(!vector.contains(elements[i]))
                out = false ;
        }

        return out ;
    }

    /**
     * Retains only the elements in this list that are contained in the specified collection.
     *
     * @param c  - collection that defines which elements this set will retain.
     * @return true if this list changed as a result of the call.
     * @throws NullPointerException - if this list contains one or more null elements or if the specified collection is null.
     * @throws ClassCastException - if the types of one or more elements in this list are incompatible with the specified collection
     */
    @Override
    public boolean retainAll(Collection<?> c)
    {
        if(c == null)
            throw new NullPointerException() ;

        E[] elements = (E[])this.toArray() ;

        boolean out = false ;

        for(int i = 0; i < elements.length; i++)
        {
            E obj = elements[i] ;

            if(!c.contains(obj))
            {
                while(this.contains(obj))
                {
                    this.remove(obj) ;
                }

                out = true ;
            }
        }

        return out ;
    }

    /**
     * Compares the specified object with this list for equality. Returns true if and only if the specified object is also a list, both lists have the same size, and all corresponding pairs of elements in the two lists are equal. (Two elements e1 and e2 are equal if (e1==null ? e2==null : e1.equals(e2)).) In other words, two lists are defined to be equal if they contain the same elements in the same order. This definition ensures that the equals method works properly across different implementations of the List interface.
     *
     * @param o - the object to be compared for equality with this list.
     * @return true if the specified object is equal to this list.
     */
    @Override
    public boolean equals(Object o)
    {
        try {
        boolean out = true ;

        List<E> liAdO = (ListAdapter<E>)o ;

        if(liAdO.size() != vector.size())
        {
            out = false ;
            return out ;
        }

        E[] elements = (E[]) liAdO.toArray();

        for(int i = 0; i < elements.length; i++)
        {
            if(!elements[i].equals(vector.elementAt(i)))
                out = false ;
        }

        return out ;
        }
        catch(ClassCastException exc){
            return false ;
        }

    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence.
     */
    @Override
    public java.util.Iterator<E> iterator()
    {
        return new ListAdapterIterator(0, vector.size(), vector) ;
    }

    /**
     * Returns a list iterator of the elements in this list (in proper sequence).
     *
     * @return a list iterator of the elements in this list (in proper sequence).
     */
    @Override
    public ListIterator<E> listIterator()
    {
        return new ListAdapterListIterator(0, vector.size(), vector) ;
    }

    /**
     * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list. The specified index indicates the first element that would be returned by an initial call to the next method. An initial call to the previous method would return the element with the specified index minus one.
     *
     * @param index - index of first element to be returned from the list iterator (by a call to the next method).
     * @return a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size()).
     */
    @Override
    public ListIterator<E> listIterator(int index)
    {
        if(index < 0 || index >= vector.size())
            throw new IndexOutOfBoundsException() ;

        return new ListAdapterListIterator(index, vector.size(), vector) ;
    }

    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive. (If fromIndex and toIndex are equal, the returned list is empty.) The returned list is backed by this list, so non-structural changes in the returned list are reflected in this list, and vice-versa. The returned list supports all of the optional list operations supported by this list.
     * This method eliminates the need for explicit range operations (of the sort that commonly exist for arrays). Any operation (except for sublist()) that expects a list can be used as a range operation by passing a subList view instead of a whole list. For example, the following idiom removes a range of elements from a list:
     *
     * 	    list.subList(from, to).clear();
     *
     * Similar idioms may be constructed for indexOf and lastIndexOf, and all of the algorithms in the Collections class can be applied to a subList.
     * The semantics of the list returned by this method become undefined if the backing list (i.e., this list) is structurally modified in any way other than via the returned list. (Structural modifications are those that change the size of this list, or otherwise perturb it in such a fashion that iterations in progress may yield incorrect results.)
     *
     * @param fromIndex - low endpoint (inclusive) of the subList.
     * @param toIndex - high endpoint (exclusive) of the subList.
     * @return a view of the specified range within this list.
     * @throws IndexOutOfBoundsException - for an illegal endpoint index value (fromIndex < 0 || toIndex > size || fromIndex > toIndex).
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex)
    {
        return new SubList(fromIndex,toIndex) ;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence. Obeys the general contract of the Collection.toArray method.
     *
     * @return an array containing all of the elements in this list in proper sequence.
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

    private class SubList implements List<E>
    {
        private int from ;
        private int to ;

        public SubList(int fromIndex, int toIndex)
        {
            if(fromIndex < 0 || toIndex > vector.size() || fromIndex > toIndex)
                throw new IndexOutOfBoundsException() ;

            from = fromIndex ;
            to = toIndex ;
        }

        /**
         * Returns the number of elements in this list. If this list contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
         *
         * @return the number of elements in this list
         */
        @Override
        public int size()
        {
            return to - from ;
        }

        /**
         * Returns true if this list contains no elements.
         *
         * @return true if this list contains no elements
         */
        @Override
        public boolean isEmpty()
        {
            return to == from ;
        }

        /**
         *  Removes all of the elements from this list. This collection will be empty after this method returns.
         */
        @Override
        public void clear()
        {
            for(int i = to - 1; i >= from; i--)
            {
                vector.removeElementAt(i) ;
                to-- ;
            }
        }

        /**
         * Returns the element at the specified position in this list.
         *
         * @param index - index of element to return.
         * @return the element at the specified position in this list.
         * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size()).
         */
        @Override
        public E get(int index)
        {
            if(index < 0 || index >= this.size())
                throw new IndexOutOfBoundsException() ;

            return vector.elementAt(index + from) ;
        }

        /**
         * Replaces the element at the specified position in this list with the specified element
         *
         * @param index - index of element to replace.
         * @param element - element to be stored at the specified position.
         * @return the element previously at the specified position.
         * @throws NullPointerException - if the specified element is null
         * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size()).
         * @throws ClassCastException - if the class of the specified element prevents it from being added to this list.
         */
        @Override
        public E set(int index, E element)
        {
            if(index < 0 || index >= this.size())
                throw new IndexOutOfBoundsException() ;

            if(element == null)
                throw new NullPointerException() ;

            E removed = vector.elementAt(index + from) ;

            vector.setElementAt(element,index + from) ;

            return removed ;
        }

        /**
         * Returns the hash code value for this list. The hash code of a list is defined to be the result of the following calculation:
         *   hashCode = 1;
         *   Iterator i = list.iterator();
         *   while (i.hasNext()) {
         *       Object obj = i.next();
         *       hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
         *   }
         *
         * This ensures that list1.equals(list2) implies that list1.hashCode()==list2.hashCode() for any two lists, list1 and list2, as required by the general contract of Object.hashCode.
         *
         * @return the hash code value for this list.
         */
        @Override
        public int hashCode()
        {
            int hashCode = 1;
            Iterator i = this.iterator();

            while (i.hasNext())
            {
                Object obj = i.next();
                hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
            }

            return hashCode ;
        }

        /**
         * Returns true if this list contains the specified element. More formally, returns true if and only if this list contains at least one element e such that (o==null ? e==null : o.equals(e)).
         *
         * @param o - element whose presence in this list is to be tested.
         * @return true if this list contains the specified element.
         * @throws NullPointerException - if the specified element is null
         * @throws ClassCastException - if the type of the specified element is incompatible with this list
         */
        @Override
        public boolean contains(Object o)
        {
            if(o == null)
                throw new NullPointerException() ;

            boolean out = false ;

            for(int i = from; i < to; i++)
            {
                if(vector.elementAt(i).equals((E)o))
                    out = true ;
            }

            return out ;
        }

        /**
         * Appends the specified element to the end of this list
         *
         * @param e - element to be appended to this list
         * @return true (as per the general contract of the Collection.add method).
         * @throws NullPointerException - if the specified element is null
         * @throws ClassCastException - if the class of the specified element prevents it from being added to this list.
         */
        @Override
        public boolean add(E e)
        {
            if(e == null)
                throw new NullPointerException() ;

            boolean out = true ;

            for(int i = from; i < to; i++)
            {
                if(vector.elementAt(i).equals(e))
                    out = false ;
            }

            vector.insertElementAt(e,to) ;
            to++ ;

            return out ;
        }

        /**
         * Inserts the specified element at the specified position in this list. Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
         *
         * @param index - index at which the specified element is to be inserted.
         * @param element - element to be inserted.
         * @throws NullPointerException - if the specified element is null
         * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size()).
         * @throws ClassCastException - if the class of the specified element prevents it from being added to this list.
         */
        @Override
        public void add(int index, E element)
        {
            if(index < 0 || index > this.size())
                throw new IndexOutOfBoundsException() ;

            if(element == null)
                throw new NullPointerException() ;

            vector.insertElementAt(element, from + index);
            to++ ;
        }

        /**
         * Removes the first occurrence in this list of the specified element. If this list does not contain the element, it is unchanged. More formally, removes the element with the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists).
         *
         * @return true if this list contained the specified element.
         * @throws NullPointerException - if the specified element is null
         * @throws ClassCastException - if the type of the specified element is incompatible with this list
         */
        @Override
        public boolean remove(Object o)
        {
            if(o == null)
                throw new NullPointerException() ;

            boolean out = false ;

            for(int i = from; i < to; i++)
            {
                if(vector.elementAt(i).equals((E)o))
                {
                    vector.removeElementAt(i) ;
                    to-- ;
                    out = true ;
                    break ;
                }
            }

            return out ;
        }

        /**
         * Removes the element at the specified position in this list. Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
         *
         * @param index - the index of the element to removed.
         * @return the element previously at the specified position.
         * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size()).
         */
        @Override
        public E remove(int index)
        {
            if(index < 0 || index >= this.size())
                throw new IndexOutOfBoundsException() ;

            E removed = vector.elementAt(index + from) ;

            vector.removeElementAt(index + from) ;
            to-- ;

            return removed ;
        }

        /**
         * Returns true if this list contains all of the elements of the specified collection.
         *
         * @param c - collection to be checked for containment in this list.
         * @return true if this list contains all of the elements of the specified collection.
         * @throws NullPointerException - if the specified collection contains one or more null elements or if the specified collection is null.
         * @throws ClassCastException - if the types of one or more elements in the specified collection are incompatible with this list
         */
        @Override
        public boolean containsAll(Collection<?> c)
        {
            if(c == null)
                throw new NullPointerException() ;

            boolean out = true ;

            Iterator<E> iterator = (Iterator<E>)c.iterator();

            while(iterator.hasNext())
            {
                E obj = iterator.next() ;
                boolean isPresent = false ;

                for(int i = from; i < to; i++)
                {
                    if(vector.elementAt(i).equals(obj))
                        isPresent = true ;
                }

                if(!isPresent)
                {
                    out = isPresent ;
                    return out ;
                }
            }

            return out ;
        }

        /**
         * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator. The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress. (Note that this will occur if the specified collection is this list, and it's nonempty.)
         *
         * @param c - collection whose elements are to be added to this list.
         * @return true if this list changed as a result of the call.
         * @throws NullPointerException - if the specified collection contains one or more null elements or if the specified collection is null.
         * @throws ClassCastException - if the class of an element in the specified collection prevents it from being added to this list.
         */
        @Override
        public boolean addAll(Collection<? extends E> c)
        {
            if(c == null)
                throw new NullPointerException() ;

            E[] elements = (E[])c.toArray() ;

            boolean out = false ;

            for(int j = 0; j < elements.length; j++)
            {
                if(!this.contains(elements[j]))
                    out = true ;

                vector.insertElementAt(elements[j],to) ;
                to++ ;
            }

            return out ;
        }

        /**
         * Inserts all of the elements in the specified collection into this list at the specified position. Shifts the element currently at that position (if any) and any subsequent elements to the right (increases their indices). The new elements will appear in this list in the order that they are returned by the specified collection's iterator. The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress. (Note that this will occur if the specified collection is this list, and it's nonempty.)
         *
         * @param index - index at which to insert first element from the specified collection.
         * @param c - elements to be inserted into this list.
         * @return true if this list changed as a result of the call.
         * @throws NullPointerException - if the specified collection contains one or more null elements or if the specified collection is null.
         * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size()).
         * @throws ClassCastException - if the class of one of elements of the specified collection prevents it from being added to this list.
         */
        @Override
        public boolean addAll(int index, Collection<? extends E> c)
        {
            if(c == null)
                throw new NullPointerException() ;

            if(index < 0 || index > this.size())
                throw new IndexOutOfBoundsException() ;

            E[] elements = (E[])c.toArray() ;

            boolean out = false ;

            for(int j = 0; j < elements.length; j++)
            {
                if(!this.contains(elements[j]))
                    out = true ;

                vector.insertElementAt(elements[j],index) ;
                to++ ;
            }

            return out ;
        }

        /**
         * Removes from this list all the elements that are contained in the specified collection.
         *
         * @param c - collection that defines which elements will be removed from this list.
         * @return true if this list changed as a result of the call.
         * @throws NullPointerException - if this list contains one or more null elements or if the specified collection is null.
         * @throws ClassCastException - if the types of one or more elements in this list are incompatible with the specified collection
         */
        @Override
        public boolean removeAll(Collection<?> c)
        {
            if(c == null)
                throw new NullPointerException() ;

            boolean out = false ;

            Iterator<E> iterator = this.iterator() ;

            while(iterator.hasNext())
            {
                if(c.contains(iterator.next()))
                {
                    iterator.remove() ;
                    out = true ;
                }
            }

            return out ;
        }

        /**
         * Retains only the elements in this list that are contained in the specified collection.
         *
         * @param c  - collection that defines which elements this set will retain.
         * @return true if this list changed as a result of the call.
         * @throws NullPointerException - if this list contains one or more null elements or if the specified collection is null.
         * @throws ClassCastException - if the types of one or more elements in this list are incompatible with the specified collection
         */
        @Override
        public boolean retainAll(Collection<?> c)
        {
            if(c == null)
                throw new NullPointerException() ;

            boolean out = false ;

            Iterator<E> iterator = this.iterator() ;

            while(iterator.hasNext())
            {
                if(!c.contains(iterator.next()))
                {
                    iterator.remove() ;
                    out = true ;
                }
            }

            return out ;
        }

        /**
         * Compares the specified object with this list for equality. Returns true if and only if the specified object is also a list, both lists have the same size, and all corresponding pairs of elements in the two lists are equal. (Two elements e1 and e2 are equal if (e1==null ? e2==null : e1.equals(e2)).) In other words, two lists are defined to be equal if they contain the same elements in the same order. This definition ensures that the equals method works properly across different implementations of the List interface.
         *
         * @param o - the object to be compared for equality with this list.
         * @return true if the specified object is equal to this list.
         */
        @Override
        public boolean equals(Object o)
        {
            try {
            List<E> subListO = (SubList)o ;

            boolean out = true ;

            if(subListO.size() != this.size())
            {
                out = false ;
                return out ;
            }

            Iterator<E> iteratorO = subListO.iterator() ;
            Iterator<E> iteratorThis = this.iterator() ;

            while(iteratorThis.hasNext())
            {
                E obj1 = iteratorThis.next() ;
                E obj2 = iteratorO.next() ;

                if(!obj1.equals(obj2))
                    out = false ;
            }

            return out ;
            }
            catch(ClassCastException exc){
                return false ;
            }
        }

        /**
         * Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element. More formally, returns the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index.
         *
         * @param o - element to search for.
         * @return the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
         * @throws NullPointerException - if the specified element is null and this list does not support null elements
         * @throws ClassCastException - if the type of the specified element is incompatible with this list
         */
        @Override
        public int indexOf(Object o)
        {
            if(o == null)
                throw new NullPointerException() ;

            for(int i = from; i < to; i++)
            {
                if(vector.elementAt(i).equals((E)o))
                    return i -from ;
            }

            return -1 ;
        }

        /**
         * Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element. More formally, returns the highest index i such that (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index.
         *
         * @param o - element to search for
         * @return the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
         * @throws NullPointerException - if the specified element is null and this list does not support null elements
         * @throws ClassCastException - if the type of the specified element is incompatible with this list
         */
        @Override
        public int lastIndexOf(Object o)
        {
            if(o == null)
                throw new NullPointerException() ;

            for(int i = to - 1; i >= from; i--)
            {
                if(vector.elementAt(i).equals((E)o))
                    return i - from ;
            }

            return -1 ;
        }

        /**
         * Returns an iterator over the elements in this list in proper sequence.
         *
         * @return an iterator over the elements in this list in proper sequence.
         */
        @Override
        public Iterator<E> iterator()
        {
            return new ListAdapterIterator(from, to, this) ;
        }

        /**
         * Returns a list iterator of the elements in this list (in proper sequence).
         *
         * @return a list iterator of the elements in this list (in proper sequence).
         */
        @Override
        public ListIterator<E> listIterator()
        {
            return new ListAdapterListIterator(from, to, this) ;
        }

        /**
         * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list. The specified index indicates the first element that would be returned by an initial call to the next method. An initial call to the previous method would return the element with the specified index minus one.
         *
         * @param index - index of first element to be returned from the list iterator (by a call to the next method).
         * @return a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
         * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size()).
         */
        @Override
        public ListIterator<E> listIterator(int index)
        {
            if(index < from || index >= to)
                throw new IndexOutOfBoundsException() ;

            return new ListAdapterListIterator(index, to, this) ;
        }

        /**
         * @throws UnsupportedOperationException whenever this method is called.
         */
        @Override
        public List<E> subList(int fromIndex, int toIndex)
        {
            throw new UnsupportedOperationException() ;
        }

        /**
         * Returns an array containing all of the elements in this list in proper sequence. Obeys the general contract of the Collection.toArray method.
         *
         * @return an array containing all of the elements in this list in proper sequence.
         */
        @Override
        public Object[] toArray()
        {
            Object[] out = new Object[to - from] ;

            Iterator<E> iterator = this.iterator() ;

            int i = 0 ;
            while(iterator.hasNext())
            {
                out[i] = iterator.next() ;
                i++ ;
             }

            return out ;
        }

        /**
         * @throws UnsupportedOperationException whenever this method is called
         */
        @Override
        public <T> T[] toArray(T[] a)
        {
            throw new UnsupportedOperationException() ;
        }
    }

}