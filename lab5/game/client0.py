import multiprocessing as mp

from window import *
from multiprocessing import Lock
from multiprocessing.managers import BaseManager
from database import *


if __name__ == '__main__':
    board_queue = mp.Queue()
    status_queue = mp.Queue()

    BaseManager.register('get_queue', callable=lambda: board_queue)
    BaseManager.register('status_queue', callable=lambda: status_queue)
    manager = BaseManager(address=('localhost', 50000), authkey=b'passwd')

    board_queue.put([0] * 9)
    # 1 - client1
    # 2 - client2
    status_queue.put([1, True])

    run = True
    clock = pygame.time.Clock()
    window = Window(board_queue)
    window.draw_lines()

    manager.start()

    update_draw_flag = True

    player_1_name = input("Type player1 name:")

    player1 = Player(id=None,
                     player_name=player_1_name,
                     score=0)
    database_manager = DatabaseManager()
    database_manager.insert(player1)

    '''
        return_type :   0) run (true/false)
                        1) switch_turn (bassically, if it must block to move)
                        2) player_win (0-no win 1-player1 win 2-player2 win)
                        
        status_type:    0) player_turn (player 1/2) 
                        1) runnable (true/false) - if a player has already finished
    '''

    player_win = 0
    while run and not player_win:
        status = status_queue.get()
        if not status[1]:
            run = False
        else:
            status_queue.put([status[0], True])
            if status[0] == 1:
                pygame.event.clear()
                if update_draw_flag: # run one time per move
                    window.draw_figure()
                    update_draw_flag = False
                clock.tick(60)
                run, switch_turn, player_win = window.update(status[0])
                if switch_turn:
                    status_queue.get()
                    status[0] = 2
                    status_queue.put(status)
                    update_draw_flag = True

    if status[0]:
        status_queue.get()
        status_queue.put([0, False])

    if player_win == 1:
        database_manager.update_score(player1)


