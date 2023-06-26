

import { useRoutes } from "react-router-dom";

import All, { TodoContext } from "./pages/todo/All";
import Done from "./pages/todo/Done";
import Todo from "./pages/todo/Todo";
import Layout from "./components/Layout";



function App() {

  const updateAllTodos = () => {
    console.log("updateAllTodos");
  };

  const routes = useRoutes([
    {
      path: "/",
      element: (
        <>
 <TodoContext.Provider value={{ updateAllTodos }}>
 <Layout />

 </TodoContext.Provider>
         


        </>
      ),
      children: [
        {
          path: "all",
          element: <All />,
        },
        {
          path: "done",
          element: <Done />,
        },
        {
          path: "todo",
          element: <Todo />,
        },
      ],
    },
    {
      path: "*",
      element: <div>Not Found</div>,
    },
  ]);

  return routes;
}

export default App;
