package com.zheye.assignment.service.impl;

import com.zheye.assignment.mapper.BookMapper;
import com.zheye.assignment.model.Book;
import com.zheye.assignment.model.BookExample;
import com.zheye.assignment.service.BookService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    public Book get(Long id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    public int add(Book book) {
        return bookMapper.insert(book);
    }

    public int update(Book book) {
        return bookMapper.updateByPrimaryKeySelective(book);
    }

    public int delete(Long id) {
        return bookMapper.deleteByPrimaryKey(id);
    }

    public List<Book> listAll() {
        return bookMapper.selectByExample(new BookExample());
    }

    @Override
    public List<Book> search(String title,
                             String author,
                             String isbn,
                             BigDecimal[] priceRange,
                             Long[] quantityRange) {

        BookExample example = new BookExample();
        BookExample.Criteria criteria = example.createCriteria();

        if (title != null) {
            criteria.andTitleEqualTo(title);
        }

        if (author != null) {
            criteria.andAuthorEqualTo(author);
        }

        if (isbn != null) {
            criteria.andIsbnEqualTo(isbn);
        }

        if (priceRange != null && priceRange.length == 2) {
            criteria.andPriceBetween(priceRange[0], priceRange[1]);
        }

        if (quantityRange != null && quantityRange.length == 2) {
            criteria.andQuantityBetween(quantityRange[0], quantityRange[1]);
        }

        return bookMapper.selectByExample(example);

    }

}
