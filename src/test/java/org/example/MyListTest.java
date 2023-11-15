package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MyListTest {
    private MyList<Integer> myList = new MyList<>();

    @Test
    void add() {
        myList = new MyList<>();
        Integer res = myList.add(1);

        assertEquals("1", res);
    }

    @Test
    void get() {
        myList = new MyList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        Integer res = myList.get(0);

        assertEquals(1, res);

    }

    @Test
    void remove() {
        myList = new MyList<>();
        myList.add(1);
        myList.add(2);
        myList.remove(0);
        int res = myList.size();
        assertEquals(1, res);
    }

    @Test
    void map() {
        myList = new MyList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        MyList<Integer> myListNew = myList.map(x -> x + 1);
        Integer res = myList.get(2);
        assertEquals(4, res);
    }

    @Test
    void equals() {
        MyList<Integer> myList1 = new MyList<>();
        myList1.add(1);
        myList1.add(2);
        MyList<Integer> myList2 = new MyList<>();
        myList2.add(3);
        myList2.add(4);
        MyList<Integer> myList3 = myList1;
        Assertions.assertTrue(myList1.equals(myList2), "в листах 1 и 2 разные значения");
        Assertions.assertFalse(myList3.equals(myList1), "Листы скопированны, одинаковые");
        Assertions.assertTrue(myList2.equals(myList3), "в листах 2 и 3 разные значения");


    }

    @Test
    void testHashCode() {
        MyList<Integer> myList1 = new MyList<>();
        myList1.add(1);
        myList1.add(2);
        MyList<Integer> myList2 = new MyList<>();
        myList2.add(3);
        myList2.add(4);
        MyList<Integer> myList3 = myList1;
        MyList<Integer> myList4 = new MyList<>();
        myList4.add(1);
        myList4.add(2);
        int res1 = myList1.hashCode();
        int res2 = myList2.hashCode();
        int res3 = myList3.hashCode();
        int res4 = myList4.hashCode();
        Assertions.assertNotEquals(res1, res2);
        Assertions.assertEquals(res1, res3);
        Assertions.assertEquals(res1, res4, "4-ый лист заполнен теми же значениями что и 1-ый");
    }
//  test hasNext on an empty collection (returns false)
//  test next() on an empty collection (throws exception)
//  test hasNext on a collection with one item (returns true, several times)
//  test hasNext/next on a collection with one item: hasNext returns true, next returns the item, hasNext returns false, twice
//  test remove on that collection: check size is 0 after
//  test remove again: exception
//  final test with a collection with several items, make sure the iterator goes through each item, in the correct order (if there is one)
//  remove all elements from the collection: collection is now empty


    @Test
    void hasNext() {
        myList = new MyList<>();
        Assertions.assertFalse(myList.iterator().hasNext());
        myList.add(1);
        myList.add(2);
        Assertions.assertTrue(myList.iterator().hasNext());
    }

    @Test
    void next() {
        myList = new MyList<>();
        Iterator<Integer> iter1 = myList.iterator();
        Integer res = iter1.next();
        Assertions.assertEquals(null, res);

        myList.add(1);
        myList.add(2);
        Iterator<Integer> iter2 = myList.iterator();
        res = iter2.next();
        res = iter2.next();
        Assertions.assertEquals(2, res);
    }

    @Test
    void testErrAdd() {
        myList = new MyList<>();

        Iterator<Integer> iter = myList.iterator();
        iter.next();
        iter.remove();

        ArrayIndexOutOfBoundsException thromn = assertThrows(
                ArrayIndexOutOfBoundsException.class,
                () -> myList.add(1), "Expected add() to throw, but it didn't"
        );


    }

    @Test
    void testRemove() {
        myList = new MyList<>();
        myList.add(1);
        Iterator<Integer> iter = myList.iterator();
        iter.next();
        iter.remove();
        int res = myList.size();
        Assertions.assertEquals(0, res);

    }

    @Test
    void iterAll() {
        myList = new MyList<>();

        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);

        Iterator<Integer> iter = myList.iterator();
        int index = 0;
        while (iter.hasNext()) {
            Integer res = iter.next();
            Assertions.assertEquals(myList.get(index++), res);
        }

    }
}
