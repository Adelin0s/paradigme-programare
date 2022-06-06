import subprocess


def processWithoutInput(command_line):
    process1 = subprocess.Popen(command_line, shell=True, stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    return process1.communicate()[0]


def processWithInput(input, command_line):
    process = subprocess.Popen(command_line, shell=True, stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    process.stdin.write(input)
    return process.communicate()[0]


def main():
    commanand_line = "ip a | grep inet | wc -l"
    cm = commanand_line.split('|')
    print(cm)

    o1 = processWithoutInput(cm[0])

    o2 = processWithInput(o1, cm[1])

    o3 = processWithInput(o2, cm[2])

    print(o3)

main()
