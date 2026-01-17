package dev.oscarrojas.order_manager.web.forms;

import java.util.Map;

public record CreateOrderFormProduct(String name, String desc, int quantity, double unitPrice) {}
