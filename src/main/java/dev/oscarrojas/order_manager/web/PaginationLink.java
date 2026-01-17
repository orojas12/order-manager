package dev.oscarrojas.order_manager.web;

public record PaginationLink(String label, int pageNumber, boolean isCurrent, String href) {}
