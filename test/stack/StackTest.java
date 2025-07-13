package stack;

import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

/**
 * Unit tests for {@link Stack}.
 */
class StackTest {

    private Stack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
    }

    @Test
    void testPushAndTop() {
        stack.push(10);
        stack.push(20);
        stack.push(30);
        assertEquals(30, stack.top());
        assertEquals(20, stack.top());
        assertEquals(10, stack.top());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testPeek() {
        stack.push(42);
        assertEquals(42, stack.peek());
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
    }

    @Test
    void testIsEmptyAndSize() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        stack.push(1);
        stack.push(2);
        assertFalse(stack.isEmpty());
        assertEquals(2, stack.size());
    }

    @Test
    void testTopThrowsExceptionWhenEmpty() {
        assertThrows(NoSuchElementException.class, stack::top);
    }

    @Test
    void testPeekThrowsExceptionWhenEmpty() {
        assertThrows(NoSuchElementException.class, stack::peek);
    }

    @Test
    void testClear() {
        stack.push(5);
        stack.push(6);
        stack.clear();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void testContains() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertTrue(stack.contains(2));
        assertFalse(stack.contains(4));
    }

    @Test
    void testToArray() {
        stack.push(100);
        stack.push(200);
        stack.push(300);
        Object[] array = stack.toArray();
        assertArrayEquals(new Object[]{300, 200, 100}, array);
    }

    @Test
    void testReverse() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.reverse();
        assertEquals(1, stack.top());
        assertEquals(2, stack.top());
        assertEquals(3, stack.top());
    }

    @Test
    void testClone() {
        stack.push(7);
        stack.push(8);
        stack.push(9);
        Stack<Integer> cloned = stack.clone();
        assertEquals(stack, cloned);
        assertNotSame(stack, cloned);
        cloned.push(10);
        assertNotEquals(stack, cloned);
    }

    @Test
    void testEqualsAndHashCode() {
        Stack<Integer> other = new Stack<>();
        stack.push(1);
        stack.push(2);
        other.push(1);
        other.push(2);
        assertEquals(stack, other);
        assertEquals(stack.hashCode(), other.hashCode());
        other.push(3);
        assertNotEquals(stack, other);
    }

    @Test
    void testPopAll() {
        stack.push(10);
        stack.push(20);
        stack.push(30);
        AtomicInteger sum = new AtomicInteger(0);
        stack.popAll(sum::addAndGet);
        assertEquals(60, sum.get());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testIterator() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        int[] expected = {3, 2, 1};
        int index = 0;
        for (int item : stack) {
            assertEquals(expected[index++], item);
        }
        assertEquals(expected.length, index);
    }
    
    @Test
    void testHashCodeWithNullElement() {
        stack.push(null);
        stack.push(42);
        int hash = stack.hashCode();
        assertDoesNotThrow(() -> {
            System.out.println("Hash code: " + hash);
        });
    }
    
    @Test
    void testContainsNullInStack() {
        stack.push(null);
        stack.push(42);
        assertTrue(stack.contains(null));
    }

    @Test
    void testContainsNullNotInStack() {
        stack.push(1);
        stack.push(2);
        assertFalse(stack.contains(null));
    }
    
    @Test
    void testContainsValueInStackWithNull() {
        stack.push(null);
        stack.push(99);
        assertTrue(stack.contains(99));
    }
    
    @Test
    void testContainsValueNotInStackWithNull() {
        stack.push(null);
        stack.push(77);
        assertFalse(stack.contains(123));
    }
    
    @Test
    void testEqualsSameInstance() {
        stack.push(1);
        stack.push(2);
        assertTrue(stack.equals(stack));
    }
    
	@Test
	@SuppressWarnings("unlikely-arg-type")
    void testEqualsWithDifferentType() {
        stack.push(1);
        assertFalse(stack.equals("not a stack"));
    }
    
    @Test
    void testEqualsDifferentSizes() {
        stack.push(1);
        Stack<Integer> other = new Stack<>();
        other.push(1);
        other.push(2);
        assertFalse(stack.equals(other));
    }
    
    @Test
    void testEqualsWithNullElementsEqual() {
        stack.push(null);
        stack.push(2);
        Stack<Integer> other = new Stack<>();
        other.push(null);
        other.push(2);
        assertTrue(stack.equals(other));
    }

    @Test
    void testEqualsWithNullElementsNotEqual() {
        stack.push(null);
        stack.push(2);
        Stack<Integer> other = new Stack<>();
        other.push(1);
        other.push(2);
        assertFalse(stack.equals(other));
    }

    @Test
    void testEqualsWithNullElementsNotEqualReversed() {
        stack.push(1);
        stack.push(2);
        Stack<Integer> other = new Stack<>();
        other.push(null);
        other.push(2);
        assertFalse(stack.equals(other));
    }
    
    @Test
    void testToStringEmptyStack() {
        assertEquals("Stack: []", stack.toString());
    }

    @Test
    void testToStringWithElements() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals("Stack: [3, 2, 1]", stack.toString());
    }

    @Test
    void testToStringWithNullElements() {
        stack.push(null);
        stack.push(5);
        assertEquals("Stack: [5, null]", stack.toString());
    }
    
    @Test
    void testIteratorNextThrowsExceptionWhenNoMoreElements() {
        stack.push(1);
        Iterator<Integer> it = stack.iterator();
        assertTrue(it.hasNext());
        assertEquals(1, it.next());
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);
    }
    
}