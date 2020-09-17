package src.Models;

import android.content.ContentValues;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

import src.Database.Tables.UserTable;
import src.Exceptions.EmailException;
import src.Interfaces.Entity;
import src.Protocols.EmailProtocol;

public class User implements Entity {
    private Integer id;
    private String name;
    private String email;
    private String password;

    public User(String email, String password) throws Exception {
        this.setEmail(email);
        this.setPassword(password);
    }

    public User(String name, String email, String password) throws Exception {
        this(email, password);
        this.setName(name);
    }

    public User(Integer id, String name, String email) throws Exception {
        this(name, email, null);
        this.setId(id);
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

    public void setName(String name) throws Exception {
        if (!name.chars().allMatch(Character::isLetter)) {
            throw new Exception("El nombre solo puede contener letras");
        }

        if (name.length() < 3 || name.length() > 25) {
            throw new Exception("El nombre debe contener entre 3 y 25 caracteres");
        }

        this.name = StringUtils.capitalize(name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmailException {
        EmailProtocol.validate(email);

        this.email = email.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        if (password.length() < 3 || password.length() > 25) {
            throw new Exception("La contraseña debe contener entre 3 y 25 caracteres");
        }

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
