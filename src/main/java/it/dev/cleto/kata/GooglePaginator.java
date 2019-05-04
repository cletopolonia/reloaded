package it.dev.cleto.kata;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GooglePaginator {

    public static final int LIMIT_DOWN = 1;
    public static final int OFFSET_DOWN = 5;
    public static final int OFFSET_UP = 4;
    public static final int LIMIT_UP = 10;

    public String paginator(Integer currentPage, Integer totalPages) {
        Integer min = getMinOffset(currentPage, totalPages);
        Integer max = getMaxOffset(currentPage, totalPages);
        return createResults(min, max);
    }

    protected Integer getMaxOffset(Integer cur, Integer tot) {
        Integer resOffset = cur + OFFSET_UP;
        Integer resLimit = tot - LIMIT_UP;
        if (resOffset < resLimit) return LIMIT_UP;
        Integer res = getMax(resOffset, resLimit);
        return res;
    }

    protected Integer getMinOffset(Integer cur, Integer tot) {
        Integer resOffset = cur - OFFSET_DOWN;
        Integer resLimit = tot - LIMIT_UP;
        Integer res = getMin(resOffset, resLimit);
        if (res < LIMIT_DOWN) return LIMIT_DOWN;
        return res;
    }


    protected Integer getMin(Integer val1, Integer val2) {
        if (val1 < val2)
            return val1;
        else return val2;
    }

    protected Integer getMax(Integer val1, Integer val2) {
        if (val1 > val2)
            return val1;
        else return val2;
    }

    protected String createResults(Integer min, Integer max) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(min);
        stringBuilder.append("..");
        stringBuilder.append(max);
        return stringBuilder.toString();
    }


}
