package com.zhn.common.domain.request;

import lombok.Data;

/**
 * @ClassName:ReqBooks
 * @author:zhn
 * @create: 2023-08-06 10:47
 * @Description: ReqBooks
 */
@Data
public class ReqBooks {
    private String id;
    private String title;
    private String author;
    private String bookClassification;
    private String bookPrice;
    private String membershipPrice;
    private String chapterId;
    private String chapterTableOfContents;
}
