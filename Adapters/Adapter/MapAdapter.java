import java.util.*;

/**
 * This class implements the Map interface. The instances of this class do not contain null keys or values.
 *
 * @param <K> keys' type
 * @param <V> values' type
 */
public class MapAdapter<K,V> implements Map<K,V>
{
    private Hashtable<K,V> table = new Hashtable() ;

    /**
     * Returns the number of elements in this map. If this map contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     *
     * @return the number of elements in this map
     */
    @Override
    public int size()
    {
        if(table.size() > Integer.MAX_VALUE)
            return Integer.MAX_VALUE ;

        return table.size() ;
    }

    /**
     * Returns true if this map contains no elements.
     *
     * @return true if this map contains no elements
     */
    @Override
    public boolean isEmpty()
    {
        return table.isEmpty() ;
    }

    /**
     *  Removes all of the elements from this map. This map will be empty after this method returns.
     */
    @Override
    public void clear()
    {
        table.clear() ;
    }

    /**
     * Returns the value to which this map maps the specified key. Returns null if the map contains no mapping for this key
     *
     * @param key key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or null if the map contains no mapping for this key.
     * @throws NullPointerException - key is null and this map does not not permit null keys
     * @throws ClassCastException - if the key is of an inappropriate type for this map
     */
    @Override
    public V get(Object key)
    {
        if(key == null)
            throw new NullPointerException() ;

        if(!table.containsKey(key))
            return null ;

        return table.get(key) ;
    }

    /**
     * Associates the specified value with the specified key in this map. If the map previously contained a mapping for this key, the old value is replaced by the specified value. (A map m is said to contain a mapping for a key k if and only if m.containsKey(k) would return true.))
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return previous value associated with specified key, or null if there was no mapping for key.
     * @throws ClassCastException - if the class of the specified key or value prevents it from being stored in this map.
     * @throws NullPointerException -  the specified key or value is null.
     */
    @Override
    public V put(K key, V value)
    {
        if(key == null)
            throw new NullPointerException() ;

        if(value == null)
            throw new NullPointerException() ;

        return table.put(key,value) ;
    }

    /**
     * Returns true if this map contains a mapping for the specified key. More formally, returns true if and only if this map contains at a mapping for a key k such that (key==null ? k==null : key.equals(k)). (There can be at most one such mapping.)
     *
     * @param key key whose presence in this map is to be tested.
     * @return true if this map contains a mapping for the specified key.
     * @throws NullPointerException - if the key is null
     * @throws ClassCastException - if the key is of an inappropriate type for this map
     */
    @Override
    public boolean containsKey(Object key)
    {
        if(key == null)
            throw new NullPointerException() ;

        return table.containsKey(key) ;
    }

    /**
     * Returns true if this map maps one or more keys to the specified value. More formally, returns true if and only if this map contains at least one mapping to a value v such that (value==null ? v==null : value.equals(v)). This operation will probably require time linear in the map size for most implementations of the Map interface.
     *
     * @param value value whose presence in this map is to be tested
     * @return true if this map maps one or more keys to the specified value
     * @throws NullPointerException - if the value is null
     * @throws ClassCastException - if the value is of an inappropriate type for this map
     */
    @Override
    public boolean containsValue(Object value)
    {
        if(value == null)
            throw new NullPointerException() ;

        return table.contains(value) ;
    }

    /**
     * Returns a set view of the mappings contained in this map. Each element in the returned set is a Map.Entry. The set is backed by the map, so changes to the map are reflected in the set, and vice-versa. If the map is modified while an iteration over the set is in progress, the results of the iteration are undefined. The set supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Set.remove, removeAll, retainAll and clear operations. It does not support the add or addAll operations.
     *
     * @return a set view of the mappings contained in this map.
     */
    @Override
    public Set<Map.Entry<K,V>> entrySet()
    {
        return new EntrySet();
    }

    /**
     * Compares the specified object with this map for equality. Returns true if the given object is also a map and the two Maps represent the same mappings. More formally, two maps t1 and t2 represent the same mappings if t1.entrySet().equals(t2.entrySet()). This ensures that the equals method works properly across different implementations of the Map interface.
     *
     * @param o object to be compared for equality with this map.
     * @return true if the specified object is equal to this map.
     */
    @Override
    public boolean equals(Object o)
    {
        try {
        Map<K,V> mapO = (MapAdapter<K,V>)o ;

        return this.entrySet().equals(mapO.entrySet()) ;
        }
        catch(ClassCastException exc)
        {
            return false ;
        }

    }

    /**
     * Returns the hash code value for this map. The hash code of a map is defined to be the sum of the hashCodes of each entry in the map's entrySet view. This ensures that t1.equals(t2) implies that t1.hashCode()==t2.hashCode() for any two maps t1 and t2, as required by the general contract of Object.hashCode.
     *
     * @return hash code value for this map.
     */
    @Override
    public int hashCode()
    {
        return this.entrySet().hashCode() ;
    }

    /**
     * Returns a set view of the keys contained in this map. The set is backed by the map, so changes to the map are reflected in the set, and vice-versa. If the map is modified while an iteration over the set is in progress, the results of the iteration are undefined. The set supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Set.remove, removeAll retainAll, and clear operations. It does not support the add or addAll operations.
     *
     * @return a set view of the keys contained in this map.
     */
    @Override
    public Set<K> keySet()
    {
        return new KeySet() ;
    }

    /**
     * Copies all of the mappings from the specified map to this map (optional operation). The effect of this call is equivalent to that of calling put(k, v) on this map once for each mapping from key k to value v in the specified map. The behavior of this operation is unspecified if the specified map is modified while the operation is in progress.
     *
     * @param m Mappings to be stored in this map.
     * @throws NullPointerException - the specified map is null, or if the specified map contains null keys or values.
     * @throws ClassCastException - if the class of a key or value in the specified map prevents it from being stored in this map.
     */
    @Override
    public void putAll(Map<? extends K,? extends V> m)
    {
        if(m == null)
            throw new NullPointerException() ;

        Set<? extends Map.Entry<? extends K, ? extends V>> values = m.entrySet() ;

        Iterator<? extends Map.Entry<? extends K, ? extends V>> iterator = values.iterator() ;

        while(iterator.hasNext())
        {
            Map.Entry<? extends K, ? extends V> entry = iterator.next() ;

            this.put((K)entry.getKey(),(V)entry.getValue()) ;
        }
    }

    /**
     * Removes the mapping for this key from this map if it is present (optional operation). More formally, if this map contains a mapping from key k to value v such that (key==null ? k==null : key.equals(k)), that mapping is removed. (The map can contain at most one such mapping.)
     * Returns the value to which the map previously associated the key, or null if the map contained no mapping for this key. The map will not contain a mapping for the specified key once the call returns.
     *
     * @param key key whose mapping is to be removed from the map.
     * @return previous value associated with specified key, or null if there was no mapping for key.
     * @throws NullPointerException - if the key is null
     * @throws ClassCastException - if the key is of an inappropriate type for this map
     */
    @Override
    public V remove(Object key)
    {
        if(key == null)
            throw new NullPointerException() ;

        if(this.containsKey(key))
            return table.remove(key) ;

        return null ;
    }

    /**
     * Returns a collection view of the values contained in this map. The collection is backed by the map, so changes to the map are reflected in the collection, and vice-versa. If the map is modified while an iteration over the collection is in progress, the results of the iteration are undefined. The collection supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Collection.remove, removeAll, retainAll and clear operations. It does not support the add or addAll operations.
     *
     * @return a collection view of the values contained in this map.
     */
    @Override
    public Collection<V> values()
    {
        return new MapValues() ;
    }

    protected static class Entry<K,V> implements Map.Entry<K,V>
    {
        private K key ;
        private V value ;

        public Entry(K k, V v)
        {
            if(k == null || v == null)
                throw new NullPointerException() ;

            key = k ;
            value = v ;
        }

        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry.
         */
        @Override
        public K getKey()
        {
            return key;
        }

        /**
         * Returns the value corresponding to this entry. If the mapping has been removed from the backing map (by the iterator's remove operation), the results of this call are undefined.
         *
         * @return the value corresponding to this entry.
         */
        @Override
        public V getValue()
        {
            return value;
        }

        /**
         * Replaces the value corresponding to this entry with the specified value.  The behavior of this call is undefined if the mapping has already been removed from the map (by the iterator's remove operation).
         *
         * @param value new value to be stored in this entry.
         * @return old value corresponding to the entry.
         * @throws NullPointerException if the specified value is null.
         * @throws ClassCastException - if the class of the specified value prevents it from being stored in the backing map.
         */
        @Override
        public V setValue(V value)
        {
            V old = this.value ;

            this.value = value ;

            return old ;
        }

        /**
         * Compares the specified object with this entry for equality. Returns true if the given object is also a map entry and the two entries represent the same mapping. More formally, two entries e1 and e2 represent the same mapping if
         *      (e1.getKey()==null ?
         *       e2.getKey()==null : e1.getKey().equals(e2.getKey()))  &&
         *      (e1.getValue()==null ?
         *       e2.getValue()==null : e1.getValue().equals(e2.getValue()))
         *
         * This ensures that the equals method works properly across different implementations of the Map.Entry interface.
         *
         * @param o object to be compared for equality with this map entry.
         * @return true if the specified object is equal to this map entry.
         */
        @Override
        public boolean equals(Object o)
        {
            try{
            Map.Entry<K,V> entryO = (Map.Entry<K,V>)o ;

            if(this.key.equals(entryO.getKey()) && this.value.equals(entryO.getValue()))
                return true ;

            return false ;
            }
            catch(ClassCastException exc){
                return false ;
            }
        }

        /**
         * Returns the hash code value for this map entry. The hash code of a map entry e is defined to be:
         *      (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
         *      (e.getValue()==null ? 0 : e.getValue().hashCode())
         *
         * This ensures that e1.equals(e2) implies that e1.hashCode()==e2.hashCode() for any two Entries e1 and e2, as required by the general contract of Object.hashCode.
         *
         * @return the hash code value for this map entry.
         */
        @Override
        public int hashCode()
        {
            return (this.getKey()==null   ? 0 : this.getKey().hashCode()) ^ (this.getValue()==null ? 0 : this.getValue().hashCode()) ;
        }
    }

    public class EntrySet implements Set<Map.Entry<K, V>>
    {
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
         * Returns the hash code value for this set. The hash code of a set is defined to be the sum of the hash codes of the elements in the set, where the hashcode of a null element is defined to be zero. This ensures that s1.equals(s2) implies that s1.hashCode()==s2.hashCode() for any two sets s1 and s2, as required by the general contract of the Object.hashCode method
         *
         * @return the hash code value for this set
         */
        @Override
        public int hashCode()
        {
            int hashCode = 0 ;

            Enumeration keys = table.keys() ;

            while(keys.hasMoreElements())
            {
                K key = (K)keys.nextElement() ;

                hashCode += (new Entry(key,table.get(key))).hashCode() ;
            }

            return hashCode ;
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
            Set<Map.Entry<K,V>> setO = ((EntrySet)o) ;

            Object[] elements = setO.toArray() ;

            boolean out = true ;

            if(this.size() == setO.size())
            {
                for(int i = 0; i < this.size(); i++ )
                {
                    if (!this.contains(elements[i]))
                    {
                        out = false ;
                        break ;
                    }
                }
            }
            else
            {
                out = false ;
            }

            return out ;
            }
            catch(ClassCastException exc){
                return false ;
            }

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

            if(!(o instanceof Map.Entry))
                return false ;

            Entry<K,V> entry = (Entry<K,V>)o ;

            return (table.containsKey(entry.getKey()) && entry.getValue().equals(table.get(entry.getKey()))) ;
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

            Object[] elements = c.toArray() ;

            boolean out = true ;

            for(int i = 0; i < elements.length; i++)
            {
                Entry<K,V> entry = ((Entry<K,V>)elements[i]) ;

                if(!table.containsKey(entry.getKey()) || !entry.getValue().equals(table.get(entry.getKey())))
                {
                    out = false ;
                    break ;
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

            if(table.containsKey(((Entry<K,V>)o).getKey()) && ((Entry<K,V>)o).getValue().equals(table.get(((Entry<K,V>)o).getKey())))
            {
                table.remove(((Entry<K,V>)o).getKey()) ;

                out = true ;
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

            boolean out = false ;

            Object[] elements = c.toArray() ;

            for(int i = 0; i < elements.length; i++)
            {
                if(table.containsKey(((Entry<K,V>)elements[i]).getKey()) && ((Entry<K,V>)elements[i]).getValue().equals(table.get(((Entry<K,V>)elements[i]).getKey())))
                {
                    table.remove(((Entry<K,V>)elements[i]).getKey()) ;

                    out = true ;
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

            boolean out = false ;

            Enumeration keys = table.keys() ;

            Object[] entry = new Object[table.size()] ;

            int i = 0 ;
            while(keys.hasMoreElements())
            {
                K key = (K)keys.nextElement() ;
                entry[i] = new Entry(key,table.get(key)) ;
                i++ ;
            }

            for(int k = 0; k < entry.length; k++)
            {
                if(!c.contains(((Entry<K,V>)entry[k])))
                {
                    table.remove(((Entry<K,V>)entry[k]).getKey()) ;
                    out = true ;
                }
            }

            return out ;
        }

        /**
         * Returns an iterator over the elements in this list in proper sequence.
         *
         * @return an iterator over the elements in this list in proper sequence.
         */
        @Override
        public Iterator<Map.Entry<K,V>> iterator()
        {
            return new MapSetIterator(table) ;
        }

        /**
         * Returns an array containing all of the elements in this list in proper sequence. Obeys the general contract of the Collection.toArray method.
         *
         * @return an array containing all of the elements in this list in proper sequence.
         */
        @Override
        public Object[] toArray()
        {
            Enumeration keys = table.keys() ;
            Object[] out = new Object[table.size()] ;

            int i = 0 ;
            while(keys.hasMoreElements())
            {
                K key = (K)keys.nextElement() ;
                out[i] = new Entry<K,V>(key,table.get(key)) ;
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

        /**
         * @throws UnsupportedOperationException whenever the method is called
         */
        @Override
        public boolean add(Map.Entry<K, V> kvEntry)
        {
            throw new UnsupportedOperationException() ;
        }

        /**
         * @throws UnsupportedOperationException whenever the method is called
         */
        @Override
        public boolean addAll(Collection<? extends Map.Entry<K, V>> c)
        {
            throw new UnsupportedOperationException() ;
        }
    }

    private class KeySet implements Set<K>
    {
        /**
         * Returns the number of elements in this set. If this set contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
         *
         * @return the number of elements in this set
         */
        @Override
        public int size()
        {
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
         * Returns the hash code value for this set. The hash code of a set is defined to be the sum of the hash codes of the elements in the set, where the hashcode of a null element is defined to be zero. This ensures that s1.equals(s2) implies that s1.hashCode()==s2.hashCode() for any two sets s1 and s2, as required by the general contract of the Object.hashCode method
         *
         * @return the hash code value for this set
         */
        @Override
        public int hashCode()
        {
            int hashCode = 0 ;

            Enumeration keys = table.keys() ;

            while(keys.hasMoreElements())
            {
                hashCode += keys.nextElement().hashCode() ;
            }

            return hashCode ;
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
            boolean out = true ;

            Set<K> setO = (KeySet)o;

            if(table.size() == setO.size())
            {
                Iterator<K> iterator = setO.iterator() ;

                while(iterator.hasNext())
                {
                    if(!this.contains(iterator.next()))
                    {
                        out = false ;
                        break ;
                    }
                }
            }
            else
            {
                out = false ;
            }

            return out ;
            }
            catch(ClassCastException exc){
                return false ;
            }
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

            return table.containsKey((K)o) ;
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

            Object[] elements = c.toArray() ;

            boolean out = true ;

            for(int i = 0; i < elements.length; i++)
            {
                if(!table.containsKey(elements[i]))
                {
                    out = false ;
                    break ;
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

            if(table.containsKey((K)o))
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

            Object[] elements = c.toArray() ;

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

            boolean out = false ;

            Enumeration keys = table.keys() ;

            Object[] elements = new Object[table.size()] ;

            int i = 0 ;
            while(keys.hasMoreElements())
            {
                elements[i] = keys.nextElement() ;
                i++ ;
            }

            for(int k = 0; k < elements.length; k++)
            {
                if(!c.contains(elements[k]))
                {
                    table.remove(elements[k]) ;
                    out = true ;
                }
            }

            return out ;
        }

        /**
         * Returns an iterator over the elements in this list in proper sequence.
         *
         * @return an iterator over the elements in this list in proper sequence.
         */
        @Override
        public Iterator<K> iterator()
        {
            return new SetAdapterIterator(table) ;
        }

        /**
         * Returns an array containing all of the elements in this list in proper sequence. Obeys the general contract of the Collection.toArray method.
         *
         * @return an array containing all of the elements in this list in proper sequence.
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

        /**
         * @throws UnsupportedOperationException whenever the method is called
         */
        @Override
        public boolean add(K k)
        {
            throw new UnsupportedOperationException() ;
        }

        /**
         * @throws UnsupportedOperationException whenever the method is called
         */
        @Override
        public boolean addAll(Collection<? extends K> c)
        {
            throw new UnsupportedOperationException() ;
        }
    }

    private class MapValues implements Collection<V>
    {
        /**
         * Returns the number of elements in this collection. If this collection contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
         *
         * @return the number of elements in this collection
         */
        @Override
        public int size()
        {
            if(table.size() > Integer.MAX_VALUE)
                return Integer.MAX_VALUE ;

            return table.size() ;
        }

        /**
         * Returns true if this collection contains no elements.
         *
         * @return true if this collection contains no elements
         */
        @Override
        public boolean isEmpty()
        {
            return table.isEmpty() ;
        }

        /**
         * Removes all of the elements from this collection (optional operation). This collection will be empty after this method returns unless it throws an exception.
         */
        @Override
        public void clear()
        {
            table.clear() ;
        }

        /**
         * Returns the hash code value for this collection. While the Collection interface adds no stipulations to the general contract for the Object.hashCode method, programmers should take note that any class that overrides the Object.equals method must also override the Object.hashCode method in order to satisfy the general contract for the Object.hashCodemethod. In particular, c1.equals(c2) implies that c1.hashCode()==c2.hashCode().
         *
         * @return the hash code value for this collection
         */
        @Override
        public int hashCode()
        {
            int hashCode = 0 ;
            Iterator<V> i = this.iterator() ;

            while (i.hasNext())
            {
                Object obj = i.next() ;
                hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode()) ;
            }

            return hashCode ;
        }

        /**
         * Compares the specified object with this collection for equality.
         *
         * @param o Object to be compared for equality with this collection.
         * @return true if the specified object is equal to this collection
         */
        @Override
        public boolean equals(Object o)
        {
            try {
            boolean out = true ;

            MapValues valuesO = (MapValues)o;

            if(table.size() == valuesO.size())
            {
                Iterator<V> iterator = valuesO.iterator() ;

                while(iterator.hasNext())
                {
                    V val = iterator.next() ;

                    if(!this.contains(val))
                    {
                        out = false ;
                        break ;
                    }
                }
            }
            else
            {
                out = false ;
            }

            return out ;
            }
            catch(ClassCastException exc) {
                return false ;
            }
        }

        /**
         * Returns true if this collection contains the specified element. More formally, returns true if and only if this collection contains at least one element e such that (o==null ? e==null : o.equals(e)).
         *
         * @param o element whose presence in this collection is to be tested.
         * @return true if this collection contains the specified element
         * @throws NullPointerException - if the specified element is null
         * @throws          * ClassCastException - if the type of the specified element is incompatible with this collection
         */
        @Override
        public boolean contains(Object o)
        {
            if(o == null)
                throw new NullPointerException() ;

            return table.contains(o) ;
        }

        /**
         * Returns true if this collection contains all of the elements in the specified collection.
         *
         * @param c collection to be checked for containment in this collection.
         * @return true if this collection contains all of the elements in the specified collection
         * @throws NullPointerException - if the specified collection contains one or more null elements or if the specified collection is null
         * @throws ClassCastException - if the types of one or more elements in the specified collection are incompatible with this collection
         */
        @Override
        public boolean containsAll(Collection<?> c)
        {
            if(c == null)
                throw new NullPointerException() ;

            boolean out = true ;

            Object[] elements = c.toArray() ;

            for(int i = 0; i < elements.length; i++)
            {
                if(!this.contains(elements[i]))
                {
                    out = false ;
                    break ;
                }
            }

            return out ;
        }

        /**
         * Removes a single instance of the specified element from this collection, if it is present. More formally, removes an element e such that (o==null ? e==null : o.equals(e)), if this collection contains one or more such elements. Returns true if this collection contained the specified element (or equivalently, if this collection changed as a result of the call).
         * @param o element to be removed from this collection, if present.
         * @return true if this collection changed as a result of the call
         * @throws NullPointerException - if the specified element is null
         * @throws ClassCastException - if the type of the specified element is incompatible with this collection
         */
        @Override
        public boolean remove(Object o)
        {
            if(o == null)
                throw new NullPointerException() ;

            boolean out = false ;

            if(table.contains(o))
            {
                Enumeration keys = table.keys() ;

                while(keys.hasMoreElements())
                {
                    K key = (K)keys.nextElement();

                    if(table.get(key).equals(o))
                    {
                        table.remove(key) ;
                        out = true ;
                        break ;                 //rimuovo la prima istanza
                    }
                }
            }

            return out ;
        }

        /**
         * Removes all this collection's elements that are also contained in the specified collection. After this call returns, this collection will contain no elements in common with the specified collection.
         *
         * @param c elements to be removed from this collection.
         * @return true if this collection changed as a result of the call
         * @throws NullPointerException - if this collection contains one or more null elements or if the specified collection is null
         * @throws ClassCastException - if the types of one or more elements in this collection are incompatible with the specified collection
         */
        @Override
        public boolean removeAll(Collection<?> c)
        {
            if(c == null)
                throw new NullPointerException() ;

            boolean out = false ;

            Iterator<K> iterator = (Iterator<K>)c.iterator();

            while(iterator.hasNext())
            {
                K obj = iterator.next() ;

                if(table.contains(obj))
                 {
                    Enumeration keys = table.keys() ;

                    while(keys.hasMoreElements())
                    {
                        K key = (K)keys.nextElement();

                        if(table.get(key).equals(obj))
                        {
                            table.remove(key) ;
                            out = true ;
                            break ;
                        }
                    }
                }
            }

            return out ;
        }

        /**
         * Retains only the elements in this collection that are contained in the specified collection
         *
         * @param c elements to be retained in this collection.
         * @return true if this collection changed as a result of the call
         * @throws NullPointerException - if this collection contains one or more null elements or if the specified collection is null
         * @throws ClassCastException - if the types of one or more elements in this collection are incompatible with the specified collection
         */
        @Override
        public boolean retainAll(Collection<?> c)
        {
            if(c == null)
                throw new NullPointerException() ;

            boolean out = false ;

            Iterator<V> iterator = this.iterator() ;

            while(iterator.hasNext())
            {
                V obj = iterator.next() ;

                if(!c.contains(obj))
                {
                    iterator.remove() ;
                    out = true ;
                }
            }

            return out ;
        }

        /**
         * Returns an iterator over the elements in this list in proper sequence.
         *
         * @return an iterator over the elements in this list in proper sequence.
         */
        @Override
        public Iterator<V> iterator()
        {
            return new MapCollectionIterator(table) ;
        }

        /**
         * Returns an array containing all of the elements in this list in proper sequence. Obeys the general contract of the Collection.toArray method.
         *
         * @return an array containing all of the elements in this list in proper sequence.
         */
        @Override
        public Object[] toArray()
        {
            Iterator<V> iterator = this.iterator() ;

            Object[] out = new Object[table.size()] ;

            int i = 0 ;
            while(iterator.hasNext())
            {
                out[i] = iterator.next() ;
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

        /**
         * @throws UnsupportedOperationException whenever the method is called
         */
        @Override
        public boolean add(V v)
        {
            throw new UnsupportedOperationException() ;
        }

        /**
         * @throws UnsupportedOperationException whenever the method is called
         */
        @Override
        public boolean addAll(Collection<? extends V> c)
        {
            throw new UnsupportedOperationException() ;
        }
    }
}


























