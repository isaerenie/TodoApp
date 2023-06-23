import React, { useEffect, useState } from "react";
import TodoApiService from "../../services/TodoApiService";
import { TodoDetails } from "../../models/TodoResponse";
function All() {
  // Yapılanlar listesini saklamak için bir durum değişkeni oluşturucaz.
  const [allTodos, setAllTodos] = useState<TodoDetails[]>([]);
  const todoApiService = new TodoApiService();

  // TodoApiService sınıfından bir nesne oluşturduk ve list fonksiyonunu çağırdık.
  const allTodoList = () => {
    todoApiService.list().then((res) => {
      const todo = res.data.details.todo;
      setAllTodos(todo);
    });
  };

  // TodoApiService sınıfından bir nesne oluşturduk ve updateChecked fonksiyonunu çağırdık.
  const updateCheck = (id: number, done: boolean) => {
    todoApiService.updateChecked(id, done).then(() => {
      allTodoList();
    });
  };

  // TodoApiService sınıfından bir nesne oluşturduk ve update fonksiyonunu çağırdık.
  const updateTodo = (id: number, todo: TodoDetails) => {
    todoApiService.update(id, todo).then(() => {
      allTodoList();
    });
  };

  // TodoApiService sınıfından bir nesne oluşturduk ve delete fonksiyonunu çağırdık.
  const handleDeleteClick = (id: number) => {
    todoApiService.delete(id).then(() => {
      allTodoList();
    });
  };

  // useEffect hook'unu kullanarak allTodoList fonksiyonunu çağırdık.
  useEffect(() => {
    allTodoList();
  }, []);

  // Düzenleme işlemleri için gerekli durum değişkenlerini oluşturduk.
  const [editId, setEditId] = useState<number | null>(null);
  const [editSubject, setEditSubject] = useState<string>("");
  const [editDone, seteditDone] = useState(false);

  // Düzenleme işlemleri için gerekli edit fonksiyonunu oluşturduk.
  const handleEditClick = (id: number, subject: string, done: boolean) => {
    setEditId(id);
    setEditSubject(subject);
    seteditDone(done);
  };

  // Düzenleme işlemleri için gerekli save fonksiyonunu oluşturduk.
  const handleSaveClick = (id: number) => {
    if (editSubject.trim() !== "") {
      // Düzenleme işlemi için gerekli todo nesnesini oluşturduk.
      const todo: TodoDetails = {
        id,
        systemDate: new Date(),
        subject: editSubject,
        done: editDone,
      };

      // Düzenleme işlemi için gerekli updateTodo fonksiyonunu çağırdık.
      updateTodo(id, todo);
    }
    // Düzenleme işlemi bittikten sonra editId ve editSubject durum değişkenlerini sıfırladık.
    setEditId(null);
    setEditSubject("");
  };
useEffect(() => {
    allTodoList();
}, [])
  return (
    <>
      {/* Todos dizisini map fonksiyonu ile dönerek her bir elemanı için bir div oluşturduk. */}
      {allTodos.map((todo) => (
        <div className="mb-5" key={todo.id}>
          <div className="row border border-info pt-3 mt-3 mb-3">
            <div className="col-md-9">
              {editId === todo.id ? (
                <p
                  className="text-start ps-2 border border-danger"
                  contentEditable
                  onBlur={(e) => setEditSubject(e.target.innerText)}
                  suppressContentEditableWarning={true}
                >
                  {editSubject}
                </p>
              ) : (
                <p className="text-start ps-2">{todo.subject}</p>
              )}
            </div>
            <div className="col-md-3 pe-0">
              <input
                className="form-check-input me-3 ms-5"
                type="checkbox"
                onChange={(e) => updateCheck(todo.id, e.target.checked)}
                checked={todo.done}
                id="flexCheckDefault"
              />
              {editId === todo.id ? (
                <span onClick={() => handleSaveClick(todo.id)}>
                  <i
                    className="fa-solid fa-save me-3"
                    style={{ cursor: "pointer", color: "green" }}
                  ></i>
                </span>
              ) : (
                <span
                  onClick={() =>
                    handleEditClick(todo.id, todo.subject, todo.done)
                  }
                >
                  <i
                    className="fa-solid fa-pen me-3"
                    style={{ cursor: "pointer", color: "orange" }}
                  ></i>
                </span>
              )}
              <span onClick={() => handleDeleteClick(todo.id)}>
                <i
                  className="fa-solid fa-trash text-danger mt-1"
                  style={{ cursor: "pointer" }}
                ></i>
              </span>
            </div>
          </div>
        </div>
      ))}
    </>
  );
}

export default All;
