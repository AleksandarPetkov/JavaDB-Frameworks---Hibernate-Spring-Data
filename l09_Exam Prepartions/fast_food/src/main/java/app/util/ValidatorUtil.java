package app.util;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidatorUtil {
    <T> boolean isValid(T dto);

    <T> Set<ConstraintViolation<T>> violations(T dto);
}
