package com.cc.Vo;


import com.cc.entity.Book;
import lombok.Data;

import java.util.List;

@Data
public class DataVo {
    //layui返回的数据类型必须有code，msg，count
    private Integer code;
    private String msg;
    private Long count;
    private List<Book> data;
}
