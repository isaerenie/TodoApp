import React, { useEffect, useState } from "react";
import { TodoDetails } from "../../models/TodoResponse";
import TodoApiService from "../../services/TodoApiService";

function Todo() {
 // Yapılanlar listesini saklamak için bir durum değişkeni oluşturucaz.
 const [todos, setTodos] = useState<TodoDetails[]>([]);

 // TodoApiService sınıfından bir nesne oluşturdudk.
 const todoApiService = new TodoApiService();

 // TodoApiService sınıfından bir nesne oluşturduk ve doneList fonksiyonunu çağırdık.
 const todoList = () => {
   todoApiService.allTodoList().then((res) => {
     const todo = res.data.details.todo;
    setTodos(todo);
   });
 };

 // TodoApiService sınıfından bir nesne oluşturduk ve updateChecked fonksiyonunu çağırdık.
 const updateCheck = (id: number, done: boolean) => {
   new TodoApiService().updateChecked(id, done).then((res) => {
    todoList();
   });
 };

   // TodoApiService sınıfından bir nesne oluşturduk ve delete fonksiyonunu çağırdık.
   const deleteTodo = (id: number) => {
    new TodoApiService().delete(id).then((res) => {
      todoList();
    });
  };

 useEffect(() => {
   todoList();
 }, []);
  return (
    <>
   {/* 
   Todos dizisini map fonksiyonu ile dönerek her bir elemanı için bir div oluşturduk.
   */}
      {todos.map((todo, index) => {
        return (
          <div className="mb-5" key={index}>
            <div className="row border border-info pt-3 mt-3 mb-3">
              <div className="col-md-9">
                <p className="text-start ps-2">{todo.subject}</p>
              </div>
              <div className="col-md-3 pe-0">
                <input
                  className="form-check-input me-3 ms-5 "
                  type="checkbox"
                  onChange={(e) => updateCheck(todo.id, e.target.checked)}
                  value=""
                  checked={todo.done}
                  id="flexCheckDefault"
                />
                <i
                  className="fa-solid fa-pen me-3"
                  style={{ cursor: "pointer", color: "orange" }}
                ></i>
                  <span onClick={() => deleteTodo (todo.id)}>
                <i
                  className="fa-solid fa-trash text-danger mt-1"
                  style={{ cursor: "pointer" }}
                ></i>
                </span>
              </div>
            </div>
          </div>
        );
      })}
    </>
  );
}
export default Todo;
