import os
import threading
import queue

from threadpool import *

def task1():
    print('task1')


def task2():
    print('task2')


def task3():
    print('task3')


def main():
    thread_pool = ThreadPool(2)

    thread_pool.start()

    with ThreadPool(2) as thread_pool:
        thread_pool.submit(task1)
        thread_pool.submit(task2)
        thread_pool.submit(task3)

    thread_pool.join()

main()
