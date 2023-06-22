package techcareertodo.business.service.todo.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import techcareertodo.bean.ModelMapperBean;
import techcareertodo.business.dto.TodoDto;
import techcareertodo.business.service.todo.ITodoGenericsService;
import techcareertodo.data.entity.Todo;
import techcareertodo.data.repo.ITodoRepository;
import techcareertodo.exception.todo.DoneListIsEmpty;
import techcareertodo.exception.todo.TodoListNotDeleted;
import techcareertodo.exception.todo.TodoNotFound;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // Injection
@Log4j2

@Service
public class TodoServiceImpl implements ITodoGenericsService<TodoDto, Todo> {
    private final ITodoRepository iTodoRepository;
    private final ModelMapperBean modelMapperBean;


    @Transactional
    @Override
    public TodoDto EntityToDto(Todo todo) {
        return modelMapperBean.modelMapperMethod().map(todo, TodoDto.class);
    }

    @Override
    public Todo DtoToEntity(TodoDto todoDto) {
        return modelMapperBean.modelMapperMethod().map(todoDto, Todo.class);
    }

    @Override
    public TodoDto todoServiceCreate(TodoDto todoDto) {
        if (todoDto != null) {
            Todo todoModel = DtoToEntity(todoDto);
            Todo todoDb = iTodoRepository.save(todoModel);
            todoDto.setId(todoDb.getId());
            todoDto.setSystemDate(todoDb.getSystemDate());
        } else {
            throw new TodoNotFound();
        }
        return todoDto;

    }

    @Override
    public List<TodoDto> todoServiceList() {
        return iTodoRepository.findAll().stream().map(this::EntityToDto).collect(Collectors.toList());
    }

    @Override
    public TodoDto todoServiceFindById(Long id) {
        Todo todo = null;
        if (id != null) {
            todo = iTodoRepository.findById(id).orElseThrow(TodoNotFound::new);
        } else {
            throw new TodoNotFound();
        }
        return EntityToDto(todo);
    }

    @Override
    public TodoDto todoServiceDeleteById(Long id) {
        TodoDto todoDtoDelete = todoServiceFindById(id);
        Todo todo = DtoToEntity(todoDtoDelete);
        iTodoRepository.delete(todo);
        return todoDtoDelete;
    }

    @Override
    public TodoDto todoServiceUpdateById(Long id, TodoDto todoDto) {
        Todo todo = DtoToEntity(todoServiceFindById(id));
        if (todo != null) {
            todo.setId(id);
            todo.setSubject(todoDto.getSubject());
            todo.setDone(todoDto.isDone());
            iTodoRepository.save(todo);
            todoDto.setId(todo.getId());
            todoDto.setSystemDate(todoDto.getSystemDate());
        } else {
            throw new TodoNotFound();
        }
        return EntityToDto(todo);
    }

    @Override
    public TodoDto todoServiceUpdateByIdCheck(Long id, Boolean done) {
        Todo todo = DtoToEntity(todoServiceFindById(id));
        if (todo != null) {
            todo.setId(id);
            todo.setDone(done);
            iTodoRepository.save(todo);
            todo.setSystemDate(todo.getSystemDate());
        } else {
            throw new TodoNotFound();
        }
        return EntityToDto(todo);
    }

    @Override
    public List<TodoDto> todoServiceDone() {
        return iTodoRepository.findAll()
                .stream().filter(Todo::isDone)
                .map(this::EntityToDto).toList();
    }

    @Override
    public List<TodoDto> todoServiceTodo() {

        return iTodoRepository.findAll()
                .stream().filter(todo -> !todo.isDone())
                .map(this::EntityToDto).toList();
    }

    @Override

    public Boolean todoServiceDeleteAllDone() {
        if (iTodoRepository.findAll()
                .stream().filter(Todo::isDone)
                .toList().isEmpty()) {
            throw new DoneListIsEmpty();
        } else {
            iTodoRepository.findAll()
                    .stream().filter(Todo::isDone)
                    .forEach(todo -> iTodoRepository.delete(todo));
        }
        return iTodoRepository.findAll()
                .stream().filter(Todo::isDone)
                .toList().isEmpty();
    }


    @Override
    public Boolean todoServiceDeleteAll() {
        iTodoRepository.deleteAll();
        if (iTodoRepository.findAll().isEmpty()) {
            return true;
        } else {
            throw new TodoListNotDeleted();
        }
    }
}
