package com.wkp;

import java.util.List;
import java.util.Objects;

public class SearchPeople<T extends People> {
    public T Search(List<T> list, String id){
        for(T t : list){
            if(Objects.equals(t.getId(), id)){
                return t;
            }
        }
        return null;
    }
}
