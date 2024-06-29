package org.example.springcategory.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {
    CREATED("Заказ оформлен", "orderConfirmed"),
    IN_DELIVERY("Заказ везут", "orderInDelivery"),
    DELIVERED("Доставлено", "orderInDelivered");

    private final String displayName;
    private final String serviceName;
}
