package com.quarks.cooking.component;

import java.util.ArrayList;
import java.util.List;

/**
 * PageCoverHandler
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-14 23:10
 **/
@FunctionalInterface
public interface PageDataHandler<T,S> {
    T handler(S source);

    default List<T> hanlder(List<S> sourceList){
        List<T> dataList = new ArrayList<>();
        for (S source : sourceList) {
            dataList.add(handler(source));
        }
        return dataList;
    }
}
