package by.epam.basavets.bean;

import java.util.List;

public interface Node<T> {

    List<T> getChildrenList();

    void setChildrenList(List<T> childrenList);
}
