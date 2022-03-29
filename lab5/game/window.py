import pygame
import numpy as np
import sys

WIDTH = 600
HEIGHT = 600
LINE_WIDTH = 15

CIRCLE_RADIUS = 60
CIRCLE_WIDTH = 15

CROSS_WIDTH = 25
CROSS_SPACE = 55

RED = (255, 0, 0)
BG_COLOR = (23, 123, 234)

LINE_COLOR = (23, 12, 123)
CIRCLE_COLOR = (239, 231, 200)
CROSS_COLOR = (67, 67, 67)

window = pygame.display.set_mode((WIDTH, HEIGHT))
window.fill(BG_COLOR)
pygame.display.set_caption("X & 0")

class Window:
    def __init__(self):
        self.player = 1
        self.board = np.zeros((3, 3))

    def draw_lines(self):
        pygame.draw.line(window, LINE_COLOR, (0, 200), (600, 200), LINE_WIDTH)
        pygame.draw.line(window, LINE_COLOR, (0, 400), (600, 400), LINE_WIDTH)
        pygame.draw.line(window, LINE_COLOR, (200, 0), (200, 600), LINE_WIDTH)
        pygame.draw.line(window, LINE_COLOR, (400, 0), (400, 600), LINE_WIDTH)

    def draw_figure(self):
        for px in range(3):
            for py in range(3):
                if self.board[px][py] == 1:
                    pygame.draw.circle(window, CIRCLE_COLOR, ((py * 200 + 100), (px * 200 + 100)), CIRCLE_RADIUS, CIRCLE_WIDTH)
                elif self.board[px][py] == 2:
                    pygame.draw.line(window, CROSS_COLOR, (py * 200 + CROSS_SPACE, px * 200 + 200 - CROSS_SPACE), (py * 200 + 200 - CROSS_SPACE, px * 200 + CROSS_SPACE), CROSS_WIDTH)
                    pygame.draw.line(window, CROSS_COLOR, (py * 200 + CROSS_SPACE, px * 200 + CROSS_SPACE), (py * 200 + 200 - CROSS_SPACE, px * 200 + 200 - CROSS_SPACE), CROSS_WIDTH)

    def mark_square(self, px, py, player):
        self.board[py, px] = player

    def is_available(self, px, py):
        return self.board[py][px] == 0

    def is_board_full(self):
        for px in range(3):
            for py in range(3):
                if self.is_available(px, py):
                    return False
        return True

    def check_win(self, player):
        # vertical win check
        for col in range(3):
            if self.board[0][col] == player and self.board[1][col] == player and self.board[2][col] == player:
                self.draw_vertical_winning_line(col, player)
                return True

        # horizontal win check
        for row in range(3):
            if self.board[row][0] == player and self.board[row][1] == player and self.board[row][2] == player:
                self.draw_horizontal_winning_line(row, player)
                return True

        # asc diagonal win check
        if self.board[2][0] == player and self.board[1][1] == player and self.board[0][2] == player:
            self.draw_asc_diagonal(player)
            return True

        # desc diagonal win chek
        if self.board[0][0] == player and self.board[1][1] == player and self.board[2][2] == player:
            self.draw_desc_diagonal(player)
            return True

        return False

    def draw_vertical_winning_line(self, col, player):
        posX = col * 200 + 200 // 2

        if player == 1:
            color = CIRCLE_COLOR
        elif player == 2:
            color = CROSS_COLOR

        pygame.draw.line(window, color, (posX, 15), (posX, HEIGHT - 15), LINE_WIDTH)

    def draw_horizontal_winning_line(self, row, player):
        posY = row * 200 + 200 // 2

        if player == 1:
            color = CIRCLE_COLOR
        elif player == 2:
            color = CROSS_COLOR

        pygame.draw.line(window, color, (15, posY), (WIDTH - 15, posY), LINE_WIDTH)

    def draw_asc_diagonal(self, player):
        if player == 1:
            color = CIRCLE_COLOR
        elif player == 2:
            color = CROSS_COLOR

        pygame.draw.line(window, color, (15, HEIGHT - 15), (WIDTH - 15, 15), LINE_WIDTH)

    def draw_desc_diagonal(self, player):
        if player == 1:
            color = CIRCLE_COLOR
        elif player == 2:
            color = CROSS_COLOR

        pygame.draw.line(window, color, (15, 15), (WIDTH - 15, HEIGHT - 15), LINE_WIDTH)

    def update(self):
        for event in pygame.event.get():
            keys = pygame.key.get_pressed()
            if event.type == pygame.QUIT or keys[pygame.K_ESCAPE]:
                run = False
                pygame.quit()
                sys.exit()
            if event.type == pygame.MOUSEBUTTONUP:
                mouseX = event.pos[0]
                mouseY = event.pos[1]

                pmouseX = int(mouseX // 200)
                pmouseY = int(mouseY // 200)

                if self.is_available(pmouseX, pmouseY):
                    if self.player == 1:
                        self.mark_square(pmouseX, pmouseY, self.player)
                        self.check_win(self.player)
                        self.player = 2
                    elif self.player == 2:
                        self.mark_square(pmouseX, pmouseY, self.player)
                        self.check_win(self.player)
                        self.player = 1

                    self.draw_figure()

                    print(self.board)

            pygame.display.update()
