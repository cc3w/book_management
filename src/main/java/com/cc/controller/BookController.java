package com.cc.controller;


import com.cc.Vo.DataVo;
import com.cc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;


    @RequestMapping("/searchHtml")
    public String searchHtml() {
        return "search";
    }


    @RequestMapping("/addHtml")
    public String addHtml() {
        return "add";
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }


    @RequestMapping("/search")
    @ResponseBody
    public DataVo find(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit){
        return bookService.findAll(page,limit);
    }

    @RequestMapping("/likequery")
    @ResponseBody
    public DataVo query(@RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "10")Integer limit, String searContent){
        return bookService.ConditionalQuery(page,limit,searContent);
    }

    @PostMapping("/addbook")
    @ResponseBody
    public String add(String name, String author, String publish, Integer pages, Float price)
    {
        bookService.add(name,author,publish,pages,price);
        return "true";
    }

    @PostMapping("/del")
    @ResponseBody
    public String del(Integer id)
    {
        if(bookService.del(id))
            return "true";
        return "";
    }


}
