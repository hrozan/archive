import time
import threading
from random import randint

def calc_square_sum(numbers):
    square_sum = 0
    # artificial delay
    time.sleep(0.2)
    for num in numbers :
        square_sum += (num * num)
    print(square_sum)

def calc_cube_sum(numbers):
    cube_sum = 0
    # artificial delay
    time.sleep(0.2)
    for num in numbers :
        cube_sum += (num * num * num)
    print(cube_sum)

def generate_random_array(length):
    numbers = []
    for i in range(length):
        numbers.append(randint(0,10000))
    return numbers

if __name__ == '__main__':
    numbers = generate_random_array(10000)

    t = time.time()

    # calc_square_sum(numbers)
    # calc_cube_sum(numbers)

    # create 2 threads
    th1 = threading.Thread(target=calc_square_sum, args=(numbers,) )
    th2 = threading.Thread(target=calc_cube_sum, args=(numbers, ) )

    # start thread
    th1.start()
    th2.start()

    # wait for threads to end
    th1.join()
    th2.join()

    t = time.time() - t

    print(t * 10)
    
