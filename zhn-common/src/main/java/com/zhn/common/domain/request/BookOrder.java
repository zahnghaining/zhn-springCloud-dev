package com.zhn.common.domain.request;

import lombok.Data;

/**
 * @ClassName:BookOrder
 * @author:zhn
 * @create: 2023-08-03 13:36
 * @Description: 书籍订单列表
 */
@Data
public class BookOrder {
    private String id;
    private String uid;
    private String title;
    private String author;
    private String bookClassification;
    private String bookPrice;


}
