#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char **argv) {
    char **newargv;
    int i;

    unsetenv("LD_LIBRARY_PATH");
    unsetenv("LD_PRELOAD");
    unsetenv("LD_AUDIT");

    newargv = malloc((size_t)(argc + 1) * sizeof(char *));
    if (newargv == NULL) {
        return 127;
    }
    newargv[0] = "/usr/lib/jvm/java-21-openjdk-amd64/bin/java";
    for (i = 1; i < argc; i++) {
        newargv[i] = argv[i];
    }
    newargv[argc] = NULL;

    execv(newargv[0], newargv);
    perror("не удалось запустить java");
    return 127;
}
