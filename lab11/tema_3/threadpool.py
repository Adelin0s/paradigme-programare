import logging
import os

from queue import Queue
from threading import Thread, Condition


class ThreadPool:
    def __init__(self, max_workers):
        self.m_pool = []
        self.m_tasks_queue = Queue()
        self.m_max_workers = min(max_workers, os.cpu_count())
        """
        A condition object has acquire() and release() methods that call the corresponding methods of the associated lock. 
        It also has a wait() method, and notify() and notifyAll() methods. 
        These three must only be called after the calling thread has acquired the lock.
        """
        self.m_lock = Condition()
        self.m_done = False
        self.m_running = False

        if max_workers is None:
            max_workers = 1
        if max_workers <= 0:
            raise ValueError("max_workers must be >= 0")

        for _ in range(max_workers):
            thread = Thread(target=self.run_loop)
            thread.daemon = True
            self.m_pool.append(thread)

    def run_loop(self):
        while True:
            with self.m_lock:
                if self.m_done:
                    return

                if self.m_running:
                    if not self.m_tasks_queue.empty():
                        task = self.m_tasks_queue.get_nowait()
                    else:
                        self.m_lock.wait()
                        continue
                else:
                    break

            if task:
                try:
                    task()
                except Exception as exception:
                    logging.debug(exception)
        logging.debug('Finish tasks')

    def submit(self, task):
        with self.m_lock:
            """
                Queue.put(item, block=True, timeout=None)
                block if necessary until a free slot is available
            """
            self.m_tasks_queue.put(task, False)
            self.m_lock.notify()

    def start(self):
        self.m_running = True

        for thread in self.m_pool:
            thread.start()

    def shutdown(self):
        with self.m_lock:
            self.m_done = True
            self.m_lock.notifyAll()

    def join(self):
        for threads in self.m_pool:
            threads.join()

    def __enter__(self):
        self.start()

    def __exit__(self, exc_type, exc_val, exc_tb):
        self.shutdown()
        self.join()
