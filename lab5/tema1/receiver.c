// C Program for Message Queue (Reader Process)
#include <stdio.h>
#include <stdint.h>
#include <stdbool.h>
#include <sys/ipc.h>
#include <sys/msg.h>

// structure for message queue
struct mesg_buffer {
    long mesg_type;
    char mesg_text[100];
} message;

int main()
{
    // gcc receiver.c -o receiver
    // ./receiver
    key_t key;
    int msgid;
    FILE *input, *output;

    // ftok to generate unique key
    key = ftok("message_queue_name", 'B');
    
    // msgget creates a message queue
    // and returns identifier
    msgid = msgget(key, 0666 | IPC_CREAT);
    message.mesg_type = 1;
    // msgrcv to receive message
    msgrcv(msgid, &message, sizeof(message), 1, 0);
    
    // display the message
    printf("Data Received is : %s \n", message.mesg_text);
    output = fopen("results.txt", "w");
    input = fopen(message.mesg_text, "r");
    
    uint32_t count1 = 0;
    uint32_t count2 = 0;
    char buffer[256];
    bool ok = true;
    while(fgets(buffer, 255, input))
    {
        for (uint32_t i = 0; i < sizeof(buffer); ++i)
        {
            if ('<' == buffer[i])
                ++count1;
            if ('>' == buffer[i])
                ++count2;
                
        if (count1 - count2 > 1) { ok = false; break; }
        if (buffer[i] == '/' && count2 != count1 - 1) { ok = false; break; }
        if (buffer[i] == '\n' && (count1 != count2 || count1 == 0)) { ok = false; break; }
        }

        for(int i = 0; i < sizeof(buffer); ++i)
            buffer[i]=' ';
    }
    
    if (true == ok)
        fprintf(output, "Fisierul respecta standarul HTML.");
    else
        fprintf(output, "Fisierul nu respecta standarul HTML.");
    fclose(output);
    fclose(input);

    // to destroy the message queue
    msgctl(msgid, IPC_RMID, NULL);
    
    return 0;
}