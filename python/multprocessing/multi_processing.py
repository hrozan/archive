import time
from multiprocessing import Process
from random import randint


def calc_square_sum(numbers):
    square_sum = 0
    # artificial delay
    time.sleep(0.2)
    for num in numbers:
        square_sum += (num * num)
    print(square_sum)


def calc_cube_sum(numbers):
    cube_sum = 0
    # artificial delay
    time.sleep(0.2)
    for num in numbers:
        cube_sum += (num * num * num)
    print(cube_sum)


def generate_random_array(length):
    numbers = []
    for i in range(length):
        numbers.append(randint(0, 10000))
    return numbers


if __name__ == '__main__':
    numbers = generate_random_array(10000)

    t = time.time()

    # calc_square_sum(numbers)
    # calc_cube_sum(numbers)

    # create 2 process
    p1 = Process(target=calc_square_sum, args=(numbers,))
    p2 = Process(target=calc_cube_sum, args=(numbers,))

    # start process
    p1.start()
    p2.start()

    # wait for process to end
    p1.join()
    p2.join()

    t = time.time() - t

    print(t * 10)

