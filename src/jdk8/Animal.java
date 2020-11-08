package jdk8;

import java.util.Objects;

public class Animal {

    private String aColor;
    private String typeName;

    public String getaColor() {
        return aColor;
    }

    public void setaColor(String aColor) {
        this.aColor = aColor;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Animal(String aColor, String typeName){
        this.aColor=aColor;
        this.typeName=typeName;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "aColor='" + aColor + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Animal animal = (Animal) o;
        return Objects.equals(aColor, animal.aColor) &&
                Objects.equals(typeName, animal.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aColor, typeName);
    }
}
