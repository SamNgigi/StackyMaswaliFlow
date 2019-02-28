package com.hai.jedi.stackymaswaliflow.Services;

import java.util.List;

public class ListWrapper<T> {
    /*
    * StackOverflow API wraps replies for questions or answers in a JSON object
    * with the name "items"
    *
    * To handle this, we create the following data class named ListWrapper.
    *
    * This class accepts a type parameter and simply wraps a list of objects of that type.
    * */
   public List<T> items;

}
