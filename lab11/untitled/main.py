import asyncio


async def sum(index):
    s = 0
    for i in range(0, index + 1):
        s += i
    return s


async def main():
    queue = asyncio.Queue()
    for i in range(3, 8):
        queue.put_nowait(i)

    tasks = await asyncio.gather(
        sum(await queue.get()),
        sum(await queue.get()),
        sum(await queue.get()),
        sum(await queue.get()),
    )
    print(tasks)


asyncio.run(main())
