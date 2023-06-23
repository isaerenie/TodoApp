import React from "react";
 
import { useRoutes } from "react-router-dom";

import All from "./pages/todo/All";
import Done from "./pages/todo/Done";
import Todo from "./pages/todo/Todo";
import Layout from "./components/Layout";
import { ToastContainer } from "react-toastify";

function App() {

  const routes = useRoutes([
    {
      path: "/",
      element: (
        <>
         
          <Layout />
          <ToastContainer /> 
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
