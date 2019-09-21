package org.kun.multi_thread_learning.read_write_lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author kun
 * @date 2019/09/21
 */
public class ReadWriteLockTest1 {

    private static Library LIBRARY;

    public static void main(String[] args) {
        initLibray();
        ExecutorService pool = Executors.newCachedThreadPool();
        Runnable queryBook = () -> {
            try {
                LIBRARY.QueryBookByName("Java");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Runnable addBook = () -> {
            try {
                Book bookToAddBook = new Book("Perl", new Author("xiaoxiao", 23));
                LIBRARY.addBook(bookToAddBook);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Runnable addBook1 = () -> {
            try {
                Book bookToAddBook = new Book("Scala", new Author("xiaoxiao", 23));
                LIBRARY.addBook(bookToAddBook);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        // // 读写互斥
        // pool.execute(queryBook);
        // pool.execute(addBook);

        // // 读读共享
        // pool.execute(queryBook);
        // pool.execute(queryBook);

        // 写写互斥
        pool.execute(addBook);
        pool.execute(addBook1);

        pool.shutdown();
    }

    private static void initLibray() {
        Author authorKun = new Author("kun", 28);
        Book javaBook = new Book("Java", authorKun);
        Book pythonBook = new Book("Python", authorKun);
        Book cBook = new Book("C", authorKun);
        Book javaScriptBook = new Book("JavaScript", authorKun);
        Map<String, Book> books = new HashMap<String, Book>();
        books.put("Java", javaBook);
        books.put("Python", pythonBook);
        books.put("C", cBook);
        books.put("JavaScript", javaScriptBook);
        LIBRARY = new Library(books);
    }

}

/**
 * @author kun
 * @date 2019/09/21
 */
class Library {

    private ReadWriteLock lock = null;
    private Lock readLock = null;
    private Lock writeLock = null;
    private Map<String, Book> books = null;

    public Library(Map<String, Book> books) {
        lock = new ReentrantReadWriteLock();
        readLock = lock.readLock();
        writeLock = lock.writeLock();
        this.books = books;
    }

    public Book QueryBookByName(String name) throws Exception {
        if (name == null && name.isEmpty()) {
            throw new Exception();
        }
        readLock.lock();
        Book book;
        try {
            // 体现互斥共享效果，加打印
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "正在查询书籍");
            }
            book = books.get(name);
        } finally {
            readLock.unlock();
        }
        return book;
    }

    public void addBook(Book book) throws Exception {
        if (book == null) {
            throw new Exception();
        }
        writeLock.lock();
        try {
            // 体现互斥共享效果，加打印
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "正在添加书籍");
            }
            books.put(book.getName(), book);
        } finally {
            writeLock.unlock();
        }

    }

}

class Book {

    private String name;
    private Author author;

    public Book(String name, Author author) {
        super();
        this.name = name;
        this.author = author;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @param author
     *            the author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
    }
}

class Author {
    private String name;
    private int age;

    public Author(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
}