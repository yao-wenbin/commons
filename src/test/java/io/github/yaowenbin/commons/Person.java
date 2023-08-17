package io.github.yaowenbin.commons;

public class Person {
    private Integer id;
    private Long no;
    private String username;

    public Person() { }

    public Person(Integer id, Long no, String username) {
        this.id = id;
        this.no = no;
        this.username = username;
    }

    public Integer id() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long no() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String username() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
