package com.hmkcode.android;
 
import java.util.List;
import com.hmkcode.android.model.Book;
import com.hmkcode.android.sqlite.MySQLiteHelper;
import android.os.Bundle;
import android.app.Activity;
 
public class MainActivity extends Activity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        MySQLiteHelper db = new MySQLiteHelper(this);
 
        /**
         * CRUD Operations
         * */
        // add Books
        db.addBook(new Book("r1", "t1"));   
        db.addBook(new Book("r2", "t2"));       
        db.addBook(new Book("r3", "t3"));
 
        // get all books
        List<Book> list = db.getAllBooks();
 
        // delete one book
        db.deleteBook(list.get(0));
 
        // get all books
        db.getAllBooks();
 
    }
 
}