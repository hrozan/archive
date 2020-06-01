from worker import Worker
from random import randint
from time import time

def generate_random_array(length):
    numbers = []
    for i in range(length):
        numbers.append(randint(0,10000))
    return numbers


if __name__ == '__main__':

    t = time()

    w1 = Worker(generate_random_array(10000), 's')
    w2 = Worker(generate_random_array(10000), 'c')

    w1.start()
    w2.start()

    w1.join()
    w2.join()

    t = time() - t

    print(t * 10)
