package src.Models;

import android.content.ContentValues;

import src.Database.Tables.UserTable;
import src.Interfaces.Entity;

public class User implements Entity {
    private Integer id;
    private String name;
    private String email;
    private String password;

    public User(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

    public User(String name, String email, String password) {
        this(email, password);
        this.setName(name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(UserTable.Entry.NAME, this.name);
        values.put(UserTable.Entry.EMAIL, this.email);
        values.put(UserTable.Entry.PASSWORD, this.password);

        return values;
    }
}
