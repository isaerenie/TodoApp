package techcareertodo.exception.todo;

import lombok.Getter;
import techcareertodo.util.ResponseStatus;

@Getter
public class TodoListNotDeleted extends RuntimeException{
    private final String message="Todo list not deleted";
    private final ResponseStatus status=ResponseStatus.FAILURE;
    private final String code="500";
}
