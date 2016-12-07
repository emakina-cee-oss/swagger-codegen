package io.swagger.codegen.utils;

import io.swagger.codegen.CodegenParameter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel.Hoeggerl on 06.12.2016.
 */
public class ParamComparator implements Comparator<CodegenParameter> {

    @Override
    public int compare(CodegenParameter o1, CodegenParameter o2) {
        Integer so1 = getSortOrder(o1);
        Integer so2 = getSortOrder(o2);
        int ret = so1.compareTo(so2);
        if (ret == 0) {
            o1.hasMore = o1.hasMore == null ? false : o1.hasMore;
            o2.hasMore = o2.hasMore == null ? false : o2.hasMore;
            if (o1.hasMore && !o2.hasMore) {
                ret = -1;
            }
            if (o2.hasMore && !o1.hasMore) {
                ret = 1;
            }
        }
        return ret;
    }

    private Integer getSortOrder(CodegenParameter cp) {
        if (cp.isPathParam != null && cp.isPathParam) {
            return Integer.valueOf(1);
        }
        if (cp.isQueryParam != null && cp.isQueryParam) {
            return Integer.valueOf(2);
        }
        if (cp.isBodyParam != null && cp.isBodyParam) {
            return Integer.valueOf(3);
        }
        if (cp.isHeaderParam != null && cp.isHeaderParam) {
            return Integer.valueOf(4);
        }
        if (cp.isFormParam != null && cp.isFormParam) {
            return Integer.valueOf(5);
        }
        return Integer.valueOf(6);
    }
}
