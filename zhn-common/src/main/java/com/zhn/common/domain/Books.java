package com.zhn.common.domain;

import lombok.Data;

/**
 * @ClassName:Books
 * @author:zhn
 * @create: 2023-08-03 10:20
 * @Description: 图书实体类
 */
@Data
public class Books {
    private String id;
    private String title;
    private String author;
    private String bookClassification;
    private String bookPrice;
    private String membershipPrice;
    private String chapterId;
    private String chapterTableOfContents;



}
