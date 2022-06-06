# class Director:
#     def __init__(self, builder):
#         self.builder = builder
#
#     def changeBuilder(self, newBuilder):
#         self.builder = newBuilder
#
#     def makeCar(self):
#         self.builder.setColor()
#         self.builder.setEngine()
#         self.builder.setDoors()


class BuilderCar:
    def setDoors(self):
        raise NotImplementedError

    def setColor(self):
        raise NotImplementedError

    def setEngine(self):
        raise NotImplementedError

    def getBuilder(self):
        raise NotImplementedError


class BuilderSportCar(BuilderCar):
    # def __init__(self):
    #     self.car = SportCar()

    def setDoors(self):
        self.car.parts.append("Doors: 2")

    def setColor(self):
        self.car.parts.append("Color: Red")

    def setEngine(self):
        self.car.parts.append("Engine: 2.0")

    def getBuilder(self):
        return self.car


class BuilderSuvCar(BuilderCar):
    # def __init__(self):
    #     self.car = SportCar()

    def setDoors(self):
        self.car.parts.append("Doors: 4")

    def setColor(self):
        self.car.parts.append("Color: Black")

    def setEngine(self):
        self.car.parts.append("Engine: 3.0")

    def getBuilder(self):
        return self.car


# class SportCar:
#     def __init__(self):
#         self.parts = []
#
#     def showParts(self):
#         for i in self.parts:
#             print(i)
#
#
# class SuvCar:
#     def __init__(self):
#         self.parts = []
#
#     def showParts(self):
#         for i in self.parts:
#             print(i)


def main():
    builder = BuilderSportCar()


if __name__ == "__main__":
    main()
