import deque.ArrayDeque61B;

import deque.Deque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }


     @Test
     @DisplayName("getTest")
     public void getTest() {
         Deque61B<Integer> arrlist1 = new ArrayDeque61B<>();
         arrlist1.addFirst(4);
         arrlist1.addFirst(3);
         arrlist1.addLast(5);
         arrlist1.addLast(6);
         arrlist1.addLast(7);
         arrlist1.addLast(8);
         arrlist1.addLast(9);
         arrlist1.addLast(10);
         arrlist1.addLast(11);
         arrlist1.addFirst(12);
         assertThat(arrlist1.get(0)).isEqualTo(12);
         assertThat(arrlist1.get(1)).isEqualTo(3);
         assertThat(arrlist1.get(2)).isEqualTo(4);
         assertThat(arrlist1.get(8)).isEqualTo(10);
         assertThat(arrlist1.get(60)).isNull();
         assertThat(arrlist1.get(-2)).isNull();
     }

     @Test
     @DisplayName("isemptyandsizetest")
     public void isemptyAndSize() {
         Deque61B<Integer> arrlist1 = new ArrayDeque61B<>();
         Deque61B<Integer> arrlist2 = new ArrayDeque61B<>();
         arrlist1.addFirst(4);
         arrlist1.addFirst(3);
         arrlist1.addLast(5);
         arrlist1.addLast(6);
         assertThat(arrlist1.isEmpty()).isFalse();
         assertThat(arrlist2.isEmpty()).isTrue();
         assertThat(arrlist1.size()).isEqualTo(4);
         assertThat(arrlist2.size()).isEqualTo(0);

         arrlist1.removeFirst();
         arrlist1.removeFirst();
         arrlist1.removeLast();
         arrlist1.removeLast();
         assertThat(arrlist1.isEmpty()).isTrue();
     }

     @Test
     @DisplayName("Tolisttest")
     public void tolistTest() {
         Deque61B<Integer> arrlist1 = new ArrayDeque61B<>();
         arrlist1.addFirst(4);
         arrlist1.addFirst(3);
         arrlist1.addLast(5);
         arrlist1.addLast(6);
         arrlist1.addLast(7);
         arrlist1.addLast(8);
         arrlist1.addLast(9);
         arrlist1.addLast(10);
         arrlist1.addLast(11);
         arrlist1.addFirst(12);
         assertThat(arrlist1.toList()).containsExactly(12, 3, 4, 5, 6, 7, 8, 9, 10, 11);
     }

     @Test
     @DisplayName("removefirstandlastTest")
     public void removeFirstAndLastTest() {
         Deque61B<Integer> arrlist1 = new ArrayDeque61B<>();
         arrlist1.addFirst(4);
         arrlist1.addFirst(3);
         arrlist1.addLast(5);
         arrlist1.addLast(6);
         arrlist1.removeFirst();
         arrlist1.removeLast();
         assertThat(arrlist1.toList()).containsExactly(4, 5);
         assertThat(arrlist1.size()).isEqualTo(2);
     }

     @Test
     @DisplayName("Iterrabletest")
      public void iteratorTest() {
         Deque61B<String> arrlist1 = new ArrayDeque61B<>();
         arrlist1.addLast("hello");
         arrlist1.addLast("everyone");
         arrlist1.addLast("have");
         arrlist1.addLast("a");
         arrlist1.addLast("good");
         arrlist1.addLast("day");
         assertThat(arrlist1).containsExactly("hello", "everyone", "have", "a", "good", "day");
     }

     @Test
     @DisplayName("equalsTest")
     public void testEquals() {
         Deque61B<Integer> arrlist1 = new ArrayDeque61B<>();
         Deque61B<Integer> arrlist2 = new ArrayDeque61B<>();
         Deque61B<Integer> arrlist3 = new ArrayDeque61B<>();
         arrlist1.addFirst(4);
         arrlist1.addFirst(3);
         arrlist1.addLast(5);
         arrlist1.addLast(6);
         arrlist2.addFirst(4);
         arrlist2.addFirst(3);
         arrlist2.addLast(5);
         arrlist2.addLast(6);
         arrlist3.addFirst(7);
         arrlist3.addFirst(8);
         arrlist3.addLast(9);
         arrlist3.addLast(10);
         assertThat(arrlist1).isEqualTo(arrlist1);
         assertThat(arrlist1).isEqualTo(arrlist2);
         assertThat(arrlist1).isNotEqualTo(arrlist3);

     }

     @Test
     @DisplayName("toStringtest")
     public void testToString() {
         Deque61B<Integer> arrlist1 = new ArrayDeque61B<>();
         arrlist1.addFirst(4);
         arrlist1.addFirst(3);
         arrlist1.addLast(5);
         arrlist1.addLast(6);
         System.out.println(arrlist1);
     }
}
