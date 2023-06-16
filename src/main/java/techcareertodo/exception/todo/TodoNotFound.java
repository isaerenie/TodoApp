package techcareertodo.exception.todo;

import lombok.Getter;
import techcareertodo.util.ResponseStatus;

import static techcareertodo.util.ResponseStatus.FAILURE;
@Getter
public class TodoNotFound extends RuntimeException{
    private final String message="Todo not found";
    private final ResponseStatus status=FAILURE;
    private final String code="404";
}
