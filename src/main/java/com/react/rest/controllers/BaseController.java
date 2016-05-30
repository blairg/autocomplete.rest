package com.react.rest.controllers;

import java.util.List;

/**
 * Created by bga11 on 29/05/2016.
 */
public abstract class BaseController<T, U> {
    public abstract List<U> ConvertEntitiesToModels(List<T> values);
}
