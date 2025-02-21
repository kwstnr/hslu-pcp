#include <stdio.h>
#include <stdlib.h>

void printNumbersGoto(int number) {
  int i = 0;
  start:
  printf("%i ", i);
  i++;
  if (i <= number) goto start;
}

int main(int argc, char** argv) {
  printNumbersGoto(7);
  printf("= printNumbersGoto(7)\n");
} 
