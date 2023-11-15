package org.example;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

/** Описать разницу между статическим InnerClass и обычным InnerClass
 *  Вложенные классы делятся на две категории: статические и нестатические.
 *  Вложенные классы, которые объявлены статическими, называются просто
 *  статическими вложенными классами.
 *  Нестатические вложенные классы называются внутренними классами.
 *
 *  Доступ к статическим вложенным классам осуществляется с использованием имени внешнего класса
 *  OuterClass.StaticNestedClass
 *
 *  Чтобы создать экземпляр внутреннего класса, необходимо сначала создать экземпляр внешнего класса.
 *  Затем создайте внутренний объект внутри внешнего объекта
 *  class OuterClass {
 *     ...
 *     class InnerClass {
 *         ...
 *     }
 *  }
 * OuterClass outerObject = new OuterClass()
 * OuterClass.InnerClass innerObject = outerObject.new InnerClass();
 *
 */

public class MyList<T extends Number> implements Iterable<T> {
  protected int modCount = 0;
 T[] elementData;
  private int size;
  private static final int DEFAULT_CAPACITY = 10;
    private final int CUT_RATE = 4;


  public MyList(){
      this.elementData = (T[]) new Number[DEFAULT_CAPACITY];
  }
  public T add(T t) {
    if(size == elementData.length-1)
      resize(elementData.length*2); // увеличу в 2 раза, если достигли границ
    elementData[size++] = t;
    return t;

  }

  public T get(int index) {
      return (T) elementData[index];
  }

  private void resize(int newSize) {
    T[] newArray = (T[]) new Number[newSize];
    System.arraycopy(elementData, 0, newArray, 0, size);
    elementData = newArray;
  }

  public T remove(int index) {
      T removeElement = (T) elementData[index];
      for (int i = index; i< size; i++)
          elementData[i] = elementData[i+1];
      elementData[size] = null;
      size--;
      if (elementData.length > DEFAULT_CAPACITY && size < elementData.length / CUT_RATE)
          resize(elementData.length/2);
      // если элементов в CUT_RATE раз меньше чем
      // длина массива, то уменьшу в два раза
      return removeElement;
  }

  public <F extends Number> MyList map(Function<T, F> f) {
    //  list.stream().mapToInt(f).count();
      MyList<F> mappedList = new MyList<>();
      for (int i = 0; i < size; i++) {
          mappedList.add(f.apply((T) elementData[i]));
      }

      return mappedList;
  }

  public int size() {
    return size;
  }

    @Override
    public String toString() {
        return "MyList{" +
                "elementData=" + Arrays.toString(elementData) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyList<?> myList = (MyList<?>) o;
        return size == myList.size && Arrays.equals(elementData, myList.elementData);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(elementData);
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }
    public class MyIterator implements Iterator<T> {
        int index = 0;

        public MyIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public T next() {
            return elementData[index++];
        }

        @Override
        public void remove() {
           MyList.this.remove(index);
        }
    }
}
