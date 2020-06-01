using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace HttpRequestSample.Todo
{
    public class TodoService
    {
        private const string BaseUri = "https://jsonplaceholder.typicode.com";
        private readonly HttpClient _client = new HttpClient();

        public TodoService()
        {
            _client.BaseAddress = new Uri(BaseUri);
        }

        public async Task<List<TodoModel>> GetAll()
        {
            const string requestUri = "/todos";
            var response = await _client.GetAsync(requestUri);
            return await response.Content.ReadAsAsync<List<TodoModel>>();
        }

        public async Task<TodoModel> Save(TodoModel todo)
        {
            const string requestUri = "/todos";
            var jsonObj = JsonConvert.SerializeObject(todo);
            var requestPayload = new StringContent(jsonObj, Encoding.UTF8, "application/json");
            
            var response = await _client.PostAsync(requestUri, requestPayload);
            return await response.Content.ReadAsAsync<TodoModel>();
        }
    }
}