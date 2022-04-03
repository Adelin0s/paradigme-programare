from generic_file import *

class TextAscii(GenericFile):
    path = ""
    freq = 0

    def __init__(self, path, freq):
        super.__init__()
        self.path = path
        self.freq = frq

    def get_path(self):
        return self.path

    def get_freq(self):
        return self.freq
