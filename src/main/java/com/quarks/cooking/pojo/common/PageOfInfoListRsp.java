package com.quarks.cooking.pojo.common;


import com.github.pagehelper.PageInfo;
import com.quarks.cooking.component.PageDataHandler;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 *
 * @author Leonardo iWzl
 */
@Data
public class PageOfInfoListRsp<T> implements HttpResponse{
    private int pageNum;
    private int pageSize;
    private int size;
    private int startRow;
    private int endRow;
    private int pages;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private long total;

    private List<T> dataList;

    public static <T,S>PageOfInfoListRsp<T> build(PageInfo<S> pageInfo, PageDataHandler<T,S> pageDataHandler){
        PageOfInfoListRsp<T> pageOfInfoListRsp = new PageOfInfoListRsp<>();
        BeanUtils.copyProperties(pageInfo, pageOfInfoListRsp);
        pageOfInfoListRsp.setDataList(pageDataHandler.hanlder(pageInfo.getList()));
        return pageOfInfoListRsp;
    }
}
