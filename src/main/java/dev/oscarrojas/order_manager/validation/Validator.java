package dev.oscarrojas.order_manager.validation;

import java.util.List;

public interface Validator<T> {

    List<String> validate(T input);
}
