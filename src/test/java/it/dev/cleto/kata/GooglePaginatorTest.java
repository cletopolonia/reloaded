//package it.dev.cleto.kata;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class GooglePaginatorTest {
//
//    private static GooglePaginator googlePaginator;
//
//    @Test
//    public void getMinTest() {
//        googlePaginator = new GooglePaginator();
//        assertEquals("0", "" + googlePaginator.getMin(0, 100));
//        assertEquals("0", "" + googlePaginator.getMin(100, 0));
//        assertEquals("5", "" + googlePaginator.getMin(5, 6));
//        assertEquals("6", "" + googlePaginator.getMin(6, 6));
//        assertEquals("-6", "" + googlePaginator.getMin(-6, 6));
//    }
//
//    @Test
//    public void getMaxTest() {
//        googlePaginator = new GooglePaginator();
//        assertEquals("100", "" + googlePaginator.getMax(0, 100));
//        assertEquals("100", "" + googlePaginator.getMax(100, 0));
//        assertEquals("6", "" + googlePaginator.getMax(5, 6));
//        assertEquals("6", "" + googlePaginator.getMax(6, 6));
//        assertEquals("-2", "" + googlePaginator.getMax(-6, -2));
//    }
//
//    @Test
//    public void getMinOffsetTest() {
//        googlePaginator = new GooglePaginator();
//
//        // 23 56 --> res 17
//
//        assertEquals("1", "" + googlePaginator.getMinOffset(1, 1));
//        assertEquals("1", "" + googlePaginator.getMinOffset(1, 10));
//        assertEquals("1", "" + googlePaginator.getMinOffset(1, 11));
//        assertEquals("1", "" + googlePaginator.getMinOffset(1, 100));
//        assertEquals("1", "" + googlePaginator.getMinOffset(2, 8));
//        assertEquals("1", "" + googlePaginator.getMinOffset(6, 6));
//        assertEquals("1", "" + googlePaginator.getMinOffset(10, 11));
//        assertEquals("18", "" + googlePaginator.getMinOffset(23, 56));
//        assertEquals("42", "" + googlePaginator.getMinOffset(47, 56));
//    }
//
//    @Test
//    public void getMaxOffsetTest() {
//        googlePaginator = new GooglePaginator();
//        assertEquals("1", "" + googlePaginator.getMaxOffset(1, 1));
//        assertEquals("10", "" + googlePaginator.getMaxOffset(1, 10));
//        assertEquals("10", "" + googlePaginator.getMaxOffset(1, 11));
//        assertEquals("10", "" + googlePaginator.getMaxOffset(1, 100));
//        assertEquals("8", "" + googlePaginator.getMaxOffset(2, 8));
//        assertEquals("6", "" + googlePaginator.getMaxOffset(6, 6));
//        assertEquals("11", "" + googlePaginator.getMaxOffset(10, 11));
//        assertEquals("27", "" + googlePaginator.getMaxOffset(23, 56));
//        assertEquals("52", "" + googlePaginator.getMaxOffset(47, 56));
//    }
//
//}