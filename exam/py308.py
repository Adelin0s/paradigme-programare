from tkinter import Tk, Canvas, Frame, BOTH, X, Y


class AbstractFigure:
    def draw(self):
        raise NotImplementedError


class ConcreteCircle(AbstractFigure):
    def draw(self):
        c = Circle()


class ConcreteTriangle(AbstractFigure):
    def draw(self):
        t = Triangle()


class ConcreteRectangle(AbstractFigure):
    def draw(self):
        r = Rectangle()


class Figure(Frame):
    def initUI(self):
        raise NotImplementedError


class Circle(Figure):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.pack(fill=X, expand=True)
        canvas = Canvas(self)
        canvas.create_oval(50, 50, 150, 150, fill="#601", outline="#601")
        canvas.pack(fill=BOTH, expand=True)


class Triangle(Figure):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.pack(fill=X, expand=True)
        canvas = Canvas(self)
        points = [0, 100, 50, 0, 100, 100]
        canvas.create_polygon(points, fill="red")
        canvas.pack(fill=BOTH, expand=True)


class Rectangle(Figure):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.pack(fill=X, expand=True)
        canvas = Canvas(self)
        canvas.create_rectangle(50, 50, 150, 150, fill="#601", outline="#601")
        canvas.pack(fill=BOTH, expand=True)


def main():
    root = Tk()
    circle = ConcreteCircle()
    # circle.draw()
    rectangle = ConcreteRectangle()
    # rectangle.draw()
    triangle = ConcreteTriangle()
    triangle.draw()
    root.geometry("330x220+300+300")
    root.mainloop()


if __name__ == '__main__':
    main()
