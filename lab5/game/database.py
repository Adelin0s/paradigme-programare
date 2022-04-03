import os
import sqlite3
from collections import namedtuple

Player = namedtuple("Player", ["id", "player_name", "score"])


class DatabaseManager:
    CREATE_CMD = '''CREATE TABLE IF NOT EXISTS tic_tac_toe (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    player_name VARCHAR(100) UNIQUE NOT NULL,
                    score INTEGER)'''

    INSERT_CMD = '''INSERT OR IGNORE INTO tic_tac_toe(player_name, score) 
                    VALUES (?, ?)'''

    SELECT_BY_PLAYER_NAME_CMD = '''SELECT * FROM tic_tac_toe WHERE player_name=?'''

    SELECT_BY_ID_CMD = '''SELECT * FROM tic_tac_toe WHERE id = ?'''

    UPDATE_CMD = '''UPDATE tic_tac_toe SET player_name=?, score=? WHERE id=?'''

    UPDATE_SCORE_BY_NAME_CMD = '''UPDATE tic_tac_toe SET score=score+1 WHERE player_name=?'''

    DELETE_ALL_CMD = '''DELETE FROM tic_tac_toe'''

    CURRENT_PATH = os.path.dirname(os.path.abspath(__file__))

    DATABASE_PATH = os.path.join(CURRENT_PATH, 'tic_tac_toe.db')

    def __init__(self):
        with sqlite3.connect(self.DATABASE_PATH) as db:
            cursor = db.cursor()
            cursor.execute(self.CREATE_CMD)
            cursor.close()

    def insert(self, player):
        with sqlite3.connect(self.DATABASE_PATH) as db:
            cursor = db.cursor()
            cursor.execute(self.INSERT_CMD,
                           (player.player_name, player.score))
            cursor.close()

    def select_by_player_name(self, player_name):
        with sqlite3.connect(self.DATABASE_PATH) as db:
            cursor = db.cursor()
            cursor.execute(self.SELECT_BY_PLAYER_NAME_CMD, (player_name,))
            rows = cursor.fetchall()
            cursor.close()
            return [Player(*row) for row in rows]

    def select_by_player_id(self, player_id):
        with sqlite3.connect(self.DATABASE_PATH) as db:
            cursor = db.cursor()
            cursor.execute(self.SELECT_BY_ID_CMD, (player_id,))
            row = cursor.fetchone()
            cursor.close()
            if row:
                return Player(*row)
            return None

    def update(self, player):
        with sqlite3.connect(self.DATABASE_PATH) as db:
            cursor = db.cursor()
            cursor.execute(self.UPDATE_CMD,
                           (player.player_name, player.score, player.id))
            cursor.close()

    def update_score(self, player):
        with sqlite3.connect(self.DATABASE_PATH) as db:
            cursor = db.cursor()
            cursor.execute(self.UPDATE_SCORE_BY_NAME_CMD,
                           (player.player_name,))
            cursor.close()

    def delete_all(self):
        with sqlite3.connect(self.DATABASE_PATH) as db:
            cursor = db.cursor()
            cursor.execute(self.DELETE_ALL_CMD)
            cursor.close()