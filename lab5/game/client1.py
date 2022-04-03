import multiprocessing as mp

from window import *
from multiprocessing.managers import BaseManager
from database import *

if __name__ == '__main__':
    BaseManager.register('get_queue')
    BaseManager.register('status_queue')
    manager = BaseManager(address=('localhost', 50000), authkey=b'passwd')

    manager.connect()

    board_queue = manager.get_queue()
    status_queue = manager.status_queue()

    run = True
    clock = pygame.time.Clock()
    window = Window(board_queue)
    window.draw_lines()

    update_draw_flag = True

    player_2_name = input("Type player2 name:")

    player2 = Player(id=None,
                     player_name=player_2_name,
                     score=0)
    database_manager = DatabaseManager()
    database_manager.insert(player2)

    window.update(2)

    '''
        return_type :   1) run (true/false)
                        2) switch_turn (bassically, if it must block to move)
                        3) player_win (0-no win 1-player1 win 2-player2 win)
    '''
    player_win = 0
    while run and not player_win:
        status = status_queue.get()
        if not status[1]: # if a player has already finished
            break
        status_queue.put(status)
        if status[0] == 2:
            pygame.event.clear()
            if update_draw_flag: # run one time per move
                window.draw_figure()
                update_draw_flag = False
            clock.tick(60)
            run, switch_turn, player_win = window.update(status[0])
            if switch_turn:
                status_queue.get()
                status[0] = 1
                status_queue.put(status)
                update_draw_flag = True

    if status[0]:
        status_queue.get()
        status_queue.put([0, False])

    if player_win == 2:
        database_manager.update_score(player2)
