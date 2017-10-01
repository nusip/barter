package kz.maks.barter.dtos;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-24
 */
public abstract class BaseResponse {
    private boolean hasErrors;

    public BaseResponse(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }
}
