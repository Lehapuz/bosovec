package by.epam.basavets.bean;

import java.util.List;

public interface Parent<T> {
    public String getParent();

    public void setParent(String text);

    public List<T> getChildrenList();

    public void setChildrenList(List<T> childrenList);
}
