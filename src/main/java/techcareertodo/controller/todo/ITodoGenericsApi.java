package techcareertodo.controller.todo;



import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import techcareertodo.util.ResponseMap;

import java.util.List;

public interface ITodoGenericsApi<D> {


    // ### CRUD ###############################
    // CREATE
    public ResponseMap todoServiceCreate(D d);

    // LIST
    public ResponseMap todoServiceList();

    // FIND
    public ResponseMap todoServiceFindById(Long id);

    // DELETE
    public ResponseMap todoServiceDeleteById(Long id);


    // UPDATE
    public ResponseMap todoServiceUpdateById(Long id, D d);

    //DONE
    public ResponseMap todoServiceAllDone();

    //TODO
    public ResponseMap todoServiceAllTodo();

    //DELETE DONE
    public ResponseMap todoServiceDeleteAllDone();

    //DELETE TODO
    public ResponseMap todoServiceDeleteAllTodo();

    //  DELETE ALL
    public ResponseMap todoServiceDeleteAll();


}
