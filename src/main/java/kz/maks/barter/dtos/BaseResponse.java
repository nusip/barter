package kz.maks.barter.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-24
 */
public class BaseResponse {
    private boolean hasErrors;
    private List<String> errors = new ArrayList<>();

    public BaseResponse() {}

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
