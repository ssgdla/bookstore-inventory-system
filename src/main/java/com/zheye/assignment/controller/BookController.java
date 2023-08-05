package com.zheye.assignment.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zheye.assignment.constant.BusinessException;
import com.zheye.assignment.constant.InvalidArgumentException;
import com.zheye.assignment.dto.AddBookRequest;
import com.zheye.assignment.dto.UpdateBookRequest;
import com.zheye.assignment.model.Book;
import com.zheye.assignment.service.BookService;
import com.zheye.assignment.util.PreconditionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import static com.zheye.assignment.constant.RequestConstant.*;
import static com.zheye.assignment.constant.ResultCode.BOOK_NOT_FOUND;
import static com.zheye.assignment.constant.RequestConstant.PATH_PREFIX_V1;

@RestController
@ResponseBody
@RequestMapping(PATH_PREFIX_V1)
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book/{id}")
    public Book detail(@PathVariable Long id) {
        Book book = bookService.get(id);
        if (book == null) {
            throw new BusinessException(BOOK_NOT_FOUND);
        }
        return book;
    }

    @PostMapping("/book")
    public void add(@RequestBody AddBookRequest addBookRequest) {

        String title = addBookRequest.getTitle();
        PreconditionUtil.checkArgument(title == null || title.length() <= TITLE_MAX_LENGTH,
                new InvalidArgumentException("Title is too long"));

        String author = addBookRequest.getAuthor();
        PreconditionUtil.checkArgument(author == null || author.length() <= AUTHOR_MAX_LENGTH,
                new InvalidArgumentException("Author name is too long"));

        String isbn = addBookRequest.getIsbn();
        PreconditionUtil.checkArgument(isbn == null || isbn.length() == ISBN_LENGTH,
                new InvalidArgumentException("Invalid ISBN"));

        BigDecimal price;
        try {
            price = new BigDecimal(addBookRequest.getPrice());
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("price should be a numeric value");
        }
        PreconditionUtil.checkArgument(price.compareTo(BigDecimal.ZERO) >= 0,
                new InvalidArgumentException("Price should be non-negative"));

        Long quantity = addBookRequest.getQuantity();
        PreconditionUtil.checkArgument(addBookRequest.getQuantity() >= 0,
                new InvalidArgumentException("Quantity should be non-negative"));

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setPrice(price);
        book.setQuantity(quantity);

        bookService.add(book);
    }

    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable Long id) {
        if (bookService.delete(id) != 1) {
            throw new BusinessException(BOOK_NOT_FOUND);
        }
    }

    @PostMapping("/book/{id}")
    public void update(@RequestBody UpdateBookRequest updateBookRequest) {

        Long id = updateBookRequest.getId();
        PreconditionUtil.checkArgument(id != null && id > 0, new InvalidArgumentException("Invalid book id"));

        Book book = bookService.get(updateBookRequest.getId());
        if (book == null) {
            throw new BusinessException(BOOK_NOT_FOUND);
        }

        Long quantity = updateBookRequest.getQuantity();
        PreconditionUtil.checkArgument(updateBookRequest.getQuantity() >= 0,
                new InvalidArgumentException("Quantity should be non-negative"));

        book.setQuantity(quantity);
        bookService.update(book);

    }

    @GetMapping("/book/list")
    public PageInfo<Book> list(@RequestParam("page_num") int pageNum, @RequestParam("page_size") int pageSize) {

        PreconditionUtil.checkArgument(pageNum > 0 && pageSize > 0,
                new InvalidArgumentException("Invalid page number or page size"));
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> bookService.listAll());
    }

    @GetMapping("/book/search")
    public PageInfo<List<Book>> search(String title,
                                String author,
                                String isbn,
                                @RequestParam(value = "price", required = false) String[] priceRange,
                                @RequestParam(value = "quantity", required = false) Long[] quantityRange,
                                @RequestParam(value = "page_num", required = false) int pageNum,
                                @RequestParam(value = "page_size", required = false) int pageSize
                                ) {

        BigDecimal[] prices;
        if (priceRange != null) {
            if (priceRange.length != 2) {
                throw new InvalidArgumentException("Invalid price range");
            }
            prices = new BigDecimal[2];
            for (int i=0; i<prices.length; i++) {
                BigDecimal price;
                try {
                    price = new BigDecimal(priceRange[i]);
                } catch (NumberFormatException e) {
                    throw new InvalidArgumentException("price should be a numeric value");
                }
                PreconditionUtil.checkArgument(price.compareTo(BigDecimal.ZERO) >= 0,
                        new InvalidArgumentException("Price should be non-negative"));
                prices[i] = price;
            }
        } else {
            prices = null;
        }

        PreconditionUtil.checkArgument(pageNum > 0 && pageSize > 0,
                new InvalidArgumentException("Invalid page number or page size"));

        if (quantityRange != null) {
            if (quantityRange.length != 2) {
                throw new InvalidArgumentException("Invalid quantity range");
            }
        }

        return PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> bookService.search(title, author, isbn, prices, quantityRange));

    }

}
