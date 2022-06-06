from tkinter import *
from tkinter import ttk


class Button:
    def showDialog(self):
        raise NotImplementedError


class ButtonEn(Button):
    def showDialog(self):
        root = Tk()
        root.wm_title("Title")
        root.geometry("500x200")
        content = ttk.Frame(root)
        button = ttk.Button(root, text="Button_En", command="metricChanged")
        button.grid()
        root.mainloop()


class ButtonRo(Button):
    def showDialog(self):
        root = Tk()
        root.wm_title("Title")
        root.geometry("500x200")
        content = ttk.Frame(root)
        button = ttk.Button(root, text="Button_Ro", command="metricChanged")
        button.grid()
        root.mainloop()


class Create:
    def createButton(self):
        raise NotImplementedError


class CreateEnglish(Create):
    def createButton(self):
        return ButtonEn()


class CreateRomana(Create):
    def createButton(self):
        return ButtonRo()


def main():
    creater_en = CreateEnglish()
    creater_ro = CreateRomana()

    inn = str(input("Input:"))
    if inn == "ro":
        button_ro = creater_ro.createButton()
    if inn == "en":
        button_en = creater_en.createButton()

    button_en.showDialog()
    # button_ro.showDialog()

if __name__ == "__main__":
    main()