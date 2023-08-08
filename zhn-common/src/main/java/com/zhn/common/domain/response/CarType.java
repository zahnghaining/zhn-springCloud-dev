package com.zhn.common.domain.response;

import lombok.Data;

/**
 * @ClassName:CarType
 * @author:zhn
 * @create: 2023-08-07 09:21
 * @Description: CarType
 */
@Data
public class CarType {
    private String id;
    private String brand;
    private String model;
    private String licensePlate;
    private String registrationDate;
    private String price;
    private String createTime;
    private String updateTime;

}
