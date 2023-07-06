package com.cc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Book {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String author;

    private String publish;

    private Integer pages;

    private Float price;
}
