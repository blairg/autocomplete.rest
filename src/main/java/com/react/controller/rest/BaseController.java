package com.react.controller.rest;

import java.util.List;

public abstract class BaseController<T, U> {
    public abstract List<U> ConvertEntitiesToModels(List<T> values);
}
