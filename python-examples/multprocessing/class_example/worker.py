from threading import Thread
from time import sleep

class Worker(Thread):

    def __init__(self, numbers, op):
        Thread.__init__(self)
        self.numbers = numbers
        self.op = op

    def run(self):
        if self.op == 's':
            self.calc_square_sum()
        elif self.op == 'c':
            self.calc_cube_sum()
        else:
            print('opt error')



    def calc_square_sum(self):
        square_sum = 0
        # artificial delay
        sleep(0.2)
        for num in self.numbers:
            square_sum += (num * num)
        print(square_sum)

    def calc_cube_sum(self):
        cube_sum = 0
        # artificial delay
        sleep(0.2)
        for num in self.numbers:
            cube_sum += (num * num * num)
        print(cube_sum)

    pass