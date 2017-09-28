package kz.maks.barter.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-28
 */
public class BadResponse extends BaseResponse {
    private List<String> errors = new ArrayList<>();

    public BadResponse() {
        super(true);
    }

    public List<String> getErrors() {
        return errors;
    }
}
