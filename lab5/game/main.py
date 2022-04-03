import pygame
import sys
import multiprocessing as mp

from multiprocessing.managers import BaseManager
from window import *


class Worker(mp.Process):
    def __init__(self, message_queue):
        self.message_queue = message_queue
        super(Worker, self).__init__()

    def run(self):
        result = self.message_queue.get()
        result.append('Worker: Hello!')
        self.message_queue.put(result)


class QueueManager(BaseManager):
    pass


if __name__ == '__main__':
    queue = mp.Queue()
    queue.put([])

    worker = Worker(queue)
    worker.start()

    QueueManager.register('get_queue', callable=lambda: queue)
    manager = QueueManager(address=('localhost', 50000), authkey=b'passwd')

    run = True
    clock = pygame.time.Clock()
    window = Window()
    window.draw_lines()

    server = manager.get_server()

    while run:
        clock.tick(60)
        window.update()

    server.serve_forever()

# def main():
#     run = True
#     clock = pygame.time.Clock()
#     window = Window()
#     window.draw_lines()
#
#     while run:
#         clock.tick(60)
#         window.update()
#
# main()
