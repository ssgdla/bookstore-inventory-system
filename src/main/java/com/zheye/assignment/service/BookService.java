package com.zheye.assignment.service;

import com.zheye.assignment.model.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {

    public Book get(Long id);

    public int add(Book book);

    public int update(Book book);

    public int delete(Long id);

    public List<Book> listAll();

    public List<Book> search(String title,
                             String author,
                             String isbn,
                             BigDecimal[] priceRange,
                             Long[] quantityRange);
}
