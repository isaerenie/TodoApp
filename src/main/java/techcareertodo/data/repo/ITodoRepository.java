package techcareertodo.data.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import techcareertodo.business.dto.TodoDto;
import techcareertodo.data.entity.Todo;

import java.util.List;


public interface ITodoRepository extends JpaRepository<Todo, Long> {

}
