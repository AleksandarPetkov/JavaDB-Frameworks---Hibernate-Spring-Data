package mostwantedtest.util;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationUtilImpl implements ValidationUtil{

    private Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <E> boolean isValid(E object) {
        return this.validator.validate(object).size() == 0;
    }

}
