package com.cc.service;



import com.cc.Vo.DataVo;
import com.cc.entity.Book;

import java.util.List;

public interface BookService {
    public DataVo findAll(Integer page, Integer limit);
    public List<Book> find();
    public DataVo ConditionalQuery(Integer page, Integer limit,String searContent);
    public void add(String name, String author, String publish, Integer pages, Float price);
    public Boolean del(Integer id);
}
