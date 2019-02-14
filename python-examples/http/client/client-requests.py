from requests import get, post

if __name__ == '__main__':

    get_response = get('http://localhost:8000')
    post_response = post('http://localhost:8000')

    print(get_response.text)
    print(post_response.text)

    pass