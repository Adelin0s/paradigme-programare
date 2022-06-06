class Stare:
    def execute(self, student):
        raise NotImplementedError


class Happy(Stare):
    def execute(self, student):
        student.stare = "Studentul este fericit"


class Sad(Stare):
    def execute(self, student):
        student.stare = "Studentul este trist"


class Student:
    def __init__(self):
        self.stare = None

    def getStare(self):
        return self.stare


class Colega:
    def setStare1(self, student):
        Happy.execute(self, student)

    def setStare2(self, student):
        Sad.execute(self, student)


def main():
    colega = Colega()

    student1 = Student()
    student2 = Student()

    colega.setStare1(student1)
    colega.setStare2(student2)

    print(student1.getStare())
    print(student2.getStare())


if __name__ == "__main__":
    main()
