package ru.job4j.serialization;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "faculty")
public class Faculty {

    @XmlAttribute
    String name;

    @XmlAttribute
    int group;

    public Faculty() {
    }

    public Faculty(String name, int group) {
        this.name = name;
        this.group = group;
    }

    @Override
    public String toString() {
        return "Faculty{" + "name='" + name + '\''
                + ", group=" + group + '}';
    }
}
