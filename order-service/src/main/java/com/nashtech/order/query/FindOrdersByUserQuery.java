package com.nashtech.order.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FindOrdersByUserQuery {
    String userId;
}