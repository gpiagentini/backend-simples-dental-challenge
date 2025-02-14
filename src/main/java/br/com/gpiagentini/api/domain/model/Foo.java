package br.com.gpiagentini.api.domain.model;

public class Foo {
    private String description;

    public Foo(String description) {
        this.description = description;
    }

    public boolean hasDescription(){
        return !description.isBlank();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
