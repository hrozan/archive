from http.server import HTTPServer
from http.server import BaseHTTPRequestHandler


class RequestHandler(BaseHTTPRequestHandler):

    def _set_headers(self):
        self.send_response(200)
        self.send_header('Content-type', 'text/html')
        self.end_headers()

    def do_GET(self):
        self._set_headers()
        html_file = open('index.html', 'rb')
        self.wfile.write(html_file.read())

    def do_HEAD(self):
        self._set_headers()

    def do_POST(self):
        # Doesn't do anything with posted data
        self._set_headers()
        html_file = open('index.html', 'rb')
        self.wfile.write(html_file.read())

    pass

def run(server_class=HTTPServer, handler_class=RequestHandler, host ='localhost', port=8000):
    server_address = (host, port)
    httpd = server_class(server_address, handler_class)
    print('Running on: http://{}:{}'.format(host, port))
    httpd.serve_forever()

if __name__ == '__main__':
    run()
