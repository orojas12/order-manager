package dev.oscarrojas.order_manager.web;

import java.util.ArrayList;
import java.util.List;

public class Pagination {

    public static List<PaginationLink> createLinks(int currentPage, int lastPage, String pathName) {
        List<PaginationLink> paginationLinks = new ArrayList<>();

        if (currentPage < 4) {
            for (int i = 1; i < 6; i++) {
                paginationLinks.add(new PaginationLink(String.valueOf(i), i, i == currentPage, pathName + i));
            }
        } else if (currentPage > lastPage - 2) {
            for (int i = currentPage - 3; i < lastPage + 1; i++) {
                paginationLinks.add(new PaginationLink(String.valueOf(i), i, currentPage == i, pathName + i));
            }
        } else {
            for (int i = currentPage - 2; i < currentPage + 3; i++) {
                paginationLinks.add(new PaginationLink(String.valueOf(i), i, i == currentPage, pathName + i));
            }
        }

        if (currentPage > 1) {
            paginationLinks.addFirst(
                    new PaginationLink("Previous", currentPage - 1, false, pathName + (currentPage - 1)));
            paginationLinks.addFirst(new PaginationLink("First", 1, false, pathName + 1));
        }

        if (currentPage < lastPage) {
            paginationLinks.addLast(new PaginationLink("Last", lastPage, false, pathName + lastPage));
            paginationLinks.addLast(new PaginationLink("Next", currentPage + 1, false, pathName + (currentPage + 1)));
        }

        return paginationLinks;
    }
}
