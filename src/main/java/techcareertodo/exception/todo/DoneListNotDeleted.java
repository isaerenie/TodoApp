package techcareertodo.exception.todo;

import lombok.Getter;
import techcareertodo.util.ResponseStatus;

@Getter
public class DoneListNotDeleted extends RuntimeException{
    private final String message="Done list not deleted";
    private ResponseStatus status=ResponseStatus.FAILURE;
    private final String code="500";
}
