#
# DEPRECATED - SOCKET PROCESS
# DEPRECATED
#
# import socket
# from _thread import *
#
# server = "192.168.1.150"
# port = 7070
#
# s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
#
# try:
#     s.bind((server, port))
# except socket.error as e:
#     str(e)
#
# s.listen(2)
# print("Wait for a connection", "Server Started")
#
#
# def thread_client(connection):
#     reply = ""
#     while True:
#         try:
#             data = connection.recv(2048)
#             reply = data.decode("utf-8")
#
#             if not data:
#                 print("Disconnected")
#                 break
#             else:
#                 print("Received: ", reply)
#                 print("Sending: ", reply)
#             connection.sendall(str.encode(reply))
#         except:
#             break
#     print("Lost connection")
#     connection.close()
#
#
# while True:
#     connection, address = s.accept()
#     print("Connected to:", address)
#
#     start_new_thread(thread_client, (connection))
