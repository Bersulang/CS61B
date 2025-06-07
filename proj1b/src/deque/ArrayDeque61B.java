package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private int nextfirst;
    private int nextlast;
    private int size;
    private T[] sentinel;

    public ArrayDeque61B(){
        sentinel = (T[]) new Object[8];
        nextfirst = 4;  //假设是下一次插入的开头的位置
        nextlast = 5;   //假设是下一次插入的末尾的位置
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        if (size == sentinel.length) {
            resizelarge(sentinel.length * 2);
        }
        nextfirst = Math.floorMod(nextfirst, sentinel.length);
        sentinel[nextfirst] = x;
        nextfirst--;
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == sentinel.length) {
            resizelarge(sentinel.length * 2);
        }
        nextlast = Math.floorMod(nextlast, sentinel.length);
        sentinel[nextlast] = x;
        nextlast++;
        size++;
    }

/*     实现上似乎还是有些混乱，之后再来研究
        public void resizelarge(int newsize) {
        T[] newArr = (T[]) new Object[newsize];
        int copystart = Math.floorMod(nextfirst, sentinel.length);
        for (int i = 0; i < size; i++) {
            newArr[copystart] = get(i);
            copystart++;
        }
        sentinel = newArr;
        nextfirst = Math.floorMod(nextfirst - 1,sentinel.length);
        nextlast = copystart;
    }*/

    //替代方案，逻辑结构复制
    public void resizelarge(int newsize) {
        T[] newArr = (T[]) new Object[newsize];
        for (int i = 0; i < size; i++) {
            newArr[i] = get(i);  // 逻辑顺序插入
        }
        sentinel = newArr;
        nextfirst = newsize - 1;
        nextlast = size;
    }


    public void resizesmall() {
        int newCapacity = Math.max(16, sentinel.length / 2); // 16及以下则无需改动大小
        T[] newArr = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = get(i);
        }
        sentinel = newArr;
        nextfirst = newCapacity - 1;
        nextlast = size;
    }


    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<T>();
        int rest = size;
        int start = 0;
        while (rest > 0) {
            returnList.add(get(start));
            rest--;
            start++;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        nextfirst = Math.floorMod(nextfirst + 1, sentinel.length);
        T returnnum = sentinel[nextfirst];
        sentinel[nextfirst] = null;
        size--;

        if (sentinel.length >= 16 && (double) size / sentinel.length < 0.25) {
            resizesmall();
        }

        return returnnum;
    }


    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        nextlast = Math.floorMod(nextlast - 1, sentinel.length);
        T returnnum = sentinel[nextlast];
        sentinel[nextlast] = null;
        size--;

        if (sentinel.length >= 16 && (double) size / sentinel.length < 0.25) {
            resizesmall();
        }

        return returnnum;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int getindex = Math.floorMod(index + nextfirst + 1,sentinel.length);
        return sentinel[getindex];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public Iterator<T> iterator() {
        return new internalIterator();
    }

    private class internalIterator implements Iterator<T> {
        private int wizPos;

        public internalIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T item = get(wizPos);
            wizPos++;
            return item;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {return true;}
        if (other == null) {return false;}
        if (other instanceof ArrayDeque61B otherobject) {
            if (this.size != otherobject.size()) {
                return false;
            }
            for (int i = 0; i < this.size; i++) {
                if (this.get(i) != otherobject.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.toList().toString();
    }

}
