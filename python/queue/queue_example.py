from multiprocessing import Queue
from threading import Thread
from random import random
from time import sleep


def data_generator(buffer):
    while True:
        data = random() * 10
        # add data to buffer
        buffer.put(data)
        print('Generate: {}'.format(data))
        sleep(2)

def data_consumer(buffer):
    while True:
        # get remove data from queue
        print('Consume: {}'.format(buffer.get()))
        sleep(3)


if __name__ == '__main__':
    buffer = Queue()

    thread_generator = Thread(target=data_generator, args=(buffer, ))
    thread_consumer = Thread(target=data_consumer, args=(buffer, ))

    # start
    thread_generator.start()
    thread_consumer.start()
        

    pass