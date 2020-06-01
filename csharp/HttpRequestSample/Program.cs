using System;
using System.Threading.Tasks;
using HttpRequestSample.Todo;

namespace HttpRequestSample
{
    class Program
    {
        private static async Task Start()
        {
            var todoService = new TodoService();

            // Get all Todos 
            // var todos = await todoService.GetAll();
            // todos.ForEach(todo => Console.WriteLine(todo.Title));

            // Save Todo 
            var newTodo = new TodoModel()
            {
                Title = "Teste",
                Completed = false, 
                UserId = 1
            };
            var savedTodo = await todoService.Save(newTodo);
            Console.WriteLine(savedTodo.Id);
        }


        static async Task Main(string[] args)
        {
            try
            {
                Console.WriteLine("🚀 Start");
                await Start();
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
        }
    }
}