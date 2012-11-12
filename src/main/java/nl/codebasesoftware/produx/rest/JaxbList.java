package nl.codebasesoftware.produx.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * User: rvanloen
 * Date: 11-11-12
 * Time: 15:47
 */
@XmlRootElement(name = "List")
public class JaxbList<T> {
    protected List<T> list;

    public JaxbList() {
    }

    public JaxbList(List<T> list) {
        this.list = list;
    }

    @XmlElement(name = "Item")
    public List<T> getList() {
        return list;
    }
}