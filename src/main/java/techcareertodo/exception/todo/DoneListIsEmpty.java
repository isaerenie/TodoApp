package techcareertodo.exception.todo;

import lombok.Getter;
import techcareertodo.util.ResponseStatus;

@Getter
public class DoneListIsEmpty extends RuntimeException{
    private final String message="Done list is empty";
    private final ResponseStatus status=ResponseStatus.FAILURE;
    private final String code="404";
}
