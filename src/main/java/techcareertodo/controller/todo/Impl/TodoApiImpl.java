package techcareertodo.controller.todo.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import techcareertodo.business.dto.TodoDto;
import techcareertodo.business.service.todo.ITodoGenericsService;
import techcareertodo.business.service.todo.impl.TodoServiceImpl;
import techcareertodo.controller.todo.ITodoGenericsApi;
import techcareertodo.util.ResponseMap;
import techcareertodo.util.ResponseStatus;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor //Injection
@Log4j2

@RestController
/*@CrossOrigin(origins = FrontEndURL.FRONTEND_URL) // CORS*/
@RequestMapping("/todo/api/v1")
public class TodoApiImpl implements ITodoGenericsApi<TodoDto> {

    private final ITodoGenericsService iTodoGenericsService;
    private final TodoServiceImpl todoServiceImpl;

    @PostMapping("/create")
    @Transactional
    @Override
    public ResponseMap todoServiceCreate(@RequestBody TodoDto todoDto) {
        return ResponseMap.builder()
                .status(ResponseStatus.SUCCESS)
                .message("Todo created successfully")
                .details(Map.of("todo", iTodoGenericsService.todoServiceCreate(todoDto)))
                .build();
    }

    @GetMapping("/list")
    @Override
    public ResponseMap todoServiceList() {
        return ResponseMap.builder()
                .status(ResponseStatus.SUCCESS)
                .message("All list retrieved successfully")
                .details(Map.of("todo", iTodoGenericsService.todoServiceList()))
                .build();
    }

    @GetMapping({"/find", "/find/{id}"})
    @Override
    public ResponseMap todoServiceFindById(@PathVariable Long id) {
        return ResponseMap.builder()
                .status(ResponseStatus.SUCCESS)
                .message("Todo retrieved successfully")
                .details(Map.of("todo", iTodoGenericsService.todoServiceFindById(id)))
                .build();
    }

    @Transactional
    @DeleteMapping({"/delete", "/delete/{id}"})
    @Override
    public ResponseMap todoServiceDeleteById(@PathVariable Long id) {
        return ResponseMap.builder()
                .status(ResponseStatus.SUCCESS)
                .message("Todo deleted successfully")
                .details(Map.of("todo", iTodoGenericsService.todoServiceDeleteById(id)))
                .build();
    }

    @Transactional
    @PutMapping({"/update", "/update/{id}"})
    @Override
    public ResponseMap todoServiceUpdateById(@PathVariable Long id,@RequestBody TodoDto todoDto) {
        return ResponseMap.builder()
                .status(ResponseStatus.SUCCESS)
                .message("Todo updated successfully")
                .details(Map.of("todo", iTodoGenericsService.todoServiceUpdateById(id, todoDto)))
                .build();
    }

    @Override
    @Transactional
   @GetMapping({"/done"})
    public ResponseMap todoServiceAllDone() {
        return ResponseMap.builder()
                .status(ResponseStatus.SUCCESS)
                .message("Done list retrieved successfully")
                .details(Map.of("todo", iTodoGenericsService.todoServiceDone()))
                .build();
    }

    @Override
    @Transactional
    @GetMapping({"/todo"})
    public ResponseMap todoServiceAllTodo() {
        return ResponseMap.builder()
                .status(ResponseStatus.SUCCESS)
                .message("Todo list retrieved successfully")
                .details(Map.of("todo", iTodoGenericsService.todoServiceTodo()))
                .build();
    }

    @Override
    @DeleteMapping({"/done"})
    public ResponseMap todoServiceDeleteAllDone() {
        return ResponseMap.builder()
                .status(ResponseStatus.SUCCESS)
                .message("All done deleted successfully")
                .details(Map.of("done", iTodoGenericsService.todoServiceDeleteAllDone()))
                .build();
    }

    @Override
    @DeleteMapping({"/todo"})
    public ResponseMap todoServiceDeleteAllTodo() {
        return ResponseMap.builder()
                .status(ResponseStatus.SUCCESS)
                .message("All todo deleted successfully")
                .details(Map.of("todo", iTodoGenericsService.todoServiceDeleteAllTodo()))
                .build();
    }

    @Override
    @DeleteMapping({"/all"})
    public ResponseMap todoServiceDeleteAll() {
        return ResponseMap.builder()
                .status(ResponseStatus.SUCCESS)
                .message("All list deleted successfully")
                .details(Map.of("todo", iTodoGenericsService.todoServiceDeleteAll()))
                .build();
    }

}
